package net.aquamine.server.network.handlers

import com.google.common.primitives.Ints
import kotlinx.collections.immutable.persistentListOf
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.apache.logging.log4j.LogManager
import net.aquamine.api.auth.GameProfile
import net.aquamine.server.AquaServer
import net.aquamine.server.auth.AquaGameProfile
import net.aquamine.server.auth.requests.SessionService
import net.aquamine.server.config.category.ProxyCategory
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.event.auth.AquaPlayerAuthenticationEvent
import net.aquamine.server.event.player.AquaPlayerLoginEvent
import net.aquamine.server.event.server.AquaSetupPermissionsEvent
import net.aquamine.server.locale.DisconnectMessages
import net.aquamine.server.locale.MinecraftTranslationManager
import net.aquamine.server.network.NioConnection
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.forwarding.ProxyForwardedData
import net.aquamine.server.network.forwarding.VelocityProxy
import net.aquamine.server.packet.PacketState
import net.aquamine.server.packet.`in`.login.PacketInEncryptionResponse
import net.aquamine.server.packet.`in`.login.PacketInLoginStart
import net.aquamine.server.packet.`in`.login.PacketInPluginResponse
import net.aquamine.server.packet.out.login.PacketOutEncryptionRequest
import net.aquamine.server.packet.out.login.PacketOutLoginDisconnect
import net.aquamine.server.packet.out.login.PacketOutLoginSuccess
import net.aquamine.server.packet.out.login.PacketOutPluginRequest
import net.aquamine.server.util.uuid.UUIDUtil
import net.aquamine.server.util.crypto.Encryption
import net.aquamine.server.util.random.RandomSource
import net.aquamine.server.util.readVarInt
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import javax.crypto.spec.SecretKeySpec

/**
 * Handles all inbound packets in the [Login][net.aquamine.server.packet.PacketState.LOGIN]
 * state.
 *
 * There are two inbound packets in this state that we handle:
 * - [Login Start][PacketInLoginStart] -
 *   sent to initiate the login sequence
 * - [Encryption Response][PacketInEncryptionResponse] -
 *   sent to confirm the client wants to enable encryption
 */
class LoginPacketHandler(
    private val server: AquaServer,
    private val connection: NioConnection,
    private val proxyForwardedData: ProxyForwardedData?
) : PacketHandler {

    // Doesn't really matter what this is, just needs to be unique.
    private val velocityMessageId = RANDOM.nextInt(Short.MAX_VALUE.toInt())
    private val forwardingSecret = server.config.proxy.secret.encodeToByteArray()

    private var name = "" // We cache the name here to avoid late initialization of the AquaPlayer object.
    private val verifyToken = generateVerifyToken()

    fun handleLoginStart(packet: PacketInLoginStart) {
        name = packet.name

        // Ignore online mode if we want proxy forwarding
        if (!server.config.isOnline || server.config.proxy.mode.authenticatesUsers) {
            if (server.config.proxy.mode == ProxyCategory.Mode.MODERN) {
                // Try to establish Velocity connection.
                connection.send(PacketOutPluginRequest(velocityMessageId, VELOCITY_CHANNEL_ID, ByteArray(0)))
            } else {
                processOfflineLogin(packet.name)
            }
            return
        }

        // The server isn't offline and the client wasn't forwarded, enable encryption.
        connection.send(PacketOutEncryptionRequest.create(Encryption.publicKey.encoded, verifyToken))
    }

    private fun processOfflineLogin(name: String) {
        // Copy over the data from legacy forwarding
        // Note: Per the protocol, offline players use UUID v3, rather than UUID v4.
        modifyAddressIfNeeded()
        val uuid = proxyForwardedData?.uuid ?: UUIDUtil.createOfflinePlayerId(name)
        val profile = AquaGameProfile.full(uuid, name, proxyForwardedData?.properties ?: persistentListOf())

        // Check the player can join and the login event was not cancelled.
        if (!callLoginEvent(profile)) return

        // Initialize the player and setup their permissions.
        val player = AquaPlayer(connection, profile, server.worldManager.default)
        val permissionsEvent = server.eventNode.fire(AquaSetupPermissionsEvent(player, AquaPlayer.DEFAULT_PERMISSION_FUNCTION))
        player.permissionFunction = permissionsEvent.result?.function ?: permissionsEvent.defaultFunction

        finishLogin(player)
    }

    fun handleEncryptionResponse(packet: PacketInEncryptionResponse) {
        // Check that the token we sent them is what they sent back to us.
        if (!verifyToken.contentEquals(Encryption.decrypt(packet.verifyToken))) return

        // We decrypt the shared secret with the server's private key and then create a new AES streaming
        // cipher to use for encryption and decryption (see https://minecraft.wiki/w/Java_Edition_protocol/Encryption).
        val sharedSecret = Encryption.decrypt(packet.secret)
        connection.enableEncryption(SecretKeySpec(sharedSecret, Encryption.SYMMETRIC_ALGORITHM))
        modifyAddressIfNeeded()

        // Fire the authentication event.
        val authEvent = AquaPlayerAuthenticationEvent(name)
        if (!authEvent.isAllowed()) return

        val profile = authEvent.result?.profile ?: SessionService.hasJoined(name, sharedSecret, server.config.server.ip)
        if (profile == null) {
            disconnect(DisconnectMessages.UNVERIFIED_USERNAME)
            return
        }

        if (!callLoginEvent(profile)) return
        val player = AquaPlayer(connection, profile, server.worldManager.default)

        val permissionsEvent = server.eventNode.fire(AquaSetupPermissionsEvent(player, AquaPlayer.DEFAULT_PERMISSION_FUNCTION))
        player.permissionFunction = permissionsEvent.result?.function ?: permissionsEvent.defaultFunction
        finishLogin(player)
    }

    fun handlePluginResponse(packet: PacketInPluginResponse) {
        if (packet.messageId != velocityMessageId || server.config.proxy.mode != ProxyCategory.Mode.MODERN) {
            disconnect(DisconnectMessages.UNEXPECTED_QUERY_RESPONSE)
            return
        }
        if (packet.data == null) {
            disconnect(Component.text("You must connect to this server through a Velocity proxy!"))
            return
        }
        if (packet.data.isEmpty()) { // For whatever reason, there was no data sent by Velocity
            LOGGER.error("Velocity sent no data in its login plugin response!")
            return
        }

        // Verify integrity
        val buffer = ByteBuffer.wrap(packet.data)
        val reader = BinaryReader(buffer)
        if (!VelocityProxy.verifyIntegrity(reader, forwardingSecret)) {
            disconnect(Component.text("Response received from Velocity could not be verified!"))
            return
        }

        val version = buffer.readVarInt()
        check(version <= VelocityProxy.MAX_SUPPORTED_FORWARDING_VERSION) {
            "Unsupported forwarding version $version! Supported up to: ${VelocityProxy.MAX_SUPPORTED_FORWARDING_VERSION}, version: $version"
        }

        val data = VelocityProxy.readData(reader)
        val address = connection.connectAddress() as InetSocketAddress
        connection.setAddress(InetSocketAddress(data.remoteAddress, address.port))

        // All good to go, let's construct our stuff
        LOGGER.debug("Detected Velocity login for ${data.uuid}")
        val profile = AquaGameProfile.full(data.uuid, data.username, data.properties)
        val player = AquaPlayer(connection, profile, server.worldManager.default)

        // Setup permissions for the player
        val permissionsEvent = server.eventNode.fire(AquaSetupPermissionsEvent(player, AquaPlayer.DEFAULT_PERMISSION_FUNCTION))
        player.permissionFunction = permissionsEvent.result?.function ?: permissionsEvent.defaultFunction
        finishLogin(player)
    }

    private fun finishLogin(player: AquaPlayer) {
        connection.enableCompression()
        connection.send(PacketOutLoginSuccess.create(player.profile))
        connection.setState(PacketState.PLAY)
        connection.setHandler(PlayPacketHandler(server, connection, player))

        try {
            server.playerManager.addPlayer(player)
        } catch (exception: Exception) {
            LOGGER.error("Disconnecting player ${player.profile.name} due to exception caught whilst attempting to load them in...", exception)
            player.disconnect(Component.text("An unexpected exception occurred. Please contact the system administrator."))
        }
    }

    private fun callLoginEvent(profile: GameProfile): Boolean {
        val event = server.eventNode.fire(AquaPlayerLoginEvent(profile, connection.connectAddress() as InetSocketAddress))
        if (!event.isAllowed()) {
            disconnect(event.result?.reason ?: DisconnectMessages.KICKED)
            return false
        }
        return true
    }

    private fun modifyAddressIfNeeded() {
        val address = connection.connectAddress() as? InetSocketAddress ?: return
        val data = proxyForwardedData ?: return
        val port = if (data.forwardedPort != -1) data.forwardedPort else address.port
        connection.setAddress(InetSocketAddress(data.forwardedAddress, port))
    }

    private fun disconnect(reason: Component) {
        connection.send(PacketOutLoginDisconnect(reason))
        connection.disconnect(reason)
    }

    override fun onDisconnect(message: Component?) {
        if (message == null) return
        val translated = MinecraftTranslationManager.render(message)
        LOGGER.info("${formatName()} was disconnected: ${PlainTextComponentSerializer.plainText().serialize(translated)}")
    }

    private fun formatName(): String = "$name (${connection.connectAddress()})"

    companion object {

        private const val VELOCITY_CHANNEL_ID = "velocity:player_info"
        private val RANDOM = RandomSource.createThreadSafe()
        private val LOGGER = LogManager.getLogger()

        @JvmStatic
        private fun generateVerifyToken(): ByteArray = Ints.toByteArray(RANDOM.nextInt())
    }
}

package net.aquamine.server.entity.player

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.apache.logging.log4j.LogManager
import net.aquamine.api.entity.player.ChatVisibility
import net.aquamine.api.scheduling.TaskTime
import net.aquamine.server.locale.DisconnectMessages
import net.aquamine.server.network.PacketGrouping
import net.aquamine.server.network.chat.ChatTypes
import net.aquamine.server.network.chat.LastSeenMessages
import net.aquamine.server.network.chat.LastSeenMessagesValidator
import net.aquamine.server.network.chat.MessageSignatureCache
import net.aquamine.server.network.chat.PlayerChatMessage
import net.aquamine.server.network.chat.RemoteChatSession
import net.aquamine.server.network.chat.RichChatType
import net.aquamine.server.network.chat.SignableCommand
import net.aquamine.server.network.chat.SignedMessageBody
import net.aquamine.server.network.chat.SignedMessageChain
import net.aquamine.server.packet.`in`.play.PacketInChat
import net.aquamine.server.packet.`in`.play.PacketInChatCommand
import net.aquamine.server.packet.out.play.PacketOutDisguisedChat
import net.aquamine.server.packet.out.play.PacketOutPlayerChat
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate
import net.aquamine.server.packet.out.play.PacketOutSystemChat
import net.aquamine.server.util.FutureChain
import java.time.Instant
import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicReference

/**
 * Tracks the chat chain for an individual player, ensuring it is not broken.
 */
class PlayerChatTracker(private val player: AquaPlayer) {

    private val server = player.server

    private val lastChatTimestamp = AtomicReference(Instant.EPOCH)
    private var signedMessageDecoder = if (server.config.advanced.enforceSecureProfiles) {
        SignedMessageChain.Decoder.REJECT_ALL
    } else {
        SignedMessageChain.Decoder.unsigned(player.uuid)
    }
    private val lastSeenMessages = LastSeenMessagesValidator(20)
    private val messageSignatureCache = MessageSignatureCache.createDefault()
    private val chatMessageChain = FutureChain { server.scheduler.scheduleTask(it, TaskTime.zero(), TaskTime.zero()) }

    fun handleChat(message: String, timestamp: Instant, update: LastSeenMessages.Update): LastSeenMessages? {
        if (!updateChatOrder(timestamp)) {
            LOGGER.warn("Out of order chat message '$message' received from ${player.profile.name}. Disconnecting...")
            player.disconnect(DisconnectMessages.OUT_OF_ORDER_CHAT)
            return null
        }
        if (player.settings.chatVisibility == ChatVisibility.HIDDEN) {
            player.connection.send(PacketOutSystemChat(Component.translatable("chat.disabled.options", NamedTextColor.RED), false))
            return null
        }
        val lastSeen = unpackAndApplyLastSeen(update)
        player.resetLastActionTime()
        return lastSeen
    }

    fun collectSignedArguments(packet: PacketInChatCommand, command: SignableCommand<*>,
                               lastSeenMessages: LastSeenMessages): Map<String, PlayerChatMessage> {
        val result = Object2ObjectOpenHashMap<String, PlayerChatMessage>()
        command.arguments.forEach {
            val signature = packet.argumentSignatures.get(it.name())
            val body = SignedMessageBody(it.value, packet.timestamp, packet.salt, lastSeenMessages)
            result.put(it.name(), signedMessageDecoder.unpack(signature, body))
        }
        return result
    }

    private fun unpackAndApplyLastSeen(update: LastSeenMessages.Update): LastSeenMessages? {
        val updated = synchronized(lastSeenMessages) { lastSeenMessages.applyUpdate(update) }
        if (updated == null) {
            LOGGER.warn("Failed to validate message acknowledgements from ${player.profile.name}. Disconnecting...")
            player.disconnect(DisconnectMessages.CHAT_VALIDATION_FAILED)
        }
        return updated
    }

    private fun updateChatOrder(timestamp: Instant): Boolean {
        do {
            val current = lastChatTimestamp.get()
            if (timestamp.isBefore(current)) return false
        } while (!lastChatTimestamp.compareAndSet(current, timestamp))
        return true
    }

    fun getSignedMessage(packet: PacketInChat, lastSeenMessages: LastSeenMessages): PlayerChatMessage {
        val body = SignedMessageBody(packet.message, packet.timestamp, packet.salt, lastSeenMessages)
        return signedMessageDecoder.unpack(packet.signature, body)
    }

    fun sendPlayerChatMessage(message: PlayerChatMessage, type: RichChatType.Bound) {
        val packet = PacketOutPlayerChat(message.link.sender, message.link.index, message.signature, message.signedBody.pack(messageSignatureCache),
            message.unsignedContent, message.filterMask, type.toNetwork())
        player.connection.send(packet)
        addPendingMessage(message)
    }

    fun sendDisguisedChatMessage(message: Component, type: RichChatType.Bound) {
        player.connection.send(PacketOutDisguisedChat(message, type.toNetwork()))
    }

    private fun addPendingMessage(message: PlayerChatMessage) {
        val signature = message.signature ?: return
        messageSignatureCache.push(message)
        val trackedMessageCount = synchronized(lastSeenMessages) {
            lastSeenMessages.addPending(signature)
            lastSeenMessages.trackedMessagesCount()
        }
        if (trackedMessageCount > 4096) {
            player.disconnect(Component.translatable("multiplayer.disconnect.too_many_pending_chats"))
        }
    }

    fun addMessageToChain(message: PlayerChatMessage, unsignedContent: Component) {
        chatMessageChain.append {
            CompletableFuture.supplyAsync({ broadcastChatMessage(message.withUnsignedContent(unsignedContent)) }, it)
        }
    }

    private fun broadcastChatMessage(message: PlayerChatMessage) {
        server.playerManager.broadcastChatMessage(message, player, RichChatType.bind(ChatTypes.CHAT, player))
    }

    fun resetPlayerChatState(session: RemoteChatSession) {
        signedMessageDecoder = session.createMessageDecoder(player.uuid)
        chatMessageChain.append {
            player.setChatSession(session)
            PacketGrouping.sendGroupedPacket(server, PacketOutPlayerInfoUpdate(PacketOutPlayerInfoUpdate.Action.INITIALIZE_CHAT, player))
            CompletableFuture.completedFuture(null)
        }
    }

    fun onDisconnect() {
        chatMessageChain.close()
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}

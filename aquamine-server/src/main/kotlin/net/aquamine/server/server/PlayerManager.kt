package net.aquamine.server.server

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.apache.logging.log4j.LogManager
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.statistic.CustomStatistics
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.World
import net.aquamine.server.KryptonServer
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.event.player.KryptonJoinEvent
import net.aquamine.server.event.player.KryptonPlayerQuitEvent
import net.aquamine.server.network.chat.RichChatType
import net.aquamine.server.network.chat.OutgoingChatMessage
import net.aquamine.server.network.chat.PlayerChatMessage
import net.aquamine.server.packet.Packet
import net.aquamine.server.packet.out.play.GameEventTypes
import net.aquamine.server.packet.out.play.PacketOutAbilities
import net.aquamine.server.packet.out.play.PacketOutChangeDifficulty
import net.aquamine.server.packet.out.play.PacketOutEntityEvent
import net.aquamine.server.packet.out.play.PacketOutGameEvent
import net.aquamine.server.packet.out.play.PacketOutInitializeWorldBorder
import net.aquamine.server.packet.out.play.PacketOutLogin
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate
import net.aquamine.server.packet.out.play.PacketOutPluginMessage
import net.aquamine.server.packet.out.play.PacketOutResourcePack
import net.aquamine.server.packet.out.play.PacketOutSetContainerContent
import net.aquamine.server.packet.out.play.PacketOutSetDefaultSpawnPosition
import net.aquamine.server.packet.out.play.PacketOutSetHeldItem
import net.aquamine.server.packet.out.play.PacketOutSynchronizePlayerPosition
import net.aquamine.server.packet.out.play.PacketOutUpdateEnabledFeatures
import net.aquamine.server.packet.out.play.PacketOutUpdateRecipeBook
import net.aquamine.server.packet.out.play.PacketOutUpdateRecipes
import net.aquamine.server.packet.out.play.PacketOutUpdateTime
import net.aquamine.server.entity.player.RecipeBookSettings
import net.aquamine.server.locale.DisconnectMessages
import net.aquamine.server.network.PacketGrouping
import net.aquamine.server.packet.out.play.PacketOutDisconnect
import net.aquamine.server.packet.out.play.PacketOutSystemChat
import net.aquamine.server.packet.out.play.PacketOutUpdateTags
import net.aquamine.server.registry.KryptonDynamicRegistries
import net.aquamine.server.registry.network.RegistrySerialization
import net.aquamine.server.tags.TagSerializer
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.biome.BiomeManager
import net.aquamine.server.world.data.PlayerDataSerializer
import net.aquamine.server.world.dimension.KryptonDimensionType
import net.aquamine.server.world.rule.GameRuleKeys
import org.kryptonmc.serialization.Dynamic
import org.kryptonmc.serialization.nbt.NbtOps
import java.time.Instant
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

class PlayerManager(
    private val server: KryptonServer,
    private val dataSerializer: PlayerDataSerializer,
    private val statsSerializer: StatisticsSerializer?
) {

    private val serializeData = server.config.advanced.serializePlayerData
    private val players = CopyOnWriteArrayList<KryptonPlayer>()
    private val playersByName = ConcurrentHashMap<String, KryptonPlayer>()
    private val playersByUUID = ConcurrentHashMap<UUID, KryptonPlayer>()

    fun players(): List<KryptonPlayer> = players

    fun getPlayer(name: String): KryptonPlayer? = playersByName.get(name)

    fun getPlayer(uuid: UUID): KryptonPlayer? = playersByUUID.get(uuid)

    fun addPlayer(player: KryptonPlayer) {
        val profile = player.profile
        val name = server.profileCache.getProfile(profile.uuid)?.name ?: profile.name
        server.profileCache.addProfile(profile)

        val dimension = loadPlayer(player)
        val world = server.worldManager.worlds.get(dimension) ?: server.worldManager.default
        player.world = world

        val location = player.position
        LOGGER.info("Player ${profile.name} logged in with entity ID ${player.id} at $location")

        // Join the game
        val reducedDebugInfo = world.gameRules().getBoolean(GameRuleKeys.REDUCED_DEBUG_INFO)
        val doImmediateRespawn = world.gameRules().getBoolean(GameRuleKeys.DO_IMMEDIATE_RESPAWN)
        player.connection.send(PacketOutLogin(
            player.id,
            world.data.isHardcore,
            player.gameModeSystem.gameMode(),
            player.gameModeSystem.previousGameMode(),
            server.worldManager.worlds.keys,
            RegistrySerialization.networkedRegistries(world.registryHolder),
            KryptonDynamicRegistries.DIMENSION_TYPE.getResourceKey(world.dimensionType)!!,
            world.dimension,
            BiomeManager.obfuscateSeed(world.seed),
            server.config.status.maxPlayers,
            server.config.world.viewDistance,
            server.config.world.simulationDistance,
            reducedDebugInfo,
            !doImmediateRespawn,
            false,
            false,
            null
        ))
        player.connection.send(PacketOutUpdateEnabledFeatures(setOf(Key.key("vanilla"))))
        player.connection.send(PacketOutPluginMessage(BRAND_KEY, BRAND_MESSAGE))
        player.connection.send(PacketOutChangeDifficulty.from(world.difficulty))

        // Player data stuff
        player.connection.send(PacketOutAbilities.create(player.abilities))
        player.connection.send(PacketOutSetHeldItem(player.inventory.heldSlot))
        player.connection.send(PacketOutUpdateRecipes)
        player.connection.send(PacketOutUpdateTags(TagSerializer.serializeTagsToNetwork(world.registryHolder)))
        player.connection.send(PacketOutEntityEvent(player.id, if (reducedDebugInfo) ENABLE_REDUCED_DEBUG_SCREEN else DISABLE_REDUCED_DEBUG_SCREEN))
        sendCommands(player)
        player.statisticsTracker.invalidate()
        player.connection.send(PacketOutUpdateRecipeBook(PacketOutUpdateRecipeBook.Action.INIT, emptyList(), emptyList(), RecipeBookSettings()))
        world.scoreboard.addViewer(player)
        server.statusManager.invalidateStatus()

        // Add the player to the list and cache maps
        players.add(player)
        playersByName.put(player.profile.name, player)
        playersByUUID.put(player.uuid, player)

        // Fire join event and send result message
        val joinEvent = server.eventNode.fire(KryptonJoinEvent(player, !profile.name.equals(name, true)))
        if (!joinEvent.isAllowed()) {
            // Use default reason if denied without specified reason
            val reason = joinEvent.result?.message ?: DisconnectMessages.KICKED
            player.connection.send(PacketOutDisconnect(reason))
            player.connection.disconnect(reason)
            return
        }

        val joinMessage = joinEvent.result?.message ?: getDefaultJoinMessage(player, joinEvent.hasJoinedBefore)
        broadcastSystemMessage(joinMessage, false)

        player.connection.send(PacketOutSynchronizePlayerPosition.fromPlayer(player))
        player.connection.send(PacketOutPlayerInfoUpdate.createPlayerInitializing(players))
        world.spawnPlayer(player)
        player.sendInitialChunks()

        // TODO: Custom boss events, resource pack pack, mob effects
        sendWorldInfo(world, player)
        if (server.config.server.resourcePack.uri.isNotEmpty()) {
            val resourcePack = server.config.server.resourcePack
            player.connection.send(PacketOutResourcePack(resourcePack.uri, resourcePack.hash, resourcePack.forced, resourcePack.prompt))
        }

        // Send inventory data
        player.connection.send(PacketOutSetContainerContent.fromPlayerInventory(player.inventory))
    }

    private fun loadPlayer(player: KryptonPlayer): ResourceKey<World> {
        if (!serializeData) {
            // If we aren't serializing data, we need to make sure the player doesn't spawn at (0, 0, 0) every time
            player.position = player.world.data.spawnPos().asPosition()
            // We also would like for the player to have the default game mode if it is forced
            if (server.config.world.forceDefaultGameMode) player.gameModeSystem.setGameMode(server.config.world.defaultGameMode, null)
            // The overworld is the default dimension
            return World.OVERWORLD
        }

        val nbt = dataSerializer.load(player)
        val dimension = if (nbt != null) {
            KryptonDimensionType.parseLegacy(Dynamic(NbtOps.INSTANCE, nbt.get("Dimension")))
                .resultOrPartial { LOGGER.error(it) }
                .orElse(World.OVERWORLD)
        } else {
            World.OVERWORLD
        }

        if (nbt != null) {
            server.userManager.updateUser(player.profile.uuid, nbt)
            // Only load statistics if we are serializing player data
            statsSerializer?.loadAll(player)
        }
        return dimension
    }

    fun removePlayer(player: KryptonPlayer) {
        val event = server.eventNode.fire(KryptonPlayerQuitEvent(player))

        player.statisticsTracker.incrementStatistic(CustomStatistics.LEAVE_GAME.get())
        savePlayer(player)
        player.world.chunkManager.removePlayer(player)
        player.world.scoreboard.removeViewer(player, false)

        // Remove from caches
        player.world.removeEntity(player)
        players.remove(player)
        playersByName.remove(player.profile.name)
        playersByUUID.remove(player.uuid)

        // Send info and quit message
        server.statusManager.invalidateStatus()
        event.quitMessage?.let { server.sendMessage(it) }
    }

    fun broadcast(packet: Packet, world: KryptonWorld, x: Double, y: Double, z: Double, radius: Double, except: KryptonPlayer?) {
        PacketGrouping.sendGroupedPacket(server, packet) {
            if (it === except || it.world !== world) return@sendGroupedPacket false
            val dx = x - it.position.x
            val dy = y - it.position.y
            val dz = z - it.position.z
            dx * dx + dy * dy + dz * dz < radius * radius
        }
    }

    fun broadcast(packet: Packet, world: KryptonWorld, pos: Vec3i, radius: Double, except: KryptonPlayer?) {
        broadcast(packet, world, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), radius, except)
    }

    private fun broadcastSystemMessage(message: Component, overlay: Boolean) {
        server.console.sendSystemMessage(message)
        PacketGrouping.sendGroupedPacket(server, PacketOutSystemChat(message, overlay)) { it.acceptsSystemMessages(overlay) }
    }

    fun broadcastChatMessage(message: PlayerChatMessage, source: KryptonPlayer, type: RichChatType.Bound) {
        val trusted = verifyChatTrusted(message)
        server.console.logChatMessage(message.decoratedContent(), type, if (trusted) null else "Not Secure")
        val outgoingMessage = OutgoingChatMessage.create(message)

        var fullyFiltered = false
        players.forEach {
            val filter = source.shouldFilterMessageTo(it)
            it.sendChatMessage(outgoingMessage, filter, type)
            fullyFiltered = fullyFiltered or (filter && message.isFullyFiltered())
        }

        if (fullyFiltered) source.sendSystemMessage(CHAT_FILTERED_FULL)
    }

    private fun verifyChatTrusted(message: PlayerChatMessage): Boolean = message.hasSignature() && !message.hasExpired(Instant.now())

    fun disconnectAll() {
        players.forEach { it.disconnect(DisconnectMessages.SERVER_SHUTDOWN) }
    }

    fun saveAll() {
        players.forEach(::savePlayer)
    }

    private fun sendCommands(player: KryptonPlayer) {
        player.connection.send(PacketOutEntityEvent(player.id, OP_PERMISSION_LEVEL_4))
        server.commandManager.updateCommands(player)
    }

    private fun savePlayer(player: KryptonPlayer) {
        if (!serializeData) return
        val savedData = dataSerializer.save(player)
        server.userManager.updateUser(player.uuid, savedData)
        statsSerializer?.saveAll(player)
    }

    private fun sendWorldInfo(world: KryptonWorld, player: KryptonPlayer) {
        player.connection.send(PacketOutInitializeWorldBorder.create(world.border))
        player.connection.send(PacketOutUpdateTime.create(world.data))
        player.connection.send(PacketOutSetDefaultSpawnPosition(world.data.spawnPos(), world.data.spawnAngle))
        if (world.isRaining()) {
            player.connection.send(PacketOutGameEvent(GameEventTypes.BEGIN_RAINING))
            player.connection.send(PacketOutGameEvent(GameEventTypes.RAIN_LEVEL_CHANGE, world.getRainLevel(1F)))
            player.connection.send(PacketOutGameEvent(GameEventTypes.THUNDER_LEVEL_CHANGE, world.getThunderLevel(1F)))
        }
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
        private val BRAND_KEY = Key.key("brand")
        // The word "Krypton" encoded in to UTF-8 and then prefixed with the length, which in this case is 7.
        private val BRAND_MESSAGE = byteArrayOf(7, 75, 114, 121, 112, 116, 111, 110)
        private val CHAT_FILTERED_FULL = Component.translatable("chat.filtered_full")

        private const val ENABLE_REDUCED_DEBUG_SCREEN: Byte = 22
        private const val DISABLE_REDUCED_DEBUG_SCREEN: Byte = 23
        private const val OP_PERMISSION_LEVEL_4: Byte = 28

        @JvmStatic
        private fun getDefaultJoinMessage(player: KryptonPlayer, joinedBefore: Boolean): Component {
            val key = if (joinedBefore) "multiplayer.player.joined.renamed" else "multiplayer.player.joined"
            return Component.translatable(key, NamedTextColor.YELLOW, Component.text(player.name))
        }
    }
}

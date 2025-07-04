package net.aquamine.server.network.handlers

import com.mojang.brigadier.StringReader
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.adventure.translation.Translator
import org.apache.logging.log4j.LogManager
import net.aquamine.api.entity.Hand
import net.aquamine.api.resource.ResourcePack
import net.aquamine.api.util.Position
import net.aquamine.api.util.Vec3d
import net.aquamine.api.world.GameMode
import net.aquamine.server.KryptonServer
import net.aquamine.server.command.CommandSigningContext
import net.aquamine.server.commands.KryptonPermission
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.entity.player.KryptonPlayerSettings
import net.aquamine.server.entity.player.KryptonSkinParts
import net.aquamine.server.entity.player.PlayerPublicKey
import net.aquamine.server.event.command.KryptonCommandExecuteEvent
import net.aquamine.server.event.player.KryptonPlayerChatEvent
import net.aquamine.server.event.player.KryptonPlayerMoveEvent
import net.aquamine.server.event.player.interact.KryptonPlayerPlaceBlockEvent
import net.aquamine.server.event.player.KryptonPluginMessageReceivedEvent
import net.aquamine.server.event.player.KryptonPlayerResourcePackStatusEvent
import net.aquamine.server.inventory.KryptonPlayerInventory
import net.aquamine.server.item.handler
import net.aquamine.server.item.handler.ItemTimedHandler
import net.aquamine.server.network.chat.ChatUtil
import net.aquamine.server.network.chat.RemoteChatSession
import net.aquamine.server.network.chat.SignableCommand
import net.aquamine.server.network.chat.SignedMessageChain
import net.aquamine.server.packet.`in`.play.PacketInAbilities
import net.aquamine.server.packet.`in`.play.PacketInChatCommand
import net.aquamine.server.packet.`in`.play.PacketInChat
import net.aquamine.server.packet.`in`.play.PacketInChatSessionUpdate
import net.aquamine.server.packet.`in`.play.PacketInClientCommand
import net.aquamine.server.packet.`in`.play.PacketInClientInformation
import net.aquamine.server.packet.`in`.play.PacketInCommandSuggestionsRequest
import net.aquamine.server.packet.`in`.play.PacketInInteract
import net.aquamine.server.packet.`in`.play.PacketInKeepAlive
import net.aquamine.server.packet.`in`.play.PacketInPlayerAction
import net.aquamine.server.packet.`in`.play.PacketInPlayerAction.Action as PlayerAction
import net.aquamine.server.packet.`in`.play.PacketInPlayerCommand
import net.aquamine.server.packet.`in`.play.PacketInPlayerCommand.Action as EntityAction
import net.aquamine.server.packet.`in`.play.PacketInPlayerInput
import net.aquamine.server.packet.`in`.play.PacketInPluginMessage
import net.aquamine.server.packet.`in`.play.PacketInQueryEntityTag
import net.aquamine.server.packet.`in`.play.PacketInResourcePack
import net.aquamine.server.packet.`in`.play.PacketInSetCreativeModeSlot
import net.aquamine.server.packet.`in`.play.PacketInSetHeldItem
import net.aquamine.server.packet.`in`.play.PacketInSetPlayerPosition
import net.aquamine.server.packet.`in`.play.PacketInSetPlayerPositionAndRotation
import net.aquamine.server.packet.`in`.play.PacketInSetPlayerRotation
import net.aquamine.server.packet.`in`.play.PacketInSwingArm
import net.aquamine.server.packet.`in`.play.PacketInUseItem
import net.aquamine.server.packet.`in`.play.PacketInUseItemOn
import net.aquamine.server.packet.out.play.EntityAnimations
import net.aquamine.server.packet.out.play.PacketOutAnimation
import net.aquamine.server.packet.out.play.PacketOutCommandSuggestionsResponse
import net.aquamine.server.packet.out.play.PacketOutDisconnect
import net.aquamine.server.packet.out.play.PacketOutKeepAlive
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate
import net.aquamine.server.packet.out.play.PacketOutTagQueryResponse
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.util.crypto.SignatureValidator
import net.aquamine.server.world.block.KryptonBlocks
import net.aquamine.server.coordinate.ChunkPos
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.event.player.action.KryptonPlayerStartSneakingEvent
import net.aquamine.server.event.player.action.KryptonPlayerStartSprintingEvent
import net.aquamine.server.event.player.action.KryptonPlayerStopSneakingEvent
import net.aquamine.server.event.player.action.KryptonPlayerStopSprintingEvent
import net.aquamine.server.event.player.interact.KryptonPlayerAttackEntityEvent
import net.aquamine.server.event.player.interact.KryptonPlayerInteractAtEntityEvent
import net.aquamine.server.event.player.interact.KryptonPlayerInteractWithEntityEvent
import net.aquamine.server.locale.DisconnectMessages
import net.aquamine.server.locale.MinecraftTranslationManager
import net.aquamine.server.network.NioConnection
import net.aquamine.server.network.PacketGrouping
import java.time.Duration

/**
 * This is the largest and most important of the four packet handlers, as the
 * play state is where the vast majority of packets reside.
 *
 * As mentioned above, this is the packet handler for the
 * [Play][net.aquamine.server.packet.PacketState.PLAY] state.
 *
 * This handles all supported inbound packets in the play state.
 */
class PlayPacketHandler(
    private val server: KryptonServer,
    private val connection: NioConnection,
    private val player: KryptonPlayer
) : TickablePacketHandler {

    private val chatTracker = player.chatTracker
    private var chatSession: RemoteChatSession? = null

    private var lastKeepAlive = System.currentTimeMillis()
    private var keepAliveChallenge = 0L
    private var pendingKeepAlive = false

    override fun tick() {
        val time = System.currentTimeMillis()
        if (time - lastKeepAlive < KEEP_ALIVE_INTERVAL) return
        if (pendingKeepAlive) {
            disconnect(DisconnectMessages.TIMEOUT)
            return
        }
        pendingKeepAlive = true
        lastKeepAlive = time
        keepAliveChallenge = time
        connection.send(PacketOutKeepAlive(keepAliveChallenge))
    }

    private fun disconnect(reason: Component) {
        connection.send(PacketOutDisconnect(reason))
        connection.disconnect(reason)
    }

    override fun onDisconnect(message: Component?) {
        if (message != null) {
            val translated = MinecraftTranslationManager.render(message)
            LOGGER.info("${player.name} was disconnected: ${PlainTextComponentSerializer.plainText().serialize(translated)}")
        }
        player.onDisconnect()
        server.playerManager.removePlayer(player)
    }

    fun handleSwingArm(packet: PacketInSwingArm) {
        val animation = when (packet.hand) {
            Hand.MAIN -> EntityAnimations.SWING_MAIN_ARM
            Hand.OFF -> EntityAnimations.SWING_OFFHAND
        }
        PacketGrouping.sendGroupedPacket(server, PacketOutAnimation(player.id, animation)) { it !== player }
    }

    fun handleChatCommand(packet: PacketInChatCommand) {
        if (!ChatUtil.isValidMessage(packet.command)) disconnect(DisconnectMessages.ILLEGAL_CHARACTERS)

        val event = server.eventNode.fire(KryptonCommandExecuteEvent(player, packet.command))
        if (!event.isAllowed()) return

        val command = event.result?.command ?: packet.command
        val lastSeen = chatTracker.handleChat(command, packet.timestamp, packet.lastSeenMessages) ?: return

        val source = player.createCommandSourceStack()
        val parsed = server.commandManager.parse(source, command)
        val arguments = try {
            chatTracker.collectSignedArguments(packet, SignableCommand.of(parsed), lastSeen)
        } catch (exception: SignedMessageChain.DecodeException) {
            handleMessageDecodeFailure(exception)
            return
        }

        server.commandManager.dispatch(source.withSigningContext(CommandSigningContext.SignedArguments(arguments)), command)
    }

    fun handleChat(packet: PacketInChat) {
        // Sanity check message content
        if (!ChatUtil.isValidMessage(packet.message)) disconnect(DisconnectMessages.ILLEGAL_CHARACTERS)

        // Fire the chat event
        val event = server.eventNode.fire(KryptonPlayerChatEvent(player, packet.message))
        if (!event.isAllowed()) return

        val lastSeen = chatTracker.handleChat(packet.message, packet.timestamp, packet.lastSeenMessages) ?: return
        val message = try {
            chatTracker.getSignedMessage(packet, lastSeen)
        } catch (exception: SignedMessageChain.DecodeException) {
            handleMessageDecodeFailure(exception)
            return
        }

        val unsignedContent = event.result?.message ?: message.decoratedContent()
        chatTracker.addMessageToChain(message, unsignedContent)
    }

    private fun handleMessageDecodeFailure(exception: SignedMessageChain.DecodeException) {
        if (exception.shouldDisconnect) {
            disconnect(exception.asComponent())
        } else {
            player.sendSystemMessage(exception.asComponent().color(NamedTextColor.RED))
        }
    }

    fun handleChatSessionUpdate(packet: PacketInChatSessionUpdate) {
        val session = packet.chatSession
        val currentKey = chatSession?.publicKey?.data
        val newKey = session.publicKey
        if (currentKey == newKey) return // Nothing to update
        if (currentKey != null && newKey.expiryTime.isBefore(currentKey.expiryTime)) {
            disconnect(PlayerPublicKey.EXPIRED_KEY)
            return
        }

        val newSession = try {
            session.validate(player.profile, SignatureValidator.YGGDRASIL, Duration.ZERO)
        } catch (exception: PlayerPublicKey.ValidationException) {
            LOGGER.error("Failed to validate public key!", exception)
            disconnect(exception.asComponent())
            return
        }
        chatSession = newSession
        chatTracker.resetPlayerChatState(newSession)
    }

    fun handleClientInformation(packet: PacketInClientInformation) {
        player.settings = KryptonPlayerSettings(
            Translator.parseLocale(packet.locale),
            packet.viewDistance.toInt(),
            packet.chatVisibility,
            packet.chatColors,
            KryptonSkinParts(packet.skinSettings.toInt()),
            packet.mainHand,
            packet.filterText,
            packet.allowsListing
        )
    }

    fun handleSetCreativeModeSlot(packet: PacketInSetCreativeModeSlot) {
        if (player.gameMode != GameMode.CREATIVE) return
        val item = packet.clickedItem
        val slot = packet.slot.toInt()
        val inValidRange = slot >= 1 && slot < KryptonPlayerInventory.SIZE
        val isValid = item.isEmpty() || item.meta.damage >= 0 && item.amount <= 64 && !item.isEmpty()
        if (inValidRange && isValid) player.inventory.setItem(slot, packet.clickedItem)
    }

    fun handlePlayerCommand(packet: PacketInPlayerCommand) {
        when (packet.action) {
            EntityAction.START_SNEAKING -> {
                if (!server.eventNode.fire(KryptonPlayerStartSneakingEvent(player)).isAllowed()) return
                player.isSneaking = true
            }
            EntityAction.STOP_SNEAKING -> {
                if (!server.eventNode.fire(KryptonPlayerStopSneakingEvent(player)).isAllowed()) return
                player.isSneaking = false
            }
            EntityAction.START_SPRINTING -> {
                if (!server.eventNode.fire(KryptonPlayerStartSprintingEvent(player)).isAllowed()) return
                player.isSprinting = true
            }
            EntityAction.STOP_SPRINTING -> {
                if (!server.eventNode.fire(KryptonPlayerStopSprintingEvent(player)).isAllowed()) return
                player.isSprinting = false
            }
            EntityAction.STOP_SLEEPING -> Unit // TODO: Sleeping
            EntityAction.START_HORSE_JUMP, EntityAction.STOP_HORSE_JUMP -> Unit // TODO: Horse jumping
            EntityAction.OPEN_INVENTORY -> Unit // TODO: Open vehicle inventory
            EntityAction.START_GLIDING -> if (!player.tryStartGliding()) player.stopGliding()
        }
    }

    fun handleSetHeldItem(packet: PacketInSetHeldItem) {
        val slot = packet.slot.toInt()
        if (slot < 0 || slot > 8) {
            LOGGER.warn("${player.profile.name} tried to change their held item slot to an invalid value!")
            return
        }
        player.inventory.heldSlot = slot
    }

    fun handleKeepAlive(packet: PacketInKeepAlive) {
        if (pendingKeepAlive && packet.id == keepAliveChallenge) {
            connection.updateLatency(lastKeepAlive)
            pendingKeepAlive = false
            PacketGrouping.sendGroupedPacket(server, PacketOutPlayerInfoUpdate(PacketOutPlayerInfoUpdate.Action.UPDATE_LATENCY, player))
            return
        }
        disconnect(DisconnectMessages.TIMEOUT)
    }

    fun handleAbilities(packet: PacketInAbilities) {
        player.abilities.flying = packet.isFlying && player.abilities.canFly
    }

    // TODO: This entire thing needs to be rewritten
    fun handleUseItemOn(packet: PacketInUseItemOn) {
        if (!player.canBuild) return // If they can't place blocks, they are irrelevant :)

        val world = player.world
        val position = packet.hitResult.position
        val state = world.getBlock(position)
        val face = packet.hitResult.direction
        val isInside = packet.hitResult.isInside
        val event = server.eventNode.fire(KryptonPlayerPlaceBlockEvent(player, state, packet.hand, position, face, isInside))
        if (!event.isAllowed()) return

        val chunk = world.chunkManager.getChunk(ChunkPos.forEntityPosition(player.position)) ?: return
        val existingBlock = chunk.getBlock(position)
        if (!existingBlock.eq(KryptonBlocks.AIR)) return
        chunk.setBlock(position, KryptonRegistries.BLOCK.get(player.inventory.mainHand.type.key()).defaultState, false)
    }

    fun handlePlayerAction(packet: PacketInPlayerAction) {
        when (packet.action) {
            PlayerAction.START_DIGGING, PlayerAction.FINISH_DIGGING, PlayerAction.CANCEL_DIGGING -> player.gameModeSystem.handleBlockBreak(packet)
            PlayerAction.RELEASE_USE_ITEM -> {
                val handler = player.inventory.getItem(player.inventory.heldSlot).type.handler()
                if (handler is ItemTimedHandler) handler.finishUse(player, player.hand)
            }
            else -> Unit
        }
    }

    fun handleUseItem(packet: PacketInUseItem) {
        player.inventory.getHeldItem(packet.hand).type.handler().use(player, packet.hand)
    }

    fun handlePlayerInput(packet: PacketInPlayerInput) {
        // TODO: Handle steering here
        if (packet.isSneaking()) player.ejectVehicle()
    }

    fun handleInteract(packet: PacketInInteract) {
        val target = player.world.entityManager.getById(packet.entityId) ?: return
        if (player.position.distanceSquared(target.position) >= INTERACTION_RANGE_SQUARED) return

        when (packet.action) {
            is PacketInInteract.InteractAction -> onInteract(target, packet.action.hand)
            is PacketInInteract.AttackAction -> onAttack(target)
            is PacketInInteract.InteractAtAction -> onInteractAt(target, packet.action.hand, packet.action.x, packet.action.y, packet.action.z)
        }
    }

    private fun onInteract(target: KryptonEntity, hand: Hand) {
        val event = server.eventNode.fire(KryptonPlayerInteractWithEntityEvent(player, target, hand))
        if (!event.isAllowed()) return

        // TODO: Re-implement interactions and call a handler here
    }

    private fun onInteractAt(target: KryptonEntity, hand: Hand, x: Float, y: Float, z: Float) {
        val clickedPosition = Vec3d(x.toDouble(), y.toDouble(), z.toDouble())
        val event = server.eventNode.fire(KryptonPlayerInteractAtEntityEvent(player, target, hand, clickedPosition))
        if (!event.isAllowed()) return

        // TODO: Re-implement interactions and call a handler here
    }

    private fun onAttack(target: KryptonEntity) {
        val event = server.eventNode.fire(KryptonPlayerAttackEntityEvent(player, target))
        if (!event.isAllowed()) return

        // TODO: Re-implement interactions and call a handler here
    }

    fun handlePlayerPosition(packet: PacketInSetPlayerPosition) {
        handlePositionRotationUpdate(player.position.withCoordinates(packet.x, packet.y, packet.z), packet.onGround)
    }

    fun handlePlayerRotation(packet: PacketInSetPlayerRotation) {
        handlePositionRotationUpdate(player.position.withRotation(packet.yaw, packet.pitch), packet.onGround)
    }

    fun handlePlayerPositionAndRotation(packet: PacketInSetPlayerPositionAndRotation) {
        handlePositionRotationUpdate(packet.position(), packet.onGround)
    }

    private fun handlePositionRotationUpdate(newPosition: Position, onGround: Boolean) {
        val oldPosition = player.position
        if (oldPosition == newPosition) return // Position hasn't changed, no need to do anything

        // TODO: Figure out if we should make an entity move event and move this there, so the event is called on teleportation too
        val event = server.eventNode.fire(KryptonPlayerMoveEvent(player, oldPosition, newPosition))
        if (!event.isAllowed()) return

        player.isOnGround = onGround
        player.teleport(newPosition)
    }

    fun handlePluginMessage(packet: PacketInPluginMessage) {
        server.eventNode.fire(KryptonPluginMessageReceivedEvent(player, packet.channel, packet.data))
    }

    fun handleCommandSuggestionsRequest(packet: PacketInCommandSuggestionsRequest) {
        val reader = StringReader(packet.command)
        if (reader.canRead() && reader.peek() == '/') reader.skip()
        val parseResults = server.commandManager.parse(player.createCommandSourceStack(), reader)
        server.commandManager.suggest(parseResults)
            .thenAcceptAsync { connection.send(PacketOutCommandSuggestionsResponse(packet.id, it)) }
    }

    fun handleClientCommand(packet: PacketInClientCommand) {
        when (packet.action) {
            PacketInClientCommand.Action.PERFORM_RESPAWN -> Unit // TODO
            PacketInClientCommand.Action.REQUEST_STATS -> player.statisticsTracker.sendUpdated()
        }
    }

    fun handleEntityTagQuery(packet: PacketInQueryEntityTag) {
        if (!player.hasPermission(KryptonPermission.ENTITY_QUERY.node)) return
        val entity = player.world.entityManager.getById(packet.entityId) ?: return
        connection.send(PacketOutTagQueryResponse(packet.transactionId, entity.saveWithPassengers().build()))
    }

    fun handleResourcePack(packet: PacketInResourcePack) {
        if (packet.status == ResourcePack.Status.DECLINED && server.config.server.resourcePack.forced) {
            disconnect(DisconnectMessages.REQUIRED_TEXTURE_PROMPT)
            return
        }
        server.eventNode.fire(KryptonPlayerResourcePackStatusEvent(player, packet.status))
    }

    companion object {

        private const val INTERACTION_RANGE_SQUARED = 6.0 * 6.0
        private const val KEEP_ALIVE_INTERVAL = 15000L
        private val LOGGER = LogManager.getLogger()
    }
}

package net.aquamine.server.entity.system

import org.apache.logging.log4j.LogManager
import net.aquamine.api.event.player.PlayerChangeGameModeEvent
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.GameMode
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.event.player.AquaPlayerChangeGameModeEvent
import net.aquamine.server.item.handler
import net.aquamine.server.network.PacketGrouping
import net.aquamine.server.packet.`in`.play.PacketInPlayerAction
import net.aquamine.server.packet.out.play.PacketOutAbilities
import net.aquamine.server.packet.out.play.PacketOutBlockUpdate
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate.Action
import net.aquamine.server.util.enumhelper.GameModes
import net.aquamine.server.world.block.state.AquaBlockState

// TODO: Most of this logic is from vanilla, and we can probably get rid of it and replace it with something better.
class PlayerGameModeSystem(private val player: AquaPlayer) {

    private var gameMode = GameMode.SURVIVAL
    private var previousGameMode: GameMode? = null

    private var currentTick = 0
    private var isDestroying = false
    private var startDestroyProgress = 0
    private var destroyPos = Vec3i.ZERO
    private var hasDelayedDestroy = false
    private var delayedDestroyPos = Vec3i.ZERO
    private var delayedTickStart = 0
    private var lastSentState = -1

    fun gameMode(): GameMode = gameMode

    fun previousGameMode(): GameMode? = previousGameMode

    fun tick() {
        currentTick++
        if (hasDelayedDestroy) {
            val block = player.world.getBlock(delayedDestroyPos)
            if (block.isAir()) {
                hasDelayedDestroy = false
                return
            }
            if (incrementDestroyProgress(block, delayedDestroyPos, delayedTickStart) >= 1F) {
                hasDelayedDestroy = false
                destroyBlock(delayedDestroyPos)
            }
            return
        }
        if (isDestroying) {
            val block = player.world.getBlock(destroyPos)
            if (!block.isAir()) {
                incrementDestroyProgress(block, destroyPos, startDestroyProgress)
                return
            }
            player.world.broadcastBlockDestroyProgress(player.id, destroyPos, -1)
            lastSentState = -1
            isDestroying = false
        }
    }

    fun changeGameMode(mode: GameMode): PlayerChangeGameModeEvent? {
        if (mode == gameMode) return null

        val event = player.server.eventNode.fire(AquaPlayerChangeGameModeEvent(player, gameMode, mode))
        if (!event.isAllowed()) return event

        setGameMode(event.result?.newGameMode ?: mode, gameMode)
        sendGameModeUpdate()
        return event
    }

    fun setGameMode(mode: GameMode, previousMode: GameMode?) {
        previousGameMode = previousMode
        gameMode = mode
        GameModes.updatePlayerAbilities(mode, player.abilities)
        player.isInvisible = mode == GameMode.SPECTATOR
    }

    private fun sendGameModeUpdate() {
        player.connection.send(PacketOutAbilities.create(player.abilities))
        PacketGrouping.sendGroupedPacket(player.server, PacketOutPlayerInfoUpdate(Action.UPDATE_GAME_MODE, player))
    }

    fun handleBlockBreak(packet: PacketInPlayerAction) {
        handleBlockBreak(packet.position, packet.action, player.world.maximumBuildHeight())
    }

    private fun handleBlockBreak(pos: Vec3i, action: PacketInPlayerAction.Action, maxHeight: Int) {
        val dx = player.position.x - (pos.x.toDouble() + 0.5)
        val dy = player.position.y - (pos.y.toDouble() + 0.5) + 1.5
        val dz = player.position.z - (pos.z.toDouble() + 0.5)
        val squareDistance = dx * dx + dy * dy + dz * dz
        if (squareDistance > MAXIMUM_DISTANCE_FROM_BLOCK) {
            logBlockBreakUpdate(pos, false, "cannot break blocks further than 6 blocks away")
            return
        }
        if (pos.y >= maxHeight) {
            logBlockBreakUpdate(pos, false, "cannot break blocks above $maxHeight")
            return
        }
        when (action) {
            PacketInPlayerAction.Action.START_DIGGING -> {
                if (!player.world.canInteract(player, pos.x, pos.z)) {
                    sendSpawnProtectionMessage()
                    logBlockBreakUpdate(pos, false, "cannot break blocks in spawn protection")
                    return
                }
                if (player.gameMode == GameMode.CREATIVE) {
                    destroyAndAcknowledge(pos, "creative block break")
                    return
                }
                if (player.isBlockActionRestricted(pos)) {
                    player.connection.send(PacketOutBlockUpdate(pos, player.world.getBlock(pos)))
                    logBlockBreakUpdate(pos, false, "cannot break blocks while in this state!")
                    return
                }
                startDestroyProgress = currentTick
                var destroyProgress = 1F
                val block = player.world.getBlock(pos)
                if (!block.isAir()) {
                    block.attack(player.world, pos, player)
                    destroyProgress = block.getDestroyProgress(player, player.world, pos)
                }

                if (!block.isAir() && destroyProgress >= 1F) {
                    destroyAndAcknowledge(pos, "instant mine")
                    return
                }
                if (isDestroying) {
                    player.connection.send(PacketOutBlockUpdate(destroyPos, player.world.getBlock(destroyPos)))
                    val reason = "aborted because another block is already being destroyed (client instantly mined block, we didn't like that)"
                    logBlockBreakUpdate(pos, false, reason)
                }
                isDestroying = true
                destroyPos = pos
                val state = (destroyProgress * 10F).toInt()
                player.world.broadcastBlockDestroyProgress(player.id, pos, state)
                logBlockBreakUpdate(pos, true, "started breaking block")
                lastSentState = state
            }
            PacketInPlayerAction.Action.FINISH_DIGGING -> {
                if (pos != destroyPos) return
                val tickDifference = currentTick - startDestroyProgress
                val block = player.world.getBlock(pos)
                if (block.isAir()) return
                val destroyProgress = block.getDestroyProgress(player, player.world, pos) * (tickDifference + 1).toFloat()
                if (destroyProgress >= MAXIMUM_DESTROY_PROGRESS) {
                    isDestroying = false
                    player.world.broadcastBlockDestroyProgress(player.id, pos, -1)
                    destroyAndAcknowledge(pos, "block broke")
                    return
                }
                if (!hasDelayedDestroy) {
                    isDestroying = false
                    hasDelayedDestroy = true
                    delayedDestroyPos = pos
                    delayedTickStart = startDestroyProgress
                }
                logBlockBreakUpdate(pos, true, "stopped breaking block")
            }
            PacketInPlayerAction.Action.CANCEL_DIGGING -> {
                isDestroying = false
                if (pos != destroyPos) {
                    LOGGER.warn("Mismatched destroy position! Expected $destroyPos and got $pos!")
                    player.world.broadcastBlockDestroyProgress(player.id, destroyPos, -1)
                    logBlockBreakUpdate(pos, true, "aborted mismatched block breaking")
                }
                player.world.broadcastBlockDestroyProgress(player.id, pos, -1)
                logBlockBreakUpdate(pos, true, "aborted block breaking")
            }
            else -> Unit
        }
    }

    private fun sendSpawnProtectionMessage() {
        if (player.server.config.world.sendSpawnProtectionMessage) player.sendActionBar(player.server.config.world.spawnProtectionMessage)
    }

    private fun incrementDestroyProgress(block: AquaBlockState, pos: Vec3i, startTick: Int): Float {
        val tickDifference = currentTick - startTick
        val progress = block.getDestroyProgress(player, player.world, pos) * (tickDifference + 1).toFloat()
        val state = (progress * 10F).toInt()
        if (state != lastSentState) {
            player.world.broadcastBlockDestroyProgress(player.id, pos, state)
            lastSentState = state
        }
        return progress
    }

    private fun destroyAndAcknowledge(pos: Vec3i, message: String) {
        val success = destroyBlock(pos)
        logBlockBreakUpdate(pos, success, message)
        if (success) player.connection.send(PacketOutBlockUpdate(pos, player.world.getBlock(pos)))
    }

    private fun destroyBlock(pos: Vec3i): Boolean {
        val state = player.world.getBlock(pos)
        val block = state.block
        if (!player.inventory.mainHand.type.handler().canAttackBlock(player, player.world, state, pos)) return false

        // Check some conditions first
        // TODO: We need the check for instance of GameMasterBlock for this to not break other things
//        if (!player.canUseGameMasterBlocks()) {
//            player.world.sendBlockUpdated(pos, state)
//            return false
//        }
        if (player.isBlockActionRestricted(pos)) return false

        // Call pre-destroy, try and remove the block, and if we changed the block, call destroy
        block.handler.playerWillDestroy(player.world, pos, state, player)
        val hasChanged = player.world.removeBlock(pos, false)
        if (hasChanged) block.handler.destroy(player.world, pos, state)

        if (player.gameMode == GameMode.CREATIVE) return true // We're done, since the bit after this is for mining, which doesn't happen in creative
        val item = player.inventory.mainHand
        val hasCorrectTool = player.hasCorrectTool(state)
        item.type.handler().mineBlock(player, item, player.world, state, pos)
        if (hasChanged && hasCorrectTool) block.handler.playerDestroy(player.world, state, pos, null, player, item)
        return true
    }

    private fun logBlockBreakUpdate(position: Vec3i, success: Boolean, message: String) {
        if (success) {
            LOGGER.debug("Player ${player.profile.name} updated block break progress at $position: $message")
        } else {
            LOGGER.debug("Player ${player.profile.name} attempted to break block at $position but could not. Reason: $message")
        }
    }

    companion object {

        private const val MAXIMUM_DISTANCE_FROM_BLOCK = 6.0 * 6.0
        private const val MAXIMUM_DESTROY_PROGRESS = 0.7F
        private val LOGGER = LogManager.getLogger()
    }
}

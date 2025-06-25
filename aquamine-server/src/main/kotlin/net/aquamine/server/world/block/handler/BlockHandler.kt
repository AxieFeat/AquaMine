package net.aquamine.server.world.block.handler

import net.aquamine.api.entity.Hand
import net.aquamine.api.statistic.StatisticTypes
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.biome.Precipitation
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.KryptonLivingEntity
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.entity.projectile.KryptonProjectile
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.util.InteractionResult
import net.aquamine.server.util.hit.BlockHitResult
import net.aquamine.server.util.math.Mirror
import net.aquamine.server.util.math.Rotation
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.WorldEvents
import net.aquamine.server.world.block.KryptonBlock
import net.aquamine.server.world.block.entity.KryptonBlockEntity
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.components.BlockGetter
import net.aquamine.server.world.components.WorldAccessor

// TODO: Perhaps this could eventually be replaced with events?
interface BlockHandler {

    fun isApplicableBlockType(name: String): Boolean

    fun getDestroyProgress(state: KryptonBlockState, player: KryptonPlayer, world: BlockGetter, position: Vec3i): Float {
        val speed = state.block.properties.destroyTime
        if (speed == -1F) return 0F
        val correctToolBonus = if (player.hasCorrectTool(state)) 30 else 100
        return player.getDestroySpeed(state) / speed / correctToolBonus
    }

    fun onPlace(state: KryptonBlockState, world: KryptonWorld, pos: Vec3i, oldState: KryptonBlockState, isMoving: Boolean) {
        // Do nothing by default
    }

    fun setPlacedBy(world: KryptonWorld, pos: Vec3i, state: KryptonBlockState, placer: KryptonLivingEntity?, heldItem: KryptonItemStack) {
        // Do nothing by default
    }

    fun destroy(world: WorldAccessor, pos: Vec3i, state: KryptonBlockState) {
        // Do nothing by default
    }

    fun playerWillDestroy(world: KryptonWorld, pos: Vec3i, state: KryptonBlockState, player: KryptonPlayer) {
        spawnDestroyParticles(world, player, pos, state)
        // TODO: Anger nearby piglins if state is guarded by piglins and trigger game event for sculk sensors
    }

    fun playerDestroy(world: KryptonWorld, state: KryptonBlockState, pos: Vec3i, blockEntity: KryptonBlockEntity?, destroyer: KryptonPlayer,
                      heldItem: KryptonItemStack) {
        destroyer.statisticsTracker.incrementStatistic(StatisticTypes.BLOCK_MINED.get().getStatistic(state.block))
        // TODO: Cause exhaustion and drop items
    }

    fun spawnDestroyParticles(world: KryptonWorld, player: KryptonPlayer, pos: Vec3i, state: KryptonBlockState) {
        world.worldEvent(pos, WorldEvents.DESTROY_BLOCK, KryptonBlock.idOf(state), player)
    }

    fun onRemove(state: KryptonBlockState, world: KryptonWorld, pos: Vec3i, newState: KryptonBlockState, isMoving: Boolean) {
        if (state.hasBlockEntity() && !state.eq(newState.block)) {
            // TODO: Remove block entity from world
        }
    }

    fun spawnAfterBreak(state: KryptonBlockState, world: KryptonWorld, pos: Vec3i, item: KryptonItemStack, dropItems: Boolean) {
        // Do nothing by default
    }

    fun use(state: KryptonBlockState, world: BlockGetter, pos: Vec3i, user: KryptonPlayer, usedHand: Hand,
            hitResult: BlockHitResult): InteractionResult {
        return InteractionResult.PASS
    }

    fun attack(state: KryptonBlockState, world: KryptonWorld, pos: Vec3i, player: KryptonPlayer) {
        // Do nothing by default
    }

    fun triggerEvent(state: KryptonBlockState, world: KryptonWorld, pos: Vec3i, id: Int, parameter: Int): Boolean {
        return false
    }

    fun updateIndirectNeighbourShapes(state: KryptonBlockState, world: BlockGetter, pos: Vec3i, flags: Int, recursionLeft: Int) {
        // Do nothing by default
    }

    fun updateShape(world: BlockGetter, state: KryptonBlockState, currentPos: Vec3i, neighbour: KryptonBlockState, neighbourPos: Vec3i,
                    direction: Direction): KryptonBlockState {
        return state
    }

    fun entityInside(state: KryptonBlockState, world: KryptonWorld, pos: Vec3i, entity: KryptonEntity) {
        // Do nothing by default
    }

    fun onProjectileHit(world: KryptonWorld, state: KryptonBlockState, hitResult: BlockHitResult, projectile: KryptonProjectile) {
        // Do nothing by default
    }

    fun rotate(state: KryptonBlockState, rotation: Rotation): KryptonBlockState {
        return state
    }

    fun mirror(state: KryptonBlockState, mirror: Mirror): KryptonBlockState {
        return state
    }

    fun neighbourChanged(state: KryptonBlockState, world: KryptonWorld, pos: Vec3i, block: KryptonBlock, neighbourPos: Vec3i,
                         moving: Boolean) {
        // Do nothing by default
    }

    fun stepOn(world: KryptonWorld, pos: Vec3i, state: KryptonBlockState, entity: KryptonEntity) {
        // Do nothing by default
    }

    fun fallOn(world: KryptonWorld, state: KryptonBlockState, pos: Vec3i, entity: KryptonEntity, fallDistance: Float) {
        // TODO: Cause fall damage to entity
    }

    fun handlePrecipitation(state: KryptonBlockState, world: KryptonWorld, pos: Vec3i, precipitation: Precipitation) {
        // Do nothing by default
    }
}

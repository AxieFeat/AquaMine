package net.aquamine.server.world.block.handler

import net.aquamine.api.entity.Hand
import net.aquamine.api.statistic.StatisticTypes
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.biome.Precipitation
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.entity.projectile.AquaProjectile
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.util.InteractionResult
import net.aquamine.server.util.hit.BlockHitResult
import net.aquamine.server.util.math.Mirror
import net.aquamine.server.util.math.Rotation
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.WorldEvents
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.entity.AquaBlockEntity
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.components.BlockGetter
import net.aquamine.server.world.components.WorldAccessor

// TODO: Perhaps this could eventually be replaced with events?
interface BlockHandler {

    fun isApplicableBlockType(name: String): Boolean

    fun getDestroyProgress(state: AquaBlockState, player: AquaPlayer, world: BlockGetter, position: Vec3i): Float {
        val speed = state.block.properties.destroyTime
        if (speed == -1F) return 0F
        val correctToolBonus = if (player.hasCorrectTool(state)) 30 else 100
        return player.getDestroySpeed(state) / speed / correctToolBonus
    }

    fun onPlace(state: AquaBlockState, world: AquaWorld, pos: Vec3i, oldState: AquaBlockState, isMoving: Boolean) {
        // Do nothing by default
    }

    fun setPlacedBy(world: AquaWorld, pos: Vec3i, state: AquaBlockState, placer: AquaLivingEntity?, heldItem: AquaItemStack) {
        // Do nothing by default
    }

    fun destroy(world: WorldAccessor, pos: Vec3i, state: AquaBlockState) {
        // Do nothing by default
    }

    fun playerWillDestroy(world: AquaWorld, pos: Vec3i, state: AquaBlockState, player: AquaPlayer) {
        spawnDestroyParticles(world, player, pos, state)
        // TODO: Anger nearby piglins if state is guarded by piglins and trigger game event for sculk sensors
    }

    fun playerDestroy(world: AquaWorld, state: AquaBlockState, pos: Vec3i, blockEntity: AquaBlockEntity?, destroyer: AquaPlayer,
                      heldItem: AquaItemStack) {
        destroyer.statisticsTracker.incrementStatistic(StatisticTypes.BLOCK_MINED.get().getStatistic(state.block))
        // TODO: Cause exhaustion and drop items
    }

    fun spawnDestroyParticles(world: AquaWorld, player: AquaPlayer, pos: Vec3i, state: AquaBlockState) {
        world.worldEvent(pos, WorldEvents.DESTROY_BLOCK, AquaBlock.idOf(state), player)
    }

    fun onRemove(state: AquaBlockState, world: AquaWorld, pos: Vec3i, newState: AquaBlockState, isMoving: Boolean) {
        if (state.hasBlockEntity() && !state.eq(newState.block)) {
            // TODO: Remove block entity from world
        }
    }

    fun spawnAfterBreak(state: AquaBlockState, world: AquaWorld, pos: Vec3i, item: AquaItemStack, dropItems: Boolean) {
        // Do nothing by default
    }

    fun use(state: AquaBlockState, world: BlockGetter, pos: Vec3i, user: AquaPlayer, usedHand: Hand,
            hitResult: BlockHitResult): InteractionResult {
        return InteractionResult.PASS
    }

    fun attack(state: AquaBlockState, world: AquaWorld, pos: Vec3i, player: AquaPlayer) {
        // Do nothing by default
    }

    fun triggerEvent(state: AquaBlockState, world: AquaWorld, pos: Vec3i, id: Int, parameter: Int): Boolean {
        return false
    }

    fun updateIndirectNeighbourShapes(state: AquaBlockState, world: BlockGetter, pos: Vec3i, flags: Int, recursionLeft: Int) {
        // Do nothing by default
    }

    fun updateShape(world: BlockGetter, state: AquaBlockState, currentPos: Vec3i, neighbour: AquaBlockState, neighbourPos: Vec3i,
                    direction: Direction): AquaBlockState {
        return state
    }

    fun entityInside(state: AquaBlockState, world: AquaWorld, pos: Vec3i, entity: AquaEntity) {
        // Do nothing by default
    }

    fun onProjectileHit(world: AquaWorld, state: AquaBlockState, hitResult: BlockHitResult, projectile: AquaProjectile) {
        // Do nothing by default
    }

    fun rotate(state: AquaBlockState, rotation: Rotation): AquaBlockState {
        return state
    }

    fun mirror(state: AquaBlockState, mirror: Mirror): AquaBlockState {
        return state
    }

    fun neighbourChanged(state: AquaBlockState, world: AquaWorld, pos: Vec3i, block: AquaBlock, neighbourPos: Vec3i,
                         moving: Boolean) {
        // Do nothing by default
    }

    fun stepOn(world: AquaWorld, pos: Vec3i, state: AquaBlockState, entity: AquaEntity) {
        // Do nothing by default
    }

    fun fallOn(world: AquaWorld, state: AquaBlockState, pos: Vec3i, entity: AquaEntity, fallDistance: Float) {
        // TODO: Cause fall damage to entity
    }

    fun handlePrecipitation(state: AquaBlockState, world: AquaWorld, pos: Vec3i, precipitation: Precipitation) {
        // Do nothing by default
    }
}

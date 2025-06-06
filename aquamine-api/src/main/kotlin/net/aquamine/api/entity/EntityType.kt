package net.aquamine.api.entity

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import net.kyori.adventure.translation.Translatable
import net.aquamine.api.block.Block
import net.aquamine.api.block.BlockState

/**
 * A type of entity.
 *
 * @param T the type of entity.
 */
@CataloguedBy(EntityTypes::class)
@ImmutableType
interface EntityType<out T : Entity> : Keyed, Translatable {

    /**
     * The category entities of this type are part of.
     */
    val category: EntityCategory

    /**
     * If this entity type can be summoned, with the /summon command, or by
     * spawning the entity through [net.aquamine.api.world.World.spawnEntity].
     */
    val isSummonable: Boolean

    /**
     * If entities of this type are immune to all types of fire damage.
     */
    val isImmuneToFire: Boolean

    /**
     * The radius of the circle in which the client will track the movement of
     * entities of this type.
     */
    val clientTrackingRange: Int

    /**
     * The interval between when entities of this type will be updated.
     */
    val updateInterval: Int

    /**
     * The base width of entities of this type.
     */
    val width: Float

    /**
     * The base height of entities of this type.
     */
    val height: Float

    /**
     * All blocks that entities of this type will not take damage from.
     */
    val immuneTo: Set<Block>

    /**
     * The identifier for the loot table that entities of this type will use to
     * determine what drops they will have when they are killed.
     */
    // TODO: Ideally, replace this with something better when loot tables are implemented
    val lootTable: Key

    /**
     * Checks if entities of this type are immune (they will not be damaged by)
     * the given [block].
     *
     * @param block The block to check.
     *
     * @return `true` if entities are immune to the block, `false` otherwise.
     */
    fun isImmuneTo(block: BlockState): Boolean
}

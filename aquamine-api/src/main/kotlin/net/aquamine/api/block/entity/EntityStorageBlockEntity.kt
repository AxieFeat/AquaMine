package net.aquamine.api.block.entity

import net.aquamine.api.entity.Entity

/**
 * A block entity that stores entities within it.
 */
interface EntityStorageBlockEntity<T : Entity> : BlockEntity {

    /**
     * The number of entities contained within this block entity.
     */
    val entityCount: Int

    /**
     * The maximum number of entities that can be stored within this block
     * entity.
     */
    var maximumEntities: Int

    /**
     * Gets whether this block entity is full of entities.
     *
     * @return whether this block entity is full
     */
    fun isFull(): Boolean = entityCount >= maximumEntities

    /**
     * Releases all the entities contained within this block entity.
     *
     * @return the released entities
     */
    fun releaseEntities(): List<T>

    /**
     * Adds the given [entity] to the entities contained within this block
     * entity.
     *
     * @param entity the entity
     */
    fun addEntity(entity: T)

    /**
     * Clears all the entities out of this block entity.
     */
    fun clearEntities()
}

package net.aquamine.server.entity.tracking

import net.aquamine.api.entity.Entity
import net.aquamine.api.util.Position
import net.aquamine.server.coordinate.ChunkPos
import net.aquamine.server.entity.KryptonEntity
import java.util.function.Consumer
import java.util.function.Predicate

/**
 * The entity tracker is responsible for tracking entities in the world. It keeps track of what position
 * and chunk every entity is in, and what entities can see what other entities.
 *
 * This is primarily based off of Minestom's entity tracking system.
 */
interface EntityTracker {

    /**
     * Called when an entity is added to the world.
     */
    fun <E : KryptonEntity> add(entity: KryptonEntity, position: Position, target: EntityTypeTarget<E>, callback: EntityViewCallback<E>?)

    /**
     * Called when an entity is removed from the world.
     */
    fun <E : KryptonEntity> remove(entity: KryptonEntity, target: EntityTypeTarget<E>, callback: EntityViewCallback<E>?)

    /**
     * Called when an entity moves. This will both update the position and update the chunk the entity is in,
     * if the entity has moved chunks.
     */
    fun <E : KryptonEntity> onMove(entity: KryptonEntity, newPosition: Position, target: EntityTypeTarget<E>, callback: EntityViewCallback<E>?)

    /**
     * Gets all entities matching the target in the chunk at the given position.
     */
    fun <E : KryptonEntity> entitiesInChunkOfType(position: ChunkPos, target: EntityTypeTarget<E>): Collection<E>

    /**
     * Gets all entities in the chunk at the given position of the given type matching the predicate, if given.
     */
    fun <E : Entity> entitiesInChunkOfType(position: ChunkPos, type: Class<E>, predicate: Predicate<E>?): Collection<E>

    /**
     * Gets all entities in the chunk at the given position.
     */
    fun entitiesInChunk(position: ChunkPos): Collection<KryptonEntity> = entitiesInChunkOfType(position, EntityTypeTarget.ENTITIES)

    /**
     * Gets all entities within range of the position matching the target, and calls the query consumer on them.
     */
    fun <E : KryptonEntity> nearbyEntitiesOfType(position: Position, range: Double, target: EntityTypeTarget<E>, query: Consumer<E>)

    /**
     * Gets all entities within range of the position, and calls the query consumer on them.
     */
    fun nearbyEntities(position: Position, range: Double, query: Consumer<KryptonEntity>) {
        nearbyEntitiesOfType(position, range, EntityTypeTarget.ENTITIES, query)
    }

    /**
     * Gets all entities within range of the position, returning all found entities.
     */
    fun nearbyEntities(position: Position, range: Double): Collection<KryptonEntity> {
        val entities = ArrayList<KryptonEntity>()
        nearbyEntities(position, range) { entities.add(it) }
        return entities
    }

    /**
     * Gets all entities matching the target.
     */
    fun <E : KryptonEntity> entitiesOfType(target: EntityTypeTarget<E>): Set<E>

    /**
     * Gets all entities of the given type matching the predicate, if given.
     */
    fun <E : Entity> entitiesOfType(type: Class<E>, predicate: Predicate<E>?): Collection<E>

    /**
     * Gets all entities tracked by the tracker.
     *
     * This will NOT include players!
     */
    fun entities(): Set<KryptonEntity> = entitiesOfType(EntityTypeTarget.ENTITIES)
}

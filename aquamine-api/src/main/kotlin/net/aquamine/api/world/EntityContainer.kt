package net.aquamine.api.world

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.player.Player
import java.util.function.Predicate

/**
 * Something that contains entities.
 */
interface EntityContainer {

    /**
     * All entities contained within this container.
     */
    val entities: Collection<Entity>

    /**
     * All players contained within this container.
     */
    val players: Collection<Player>

    /**
     * Gets all entities of the given [type] contained within this container.
     *
     * @param E The entity type.
     * @param type The entity type.
     *
     * @return All entities of the given type.
     */
    fun <E : Entity> getEntitiesOfType(type: Class<E>): Collection<E>

    /**
     * Gets all entities of the given [type] matching the given [predicate]
     * contained within this container.
     *
     * @param E The entity type.
     * @param type The entity type.
     * @param predicate The predicate to filter entities with.
     *
     * @return The entities.
     */
    fun <E : Entity> getEntitiesOfType(type: Class<E>, predicate: Predicate<E>): Collection<E>
}

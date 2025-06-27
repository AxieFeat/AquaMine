package net.aquamine.server.entity.tracking

import net.aquamine.api.util.Position
import net.aquamine.server.entity.AquaEntity

/**
 * A callback for updates to an entity's view tracking.
 *
 * This is used to decouple the logic for entity viewing from that of entity tracking.
 */
interface EntityViewCallback<E : AquaEntity> {

    /**
     * Called when the entity comes in to view.
     */
    fun add(entity: E)

    /**
     * Called when the entity goes out of view.
     */
    fun remove(entity: E)

    /**
     * Called when the entity moves, and so the reference has changed.
     *
     * This is used by the viewing engine so it knows when to update its tracked location.
     */
    fun referenceUpdate(position: Position, tracker: EntityTracker) {
        // Do nothing by default
    }
}

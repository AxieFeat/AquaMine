package net.aquamine.api.entity.ai.goal

import net.aquamine.api.entity.Entity

/**
 * A finder for targets for an entity.
 */
fun interface TargetFinder {

    /**
     * Finds a target for the entity.
     *
     * This must always return null if no target was found.
     *
     * @return The target, or null if no target was found.
     */
    fun findTarget(): Entity?

    /**
     * If this finder is valid and can be used to find targets.
     *
     * @return `true` if this finder can be used.
     */
    fun canUse(): Boolean {
        return true
    }

    /**
     * Called when this finder starts being used to find targets.
     *
     * This allows the finder to set up any state it may need before it is
     * asked to find targets.
     */
    fun onStartUsing() {
        // Do nothing by default
    }

    /**
     * If this finder should be removed from the finders that can be used to
     * find targets, indicating it is no longer valid and should not be used.
     *
     * @return `true` if this finder should be removed.
     */
    fun shouldRemove(): Boolean {
        return false
    }

    /**
     * Called when this finder stops being used to find targets.
     *
     * This allows the finder to clean up any state it may have.
     */
    fun onRemove() {
        // Do nothing by default
    }
}

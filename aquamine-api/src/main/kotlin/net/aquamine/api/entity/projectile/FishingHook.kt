package net.aquamine.api.entity.projectile

import net.aquamine.api.entity.Entity

/**
 * A fishing hook on the end of a fishing rod.
 */
interface FishingHook : Projectile {

    /**
     * The entity that is currently hooked by this hook or null if this hook
     * is not hooking an entity.
     */
    val hooked: Entity?

    /**
     * The current state of this hook.
     */
    val state: State

    /**
     * If the hooked entity is biting the hook, meaning it can be caught.
     *
     * @return `true` if the hooked entity is biting the hook.
     */
    fun isBiting(): Boolean

    /**
     * The current state of a fishing hook.
     */
    enum class State {

        /**
         * The rod has been cast and the hook is flying in the air across the
         * water.
         */
        FLYING,

        /**
         * The hook has hooked an entity.
         */
        HOOKED,

        /**
         * The hook is bobbing up and down waiting for an entity.
         */
        BOBBING;
    }
}

package net.aquamine.api.entity.ai.goal

/**
 * A goal that an entity can achieve.
 */
interface Goal {

    /**
     * Whether the entity can use this goal.
     *
     * @return `true` if this goal can be used.
     */
    fun canUse(): Boolean

    /**
     * Starts this goal.
     */
    fun start()

    /**
     * Whether this goal should be stopped if it is currently running.
     *
     * @return `true` if this goal should be stopped.
     */
    fun shouldStop(): Boolean

    /**
     * Stops this goal.
     */
    fun stop()

    /**
     * Called when the entity this goal is registered to is ticked.
     *
     * @param time The time of the tick.
     */
    fun tick(time: Long)
}

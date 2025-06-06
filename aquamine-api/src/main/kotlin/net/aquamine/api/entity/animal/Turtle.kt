package net.aquamine.api.entity.animal

import net.aquamine.api.util.Vec3i

/**
 * A turtle.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface Turtle : Animal {

    /**
     * If this turtle currently has an egg.
     */
    @get:JvmName("hasEgg")
    var hasEgg: Boolean

    /**
     * If this turtle is currently laying an egg.
     */
    var isLayingEgg: Boolean

    /**
     * If this turtle has called it a day and is heading back [home].
     */
    var isGoingHome: Boolean

    /**
     * If this turtle is currently traveling to its [destination].
     */
    var isTravelling: Boolean

    /**
     * The location of this turtle's home.
     */
    var home: Vec3i

    /**
     * The current destination of this turtle.
     */
    var destination: Vec3i
}

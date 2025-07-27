package net.aquamine.api.block.meta

/**
 * Indicates how a wall this property is applied to connects to an adjacent
 * wall or block on one of its faces.
 */
// TODO: Find out what low and tall are
@Suppress("UndocumentedPublicProperty")
enum class WallSide {

    /**
     * The wall has no connection.
     */
    NONE,
    LOW,
    TALL
}

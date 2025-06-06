package net.aquamine.api.block.meta

/**
 * Indicates the face of a connected block that a block this property is
 * applied to is attached to.
 */
enum class AttachFace {

    /**
     * The block is attached to the top face of the block below it.
     */
    FLOOR,

    /**
     * The block is attached to the north, south, east, or west face of the
     * block to the north, south, east, or west of it.
     */
    WALL,

    /**
     * The block is attached to the bottom face of the block above it.
     */
    CEILING
}

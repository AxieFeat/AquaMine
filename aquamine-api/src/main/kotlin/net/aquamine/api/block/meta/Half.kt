package net.aquamine.api.block.meta

/**
 * Indicates which variant of a block that can attach to either the block
 * above it or the block below it, such as stairs or trapdoors, a block this
 * property is applied to represent.
 */
enum class Half {

    /**
     * The block is attached to the block above it.
     */
    TOP,

    /**
     * The block is attached to the block below it.
     */
    BOTTOM
}

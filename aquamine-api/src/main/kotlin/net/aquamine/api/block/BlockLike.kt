package net.aquamine.api.block

/**
 * Something that could be represented as a block.
 */
fun interface BlockLike {

    /**
     * Gets the block representation, or null if there is no block representation.
     */
    fun asBlock(): Block
}

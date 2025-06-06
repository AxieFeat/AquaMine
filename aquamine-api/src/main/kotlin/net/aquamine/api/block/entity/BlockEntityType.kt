package net.aquamine.api.block.entity

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed
import net.aquamine.api.block.Block

/**
 * A type of block entity.
 */
@CataloguedBy(BlockEntityTypes::class)
@ImmutableType
interface BlockEntityType<T : BlockEntity> : Keyed {

    /**
     * All the blocks that block entities of this type can be bound to.
     */
    val applicableBlocks: Set<Block>

    /**
     * Returns true if the given [block] is applicable to block entities of
     * this type, or false otherwise.
     *
     * @param block The block.
     *
     * @return `true` if the block is applicable, `false` otherwise.
     */
    fun isApplicable(block: Block): Boolean
}

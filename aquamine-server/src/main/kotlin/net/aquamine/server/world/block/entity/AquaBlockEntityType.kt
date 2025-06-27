package net.aquamine.server.world.block.entity

import com.google.common.collect.ImmutableSet
import net.kyori.adventure.key.Key
import net.aquamine.api.block.Block
import net.aquamine.api.block.entity.BlockEntity
import net.aquamine.api.block.entity.BlockEntityType

class AquaBlockEntityType<T : BlockEntity>(private val key: Key, override val applicableBlocks: ImmutableSet<Block>) : BlockEntityType<T> {

    override fun key(): Key = key

    override fun isApplicable(block: Block): Boolean = applicableBlocks.contains(block)

    override fun toString(): String = "BlockEntityType(applicableBlocks=$applicableBlocks)"
}

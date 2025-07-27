package net.aquamine.api.entity.animal.type

import net.aquamine.api.block.Block
import net.aquamine.api.block.Blocks
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.registry.RegistryReference

/**
 * A variant of mooshroom, which represents a particular type of mushroom,
 * either brown mushrooms, or red mushrooms.
 *
 * @property item The mushroom item this mooshroom variant represents.
 * @property block The mushroom block this mooshroom variant represents.
 */
@Suppress("UndocumentedPublicProperty")
enum class MooshroomVariant(val item: RegistryReference<ItemType>, val block: RegistryReference<Block>) {

    BROWN(ItemTypes.BROWN_MUSHROOM, Blocks.BROWN_MUSHROOM_BLOCK),
    RED(ItemTypes.RED_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK)
}

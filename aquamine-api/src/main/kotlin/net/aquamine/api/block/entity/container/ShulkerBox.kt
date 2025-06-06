package net.aquamine.api.block.entity.container

import net.aquamine.api.block.entity.NameableBlockEntity
import net.aquamine.api.item.data.DyeColor

/**
 * A shulker box.
 */
interface ShulkerBox : ContainerBlockEntity, NameableBlockEntity {

    /**
     * The colour of this shulker box.
     */
    val color: DyeColor?
}

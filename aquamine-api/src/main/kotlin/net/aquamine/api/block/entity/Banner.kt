package net.aquamine.api.block.entity

import net.aquamine.api.block.entity.banner.BannerPattern
import net.aquamine.api.item.data.DyeColor

/**
 * A banner.
 */
interface Banner : NameableBlockEntity {

    /**
     * The base color of this banner.
     */
    val baseColor: DyeColor

    /**
     * The patterns that are on this banner.
     */
    val patterns: List<BannerPattern>
}

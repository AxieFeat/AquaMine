package net.aquamine.api.block.entity.banner

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed

/**
 * A type of banner pattern.
 */
@CataloguedBy(BannerPatternTypes::class)
@ImmutableType
interface BannerPatternType : Keyed {

    /**
     * The shortened code identifying the banner pattern, as specified by
     * https://minecraft.wiki/w/Banner#Block_data
     */
    val code: String
}

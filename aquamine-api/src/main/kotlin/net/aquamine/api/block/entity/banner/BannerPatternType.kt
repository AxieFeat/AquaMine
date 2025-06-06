package net.aquamine.api.block.entity.banner

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed

/**
 * A type of banner pattern.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@CataloguedBy(BannerPatternTypes::class)
@ImmutableType
interface BannerPatternType : Keyed {

    /**
     * The shortened code identifying the banner pattern, as specified by
     * https://minecraft.fandom.com/wiki/Banner#Block_data
     */
    @get:JvmName("code")
    val code: String
}

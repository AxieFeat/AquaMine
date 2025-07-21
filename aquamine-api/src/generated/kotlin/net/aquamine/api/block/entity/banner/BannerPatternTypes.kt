package net.aquamine.api.block.entity.banner

import net.aquamine.annotations.Catalogue
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference
import net.kyori.adventure.key.Key

/**
 * This file is auto-generated. Do not edit this manually!
 */
@Catalogue(BannerPatternType::class)
object BannerPatternTypes {

    // @formatter:off
    @JvmField
    val BASE: RegistryReference<BannerPatternType> = of("base")
    @JvmField
    val SQUARE_BOTTOM_LEFT: RegistryReference<BannerPatternType> = of("square_bottom_left")
    @JvmField
    val SQUARE_BOTTOM_RIGHT: RegistryReference<BannerPatternType> = of("square_bottom_right")
    @JvmField
    val SQUARE_TOP_LEFT: RegistryReference<BannerPatternType> = of("square_top_left")
    @JvmField
    val SQUARE_TOP_RIGHT: RegistryReference<BannerPatternType> = of("square_top_right")
    @JvmField
    val STRIPE_BOTTOM: RegistryReference<BannerPatternType> = of("stripe_bottom")
    @JvmField
    val STRIPE_TOP: RegistryReference<BannerPatternType> = of("stripe_top")
    @JvmField
    val STRIPE_LEFT: RegistryReference<BannerPatternType> = of("stripe_left")
    @JvmField
    val STRIPE_RIGHT: RegistryReference<BannerPatternType> = of("stripe_right")
    @JvmField
    val STRIPE_CENTER: RegistryReference<BannerPatternType> = of("stripe_center")
    @JvmField
    val STRIPE_MIDDLE: RegistryReference<BannerPatternType> = of("stripe_middle")
    @JvmField
    val STRIPE_DOWNRIGHT: RegistryReference<BannerPatternType> = of("stripe_downright")
    @JvmField
    val STRIPE_DOWNLEFT: RegistryReference<BannerPatternType> = of("stripe_downleft")
    @JvmField
    val STRIPE_SMALL: RegistryReference<BannerPatternType> = of("small_stripes")
    @JvmField
    val CROSS: RegistryReference<BannerPatternType> = of("cross")
    @JvmField
    val STRAIGHT_CROSS: RegistryReference<BannerPatternType> = of("straight_cross")
    @JvmField
    val TRIANGLE_BOTTOM: RegistryReference<BannerPatternType> = of("triangle_bottom")
    @JvmField
    val TRIANGLE_TOP: RegistryReference<BannerPatternType> = of("triangle_top")
    @JvmField
    val TRIANGLES_BOTTOM: RegistryReference<BannerPatternType> = of("triangles_bottom")
    @JvmField
    val TRIANGLES_TOP: RegistryReference<BannerPatternType> = of("triangles_top")
    @JvmField
    val DIAGONAL_LEFT: RegistryReference<BannerPatternType> = of("diagonal_left")
    @JvmField
    val DIAGONAL_RIGHT: RegistryReference<BannerPatternType> = of("diagonal_up_right")
    @JvmField
    val DIAGONAL_LEFT_MIRROR: RegistryReference<BannerPatternType> = of("diagonal_up_left")
    @JvmField
    val DIAGONAL_RIGHT_MIRROR: RegistryReference<BannerPatternType> = of("diagonal_right")
    @JvmField
    val CIRCLE_MIDDLE: RegistryReference<BannerPatternType> = of("circle")
    @JvmField
    val RHOMBUS_MIDDLE: RegistryReference<BannerPatternType> = of("rhombus")
    @JvmField
    val HALF_VERTICAL: RegistryReference<BannerPatternType> = of("half_vertical")
    @JvmField
    val HALF_HORIZONTAL: RegistryReference<BannerPatternType> = of("half_horizontal")
    @JvmField
    val HALF_VERTICAL_MIRROR: RegistryReference<BannerPatternType> = of("half_vertical_right")
    @JvmField
    val HALF_HORIZONTAL_MIRROR: RegistryReference<BannerPatternType> = of("half_horizontal_bottom")
    @JvmField
    val BORDER: RegistryReference<BannerPatternType> = of("border")
    @JvmField
    val CURLY_BORDER: RegistryReference<BannerPatternType> = of("curly_border")
    @JvmField
    val GRADIENT: RegistryReference<BannerPatternType> = of("gradient")
    @JvmField
    val GRADIENT_UP: RegistryReference<BannerPatternType> = of("gradient_up")
    @JvmField
    val BRICKS: RegistryReference<BannerPatternType> = of("bricks")
    @JvmField
    val GLOBE: RegistryReference<BannerPatternType> = of("globe")
    @JvmField
    val CREEPER: RegistryReference<BannerPatternType> = of("creeper")
    @JvmField
    val SKULL: RegistryReference<BannerPatternType> = of("skull")
    @JvmField
    val FLOWER: RegistryReference<BannerPatternType> = of("flower")
    @JvmField
    val MOJANG: RegistryReference<BannerPatternType> = of("mojang")
    @JvmField
    val PIGLIN: RegistryReference<BannerPatternType> = of("piglin")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<BannerPatternType> = RegistryReference.of(Registries.BANNER_PATTERN, Key.key(name))
}

package net.aquamine.api.block.entity.banner

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference

/**
 * All the built-in vanilla banner pattern types.
 */
@Catalogue(BannerPatternType::class)
object BannerPatternTypes {

    @JvmField
    val BASE: RegistryReference<BannerPatternType> = of("base")
    @JvmField
    val BOTTOM_LEFT_SQUARE: RegistryReference<BannerPatternType> = of("square_bottom_left")
    @JvmField
    val BOTTOM_RIGHT_SQUARE: RegistryReference<BannerPatternType> = of("square_bottom_right")
    @JvmField
    val TOP_LEFT_SQUARE: RegistryReference<BannerPatternType> = of("square_top_left")
    @JvmField
    val TOP_RIGHT_SQUARE: RegistryReference<BannerPatternType> = of("square_top_right")
    @JvmField
    val BOTTOM_STRIPE: RegistryReference<BannerPatternType> = of("stripe_bottom")
    @JvmField
    val TOP_STRIPE: RegistryReference<BannerPatternType> = of("stripe_top")
    @JvmField
    val LEFT_STRIPE: RegistryReference<BannerPatternType> = of("stripe_left")
    @JvmField
    val RIGHT_STRIPE: RegistryReference<BannerPatternType> = of("stripe_right")
    @JvmField
    val CENTER_STRIPE: RegistryReference<BannerPatternType> = of("stripe_center")
    @JvmField
    val MIDDLE_STRIPE: RegistryReference<BannerPatternType> = of("stripe_middle")
    @JvmField
    val DOWN_RIGHT_STRIPE: RegistryReference<BannerPatternType> = of("stripe_downright")
    @JvmField
    val DOWN_LEFT_STRIPE: RegistryReference<BannerPatternType> = of("stripe_downleft")
    @JvmField
    val SMALL_STRIPES: RegistryReference<BannerPatternType> = of("small_stripes")
    @JvmField
    val CROSS: RegistryReference<BannerPatternType> = of("cross")
    @JvmField
    val STRAIGHT_CROSS: RegistryReference<BannerPatternType> = of("straight_cross")
    @JvmField
    val BOTTOM_TRIANGLE: RegistryReference<BannerPatternType> = of("triangle_bottom")
    @JvmField
    val TOP_TRIANGLE: RegistryReference<BannerPatternType> = of("triangle_top")
    @JvmField
    val BOTTOM_TRIANGLES: RegistryReference<BannerPatternType> = of("triangles_bottom")
    @JvmField
    val TOP_TRIANGLES: RegistryReference<BannerPatternType> = of("triangles_top")
    @JvmField
    val LEFT_DIAGONAL: RegistryReference<BannerPatternType> = of("diagonal_left")
    @JvmField
    val RIGHT_DIAGONAL: RegistryReference<BannerPatternType> = of("diagonal_up_right")
    @JvmField
    val LEFT_REVERSE_DIAGONAL: RegistryReference<BannerPatternType> = of("diagonal_up_left")
    @JvmField
    val RIGHT_REVERSE_DIAGONAL: RegistryReference<BannerPatternType> = of("diagonal_right")
    @JvmField
    val MIDDLE_CIRCLE: RegistryReference<BannerPatternType> = of("circle")
    @JvmField
    val MIDDLE_RHOMBUS: RegistryReference<BannerPatternType> = of("rhombus")
    @JvmField
    val LEFT_HALF_VERTICAL: RegistryReference<BannerPatternType> = of("half_vertical")
    @JvmField
    val TOP_HALF_HORIZONTAL: RegistryReference<BannerPatternType> = of("half_horizontal")
    @JvmField
    val RIGHT_HALF_VERTICAL: RegistryReference<BannerPatternType> = of("half_vertical_right")
    @JvmField
    val BOTTOM_HALF_HORIZONTAL: RegistryReference<BannerPatternType> = of("half_horizontal_bottom")
    @JvmField
    val BORDER: RegistryReference<BannerPatternType> = of("border")
    @JvmField
    val CURLY_BORDER: RegistryReference<BannerPatternType> = of("curly_border")
    @JvmField
    val GRADIENT: RegistryReference<BannerPatternType> = of("gradient")
    @JvmField
    val REVERSE_GRADIENT: RegistryReference<BannerPatternType> = of("gradient_up")
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

    @JvmStatic
    private fun of(name: String): RegistryReference<BannerPatternType> = RegistryReference.of(Registries.BANNER_PATTERN, Key.key(name))
}

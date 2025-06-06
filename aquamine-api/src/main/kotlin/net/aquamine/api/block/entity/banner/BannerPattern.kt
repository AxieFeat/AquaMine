package net.aquamine.api.block.entity.banner

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.item.data.DyeColor

/**
 * A pattern for a banner. These are immutable and may be reused.
 */
@ImmutableType
interface BannerPattern {

    /**
     * The type of this banner pattern.
     */
    val type: BannerPatternType

    /**
     * The color of this banner pattern.
     */
    val color: DyeColor

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(type: BannerPatternType, color: DyeColor): BannerPattern
    }

    companion object {

        /**
         * Creates a new banner pattern with the given values.
         *
         * @param type The type.
         * @param color The color.
         *
         * @return A new banner pattern.
         */
        @JvmStatic
        @Contract("_, _ -> new", pure = true)
        fun of(type: BannerPatternType, color: DyeColor): BannerPattern = AquaMine.factory<Factory>().of(type, color)
    }
}

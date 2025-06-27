package net.aquamine.server.world.block.entity.banner

import net.aquamine.api.block.entity.banner.BannerPattern
import net.aquamine.api.block.entity.banner.BannerPatternType
import net.aquamine.api.item.data.DyeColor
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.util.enumhelper.DyeColors
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.compound

@JvmRecord
data class AquaBannerPattern(override val type: BannerPatternType, override val color: DyeColor) : BannerPattern {

    object Factory : BannerPattern.Factory {

        override fun of(type: BannerPatternType, color: DyeColor): BannerPattern = AquaBannerPattern(type, color)
    }

    companion object {

        private const val PATTERN_TAG = "Pattern"
        private const val COLOR_TAG = "Color"

        @JvmStatic
        fun load(tag: CompoundTag): AquaBannerPattern {
            val patternCode = tag.getString(PATTERN_TAG)
            val type = requireNotNull(AquaRegistries.BANNER_PATTERN.firstOrNull { it.code == patternCode }) {
                "Could not find pattern type with code $patternCode!"
            }
            return AquaBannerPattern(type, DyeColors.fromId(tag.getInt(COLOR_TAG)))
        }

        @JvmStatic
        fun save(pattern: BannerPattern): CompoundTag = compound {
            putString(PATTERN_TAG, pattern.type.code)
            putInt(COLOR_TAG, pattern.color.ordinal)
        }
    }
}

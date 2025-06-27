package net.aquamine.server.item.data

import com.google.common.collect.ImmutableList
import net.aquamine.api.item.data.FireworkEffect
import net.aquamine.api.item.data.FireworkEffectType
import net.aquamine.api.util.Color
import net.aquamine.server.util.collection.ListExtras
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.compound
import java.util.Arrays

@JvmRecord
data class AquaFireworkEffect(
    override val type: FireworkEffectType,
    override val hasFlicker: Boolean,
    override val hasTrail: Boolean,
    override val colors: ImmutableList<Color>,
    override val fadeColors: ImmutableList<Color>
) : FireworkEffect {

    class Builder(private val type: FireworkEffectType) : FireworkEffect.Builder {

        private var flicker = false
        private var trail = false
        private val colors = ImmutableList.builder<Color>()
        private val fadeColors = ImmutableList.builder<Color>()

        override fun flickers(): Builder = apply { flicker = true }

        override fun trail(): Builder = apply { trail = true }

        override fun addColor(color: Color): Builder = apply { colors.add(color) }

        override fun addFadeColor(color: Color): Builder = apply { fadeColors.add(color) }

        override fun build(): AquaFireworkEffect = AquaFireworkEffect(type, flicker, trail, colors.build(), fadeColors.build())
    }

    object Factory : FireworkEffect.Factory {

        override fun builder(type: FireworkEffectType): Builder = Builder(type)
    }

    companion object {

        private const val TYPE_TAG = "Type"
        private const val FLICKER_TAG = "Flicker"
        private const val TRAIL_TAG = "Trail"
        private const val COLORS_TAG = "Colors"
        private const val FADE_COLORS_TAG = "FadeColors"
        private val EFFECT_TYPES = FireworkEffectType.values()

        @JvmStatic
        fun load(data: CompoundTag): AquaFireworkEffect {
            val type = EFFECT_TYPES[data.getByte(TYPE_TAG).toInt()]
            val colors = getColors(data, COLORS_TAG)
            val fadeColors = getColors(data, FADE_COLORS_TAG)
            return AquaFireworkEffect(type, data.getBoolean(FLICKER_TAG), data.getBoolean(TRAIL_TAG), colors, fadeColors)
        }

        @JvmStatic
        fun save(effect: FireworkEffect): CompoundTag = compound {
            putByte(TYPE_TAG, effect.type.ordinal.toByte())
            putBoolean(FLICKER_TAG, effect.hasFlicker)
            putBoolean(TRAIL_TAG, effect.hasTrail)
            putIntArray(COLORS_TAG, ListExtras.toIntArray(effect.colors, Color::encode))
            putIntArray(FADE_COLORS_TAG, ListExtras.toIntArray(effect.fadeColors, Color::encode))
        }

        @JvmStatic
        private fun getColors(data: CompoundTag, name: String): ImmutableList<Color> =
            Arrays.stream(data.getIntArray(name)).mapToObj { Color(it) }.collect(ImmutableList.toImmutableList())
    }
}

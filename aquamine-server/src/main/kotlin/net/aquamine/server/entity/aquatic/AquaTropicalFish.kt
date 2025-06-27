package net.aquamine.server.entity.aquatic

import net.aquamine.api.entity.aquatic.TropicalFish
import net.aquamine.api.entity.aquatic.TropicalFishVariant
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.data.DyeColor
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.aquatic.TropicalFishSerializer
import net.aquamine.server.util.enumhelper.DyeColors
import net.aquamine.server.world.AquaWorld
import kotlin.math.min

class AquaTropicalFish(world: AquaWorld) : AquaSchoolingFish(world), TropicalFish {

    override val type: AquaEntityType<AquaTropicalFish>
        get() = AquaEntityTypes.TROPICAL_FISH
    override val serializer: EntitySerializer<AquaTropicalFish>
        get() = TropicalFishSerializer
    override val bucketType: ItemType
        get() = ItemTypes.TROPICAL_FISH_BUCKET.get()

    override var baseColor: DyeColor
        get() = extractBaseColor(data.get(MetadataKeys.TropicalFish.VARIANT))
        set(value) = updateVariant(::modifyBaseColor, value)
    override var patternColor: DyeColor
        get() = extractPatternColor(data.get(MetadataKeys.TropicalFish.VARIANT))
        set(value) = updateVariant(::modifyPatternColor, value)
    override var variant: TropicalFishVariant
        get() = extractVariant(data.get(MetadataKeys.TropicalFish.VARIANT))
        set(value) = updateVariant(::modifyVariant, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.TropicalFish.VARIANT, TropicalFishVariant.KOB.ordinal)
    }

    /* FIXME
    override fun saveToBucket(item: KryptonItemStack) {
        item.meta.nbt = item.meta.nbt.putInt("BucketVariantTag", data.get(MetadataKeys.TROPICAL_FISH.VARIANT))
    }
     */

    private inline fun <T> updateVariant(modifier: (Int, T) -> Int, value: T) {
        data.set(MetadataKeys.TropicalFish.VARIANT, modifier(data.get(MetadataKeys.TropicalFish.VARIANT), value))
    }

    @Suppress("MagicNumber") // Nothing in here is magic as it's all explained in comments
    companion object {

        private val VARIANTS = TropicalFishVariant.values()
        private const val NO_SHAPE_PATTERN_MASK = -65536 // 11111111 11111111 00000000 00000000 (clear bits 0-15)
        private const val NO_BASE_COLOR_MASK = -16711681 // 11111111 00000000 11111111 11111111 (clear bits 16-23)
        private const val NO_PATTERN_COLOR_MASK = -65281 // 11111111 11111111 00000000 11111111 (clear bits 8-15)
        private const val SHAPE_MASK = 255 // 00000000 00000000 00000000 11111111 (clear all bits except 0-7)
        private const val PATTERN_MASK = 65280 // 00000000 00000000 11111111 00000000 (clear all bits except 8-15)
        private const val BASE_COLOR_MASK = 16711680 // 00000000 11111111 00000000 00000000 (clear all bits except 16-23)
        private const val PATTERN_COLOR_MASK = -16777216 // 11111111 00000000 00000000 00000000 (clear all bits except 24-31)

        /*
         * Tropical fish data is all packed in to a single 32-bit integer, with 8 bits being
         * used to represent each part that combine to make the variant.
         *
         * The encoding is better visualised with a table:
         * | Pattern colour | Base colour | Pattern | Shape  |
         * | -------------- | ----------- | ------- | ------ |
         * | 8 bits         | 8 bits      | 8 bits  | 8 bits |
         *
         * For example, a variant with shape 1, pattern 4, base colour gray, and pattern colour
         * purple would have the following encoded variant:
         * | Pattern colour | Base colour | Pattern  | Shape    |
         * | -------------- | ----------- | -------- | -------- |
         * | 00001010       | 00000111    | 00000100 | 00000001 |
         * Combining together in to 00001010000001110000010000000001
         *
         * For the modifications, we use AND mechanics to clear the bits we want to replace.
         * Because of the way AND works, the following are always true, where a is any binary digit:
         * - a AND 1 = a
         * - a AND 0 = 0
         */

        /*
         * The + 2 shr 3 here is a trick to get a 0 for values 5 and under and a 1 for values 6 and over (up to 13, though we only have 12 values)
         * Because 7 is the maximum value you can fit in 3 bits, anything above 7 up to 15 will always have the 8 bit (bit 3) set to 1, and as it's
         * 5 and up, we add 2 to make it up to 7. Therefore, every value 7 and below will have bit 3 set to 0, and every value 8-15 will have bit 3
         * set to 1. Therefore, we add 2 to make it up to 7 instead of up to 5, and then shift right by 3 to get the value of bit 3.
         *
         * Huge micro-optimisation for this sort of thing, but it does make for some very clean code.
         *
         * Also, for the second part, doing mod 6 produces a number from 0-5 for indices up to 5 and then 0-5 for indices up to 11. Essentially,
         * it's a way to reset the value back to 0 after 6 values.
         */
        @JvmStatic
        private fun modifyVariant(encoded: Int, variant: TropicalFishVariant): Int =
            encoded and NO_SHAPE_PATTERN_MASK or (variant.ordinal + 2 shr 3 or (variant.ordinal % 6 shl 8))

        @JvmStatic
        private fun modifyBaseColor(encoded: Int, baseColor: DyeColor): Int = encoded and NO_BASE_COLOR_MASK or (baseColor.ordinal shl 16)

        @JvmStatic
        private fun modifyPatternColor(encoded: Int, patternColor: DyeColor): Int =
            encoded and NO_PATTERN_COLOR_MASK or (patternColor.ordinal shl 24)

        @JvmStatic
        private fun extractBaseColor(variant: Int): DyeColor = DyeColors.fromId(variant and BASE_COLOR_MASK shr 16)

        @JvmStatic
        private fun extractPatternColor(encoded: Int): DyeColor = DyeColors.fromId(encoded and PATTERN_COLOR_MASK shr 24)

        @JvmStatic
        private fun extractVariant(encoded: Int): TropicalFishVariant =
            VARIANTS[min(encoded and SHAPE_MASK, 1) + 6 * min(encoded and PATTERN_MASK shr 8, 5)]
    }
}

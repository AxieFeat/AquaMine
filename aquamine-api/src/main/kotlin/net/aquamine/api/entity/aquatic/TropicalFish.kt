package net.aquamine.api.entity.aquatic

import net.aquamine.api.item.data.DyeColor

/**
 * A tropical fish.
 */
interface TropicalFish : SchoolingFish {

    /**
     * The base color of this tropical fish.
     */
    var baseColor: DyeColor

    /**
     * The color of the pattern on this tropical fish.
     */
    var patternColor: DyeColor

    /**
     * The variant that this tropical fish is, which determines its shape and
     * pattern.
     */
    var variant: TropicalFishVariant
}

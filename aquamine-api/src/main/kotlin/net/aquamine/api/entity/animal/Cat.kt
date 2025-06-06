package net.aquamine.api.entity.animal

import net.aquamine.api.entity.animal.type.CatVariant
import net.aquamine.api.item.data.DyeColor

/**
 * A cat.
 */
interface Cat : Tamable {

    /**
     * The variant of this cat.
     */
    var variant: CatVariant

    /**
     * If this cat is currently lying down.
     */
    var isLying: Boolean

    /**
     * If this cat is currently relaxed.
     */
    var isRelaxed: Boolean

    /**
     * The colour of this cat's collar.
     */
    var collarColor: DyeColor

    /**
     * Orders the cat to hiss, playing the sound [SoundEvents.CAT_HISS] to
     * surrounding entities.
     */
    fun hiss()
}

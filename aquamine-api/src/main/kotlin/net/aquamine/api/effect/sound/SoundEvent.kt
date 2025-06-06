package net.aquamine.api.effect.sound

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.sound.Sound

/**
 * A type of sound.
 */
@CataloguedBy(SoundEvents::class)
@ImmutableType
interface SoundEvent : Sound.Type {

    /**
     * The range that this sound can be heard from.
     *
     * A value of 0 indicates the sound does not have a range.
     */
    val range: Float
}

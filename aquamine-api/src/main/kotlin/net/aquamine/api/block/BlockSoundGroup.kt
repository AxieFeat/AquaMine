package net.aquamine.api.block

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.sound.SoundEvent

/**
 * The sounds that a block will make when specific actions are taken, such as
 * breaking it, stepping on it, or falling on it.
 */
@ImmutableType
interface BlockSoundGroup {

    /**
     * The volume that the sounds in this group will be played at.
     */
    val volume: Float

    /**
     * The pitch that the sounds in this group will be played at.
     */
    val pitch: Float

    /**
     * The sound that is played when the block is broken.
     */
    val breakSound: SoundEvent

    /**
     * The sound that is played when the block is stepped on.
     */
    val stepSound: SoundEvent

    /**
     * The sound that is played when the block is placed.
     */
    val placeSound: SoundEvent

    /**
     * The sound that is played when the block is hit.
     */
    val hitSound: SoundEvent

    /**
     * The sound that is played when the block falls.
     */
    val fallSound: SoundEvent
}

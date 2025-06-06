package net.aquamine.api.effect.particle.data

import net.aquamine.annotations.ImmutableType
import org.jetbrains.annotations.Contract

/**
 * Holds data for note particle effects.
 */
@ImmutableType
interface NoteParticleData : ParticleData {

    /**
     * The note that will be displayed. Must be between 0 and 24 (inclusive).
     */
    val note: Byte

    companion object {

        /**
         * Creates new note particle data with the given [note].
         *
         * @param note The note that will be displayed.
         *
         * @return New note particle data.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun of(note: Byte): NoteParticleData = ParticleData.factory().note(note)
    }
}

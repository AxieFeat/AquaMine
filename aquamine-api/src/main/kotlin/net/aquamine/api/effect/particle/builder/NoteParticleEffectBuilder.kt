package net.aquamine.api.effect.particle.builder

import net.aquamine.annotations.dsl.ParticleDsl
import org.jetbrains.annotations.Contract

/**
 * A builder for building note particle effects.
 */
@ParticleDsl
interface NoteParticleEffectBuilder : BaseParticleEffectBuilder<NoteParticleEffectBuilder> {

    /**
     * Sets the note for the particle effect.
     *
     * Must be between 0 and 24 inclusively.
     *
     * @param note The note.
     *
     * @return This builder.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun note(note: Int): NoteParticleEffectBuilder
}

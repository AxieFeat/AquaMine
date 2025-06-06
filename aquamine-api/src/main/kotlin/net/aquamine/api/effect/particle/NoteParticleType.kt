package net.aquamine.api.effect.particle

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.particle.builder.NoteParticleEffectBuilder

/**
 * A type of particle that uses a specific note value for its color
 * appearance.
 */
@ImmutableType
interface NoteParticleType : ScopedParticleType<NoteParticleEffectBuilder>

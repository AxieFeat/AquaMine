package net.aquamine.api.effect.particle

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.particle.builder.DustTransitionParticleEffectBuilder

/**
 * A type of particle that uses a color and scale for its appearance, and
 * transitions from one color to another.
 */
@ImmutableType
interface DustTransitionParticleType : ScopedParticleType<DustTransitionParticleEffectBuilder>

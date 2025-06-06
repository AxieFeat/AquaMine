package net.aquamine.api.effect.particle

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.particle.builder.VibrationParticleEffectBuilder

/**
 * A type of particle that vibrates from one location to another in a given
 * number of ticks.
 */
@ImmutableType
interface VibrationParticleType : ScopedParticleType<VibrationParticleEffectBuilder>

package net.aquamine.api.effect.particle

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.particle.builder.DustParticleEffectBuilder

/**
 * A type of particle that uses a color and scale for its appearance.
 */
@ImmutableType
interface DustParticleType : ScopedParticleType<DustParticleEffectBuilder>

package net.aquamine.api.effect.particle

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.particle.builder.SimpleParticleEffectBuilder

/**
 * A type of particle with basic options available.
 */
@ImmutableType
interface SimpleParticleType : ScopedParticleType<SimpleParticleEffectBuilder>

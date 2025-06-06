package net.aquamine.api.effect.particle

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.particle.builder.DirectionalParticleEffectBuilder

/**
 * A type of particle that can have velocity applied in a direction.
 */
@ImmutableType
interface DirectionalParticleType : ScopedParticleType<DirectionalParticleEffectBuilder>

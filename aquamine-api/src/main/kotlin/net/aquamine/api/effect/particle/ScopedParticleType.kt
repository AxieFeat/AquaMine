package net.aquamine.api.effect.particle

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.particle.builder.BaseParticleEffectBuilder

/**
 * A particle type that scopes the return of the builder.
 */
@ImmutableType
sealed interface ScopedParticleType<B : BaseParticleEffectBuilder<B>> : ParticleType {

    override fun builder(): B
}

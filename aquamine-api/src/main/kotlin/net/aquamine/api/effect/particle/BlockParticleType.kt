package net.aquamine.api.effect.particle

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.particle.builder.BlockParticleEffectBuilder

/**
 * A type of particle that uses a block texture for its appearance.
 */
@ImmutableType
interface BlockParticleType : ScopedParticleType<BlockParticleEffectBuilder>

package net.aquamine.api.effect.particle

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.particle.builder.ItemParticleEffectBuilder

/**
 * A type of particle that uses an item texture for its appearance.
 */
@ImmutableType
interface ItemParticleType : ScopedParticleType<ItemParticleEffectBuilder>

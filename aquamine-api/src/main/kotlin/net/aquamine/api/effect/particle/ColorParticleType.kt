package net.aquamine.api.effect.particle

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.particle.builder.ColorParticleEffectBuilder

/**
 * A type of particle that uses a color for its appearance.
 */
@ImmutableType
interface ColorParticleType : ScopedParticleType<ColorParticleEffectBuilder>

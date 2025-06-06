package net.aquamine.api.effect.particle

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed
import org.jetbrains.annotations.Contract
import net.aquamine.api.effect.particle.builder.BaseParticleEffectBuilder

/**
 * A type of particle effect.
 */
@CataloguedBy(ParticleTypes::class)
@ImmutableType
interface ParticleType : Keyed {

    /**
     * Constructs a new builder to build a new [ParticleEffect] of this type.
     */
    @Contract("_ -> new", pure = true)
    fun builder(): BaseParticleEffectBuilder<*>
}

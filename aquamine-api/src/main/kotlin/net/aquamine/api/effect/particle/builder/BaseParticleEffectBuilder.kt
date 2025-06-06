package net.aquamine.api.effect.particle.builder

import net.aquamine.annotations.dsl.ParticleDsl
import net.kyori.adventure.builder.AbstractBuilder
import org.jetbrains.annotations.Contract
import net.aquamine.api.effect.particle.ParticleEffect
import net.aquamine.api.util.Vec3d

/**
 * The base builder for building particle effects.
 */
@ParticleDsl
interface BaseParticleEffectBuilder<B : BaseParticleEffectBuilder<B>> : AbstractBuilder<ParticleEffect> {

    /**
     * Sets the number of particles to be spawned by the particle effect.
     *
     * The quantity must be between 1 and 16,384, inclusively.
     *
     * @param quantity The quantity.
     *
     * @return This builder.
     *
     * @throws IllegalArgumentException If the quantity is < 1.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun quantity(quantity: Int): B

    /**
     * Sets the offset the particles can be from the origin.
     *
     * @param offset The offset from the origin.
     *
     * @return This builder.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun offset(offset: Vec3d): B

    /**
     * Sets whether the particle effect can be viewed from a further distance
     * than normal.
     *
     * This value changes the maximum view distance, in blocks, that the
     * effect can be viewed from the location where the effect was spawned to
     * the following:
     * * If true, 65,536 blocks
     * * If false, 256 blocks
     *
     * @param longDistance Whether the effect can be viewed from a further
     * distance than normal.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun longDistance(longDistance: Boolean): B
}

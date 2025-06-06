package net.aquamine.api.effect.particle.builder

import net.aquamine.annotations.dsl.ParticleDsl
import org.jetbrains.annotations.Contract
import net.aquamine.api.util.Vec3d

/**
 * A builder for building vibration particle effects.
 */
@ParticleDsl
interface VibrationParticleEffectBuilder : BaseParticleEffectBuilder<VibrationParticleEffectBuilder> {

    /**
     * Sets the destination location from the given [position].
     *
     * @param position The destination position.
     *
     * @return This builder.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun destination(position: Vec3d): VibrationParticleEffectBuilder

    /**
     * Sets the number of ticks it will take to vibrate from the
     * origin to the destination.
     *
     * @param ticks The number of ticks.
     *
     * @return This builder.
     */
    @ParticleDsl
    @Contract("_ -> this", mutates = "this")
    fun ticks(ticks: Int): VibrationParticleEffectBuilder
}

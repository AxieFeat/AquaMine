package net.aquamine.api.effect.particle

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.effect.particle.builder.BaseParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.api.util.Vec3d

/**
 * Holds information used to spawn particles for a player.
 *
 * This effect is entirely immutable and so is safe for both storage and
 * reuse.
 *
 * Effect instances can only be created through particle effect builders (any
 * subclass of [BaseParticleEffectBuilder]).
 */
@ImmutableType
interface ParticleEffect {

    /**
     * The type of this effect.
     */
    val type: ParticleType

    /**
     * The amount of this effect that should be spawned.
     */
    val quantity: Int

    /**
     * The offset vector from the spawn location of this effect.
     */
    val offset: Vec3d

    /**
     * If the distance of this effect is longer than usual.
     *
     * Specifically, the distance will increase from 256 to 65,536.
     */
    val longDistance: Boolean

    /**
     * The extra data for this effect.
     *
     * May be null if this effect does not have any extra data.
     */
    val data: ParticleData?
}

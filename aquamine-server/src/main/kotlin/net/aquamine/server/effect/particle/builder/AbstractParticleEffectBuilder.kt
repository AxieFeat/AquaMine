package net.aquamine.server.effect.particle.builder

import net.aquamine.api.effect.particle.ParticleEffect
import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.particle.builder.BaseParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.api.util.Vec3d
import net.aquamine.server.effect.particle.AquaParticleEffect

@Suppress("UNCHECKED_CAST")
abstract class AbstractParticleEffectBuilder<B : BaseParticleEffectBuilder<B>>(protected val type: ParticleType) : BaseParticleEffectBuilder<B> {

    private var quantity = 1
    private var offset: Vec3d = Vec3d.ZERO
    private var longDistance = false

    abstract fun buildData(): ParticleData?

    override fun quantity(quantity: Int): B = apply {
        require(quantity >= 1) { "Quantity must be >= 1!" }
        this.quantity = quantity
    } as B

    override fun offset(offset: Vec3d): B = apply { this.offset = offset } as B

    override fun longDistance(longDistance: Boolean): B = apply { this.longDistance = longDistance } as B

    override fun build(): ParticleEffect = AquaParticleEffect(type, quantity, offset, longDistance, buildData())
}

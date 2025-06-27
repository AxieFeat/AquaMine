package net.aquamine.server.effect.particle

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.particle.SimpleParticleType
import net.aquamine.api.effect.particle.builder.SimpleParticleEffectBuilder
import net.aquamine.server.effect.particle.builder.AquaSimpleParticleEffectBuilder

@JvmRecord
data class AquaSimpleParticleType(private val key: Key) : SimpleParticleType {

    override fun key(): Key = key

    override fun builder(): SimpleParticleEffectBuilder = AquaSimpleParticleEffectBuilder(this)
}

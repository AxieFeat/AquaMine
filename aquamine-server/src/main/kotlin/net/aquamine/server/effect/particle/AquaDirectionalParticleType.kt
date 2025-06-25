package net.aquamine.server.effect.particle

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.particle.DirectionalParticleType
import net.aquamine.api.effect.particle.builder.DirectionalParticleEffectBuilder
import net.aquamine.server.effect.particle.builder.AquaDirectionalParticleEffectBuilder

@JvmRecord
data class AquaDirectionalParticleType(private val key: Key) : DirectionalParticleType {

    override fun key(): Key = key

    override fun builder(): DirectionalParticleEffectBuilder = AquaDirectionalParticleEffectBuilder(this)
}

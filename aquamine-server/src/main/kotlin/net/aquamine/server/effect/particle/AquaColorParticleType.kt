package net.aquamine.server.effect.particle

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.particle.ColorParticleType
import net.aquamine.api.effect.particle.builder.ColorParticleEffectBuilder
import net.aquamine.server.effect.particle.builder.AquaColorParticleEffectBuilder

@JvmRecord
data class AquaColorParticleType(private val key: Key) : ColorParticleType {

    override fun key(): Key = key

    override fun builder(): ColorParticleEffectBuilder = AquaColorParticleEffectBuilder(this)
}

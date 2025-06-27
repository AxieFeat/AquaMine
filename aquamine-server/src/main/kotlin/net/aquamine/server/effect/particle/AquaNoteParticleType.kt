package net.aquamine.server.effect.particle

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.particle.NoteParticleType
import net.aquamine.api.effect.particle.builder.NoteParticleEffectBuilder
import net.aquamine.server.effect.particle.builder.AquaNoteParticleEffectBuilder

@JvmRecord
data class AquaNoteParticleType(private val key: Key) : NoteParticleType {

    override fun key(): Key = key

    override fun builder(): NoteParticleEffectBuilder = AquaNoteParticleEffectBuilder(this)
}

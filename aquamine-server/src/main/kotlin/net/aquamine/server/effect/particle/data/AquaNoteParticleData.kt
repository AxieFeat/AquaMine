package net.aquamine.server.effect.particle.data

import net.aquamine.api.effect.particle.data.NoteParticleData

@JvmRecord
data class AquaNoteParticleData(override val note: Byte) : NoteParticleData {

    init {
        require(note in 0..24) { "Note must be between 0 and 24!" }
    }
}

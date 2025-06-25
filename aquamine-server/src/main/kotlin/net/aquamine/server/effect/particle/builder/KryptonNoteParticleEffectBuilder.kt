package net.aquamine.server.effect.particle.builder

import net.aquamine.api.effect.particle.NoteParticleType
import net.aquamine.api.effect.particle.builder.NoteParticleEffectBuilder
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.server.effect.particle.data.AquaNoteParticleData

class KryptonNoteParticleEffectBuilder(type: NoteParticleType) : AbstractParticleEffectBuilder<ApiNote>(type), ApiNote {

    private var note: Byte = 0

    override fun note(note: Int): ApiNote = apply {
        require(note in 0..24) { "Note must be between 0 and 24!" }
        this.note = note.toByte()
    }

    override fun buildData(): ParticleData = AquaNoteParticleData(note)
}

private typealias ApiNote = NoteParticleEffectBuilder

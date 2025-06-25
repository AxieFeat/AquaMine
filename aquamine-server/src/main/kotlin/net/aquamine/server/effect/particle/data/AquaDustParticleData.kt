package net.aquamine.server.effect.particle.data

import net.aquamine.api.effect.particle.data.DustParticleData
import net.aquamine.api.util.Color
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

@JvmRecord
data class AquaDustParticleData(override val color: Color, override val scale: Float) : DustParticleData, Writable {

    constructor(reader: BinaryReader) : this(reader.readParticleColor(), reader.readFloat())

    override fun write(writer: BinaryWriter) {
        writer.writeParticleColor(color)
        writer.writeFloat(scale)
    }
}

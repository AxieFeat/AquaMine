package net.aquamine.server.effect.particle.data

import net.aquamine.api.effect.particle.data.DustTransitionParticleData
import net.aquamine.api.util.Color
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

@JvmRecord
data class AquaDustTransitionParticleData(
    override val color: Color,
    override val scale: Float,
    override val toColor: Color
) : DustTransitionParticleData, Writable {

    constructor(reader: BinaryReader) : this(reader.readParticleColor(), reader.readFloat(), reader.readParticleColor())

    override fun write(writer: BinaryWriter) {
        writer.writeParticleColor(color)
        writer.writeFloat(scale)
        writer.writeParticleColor(toColor)
    }
}

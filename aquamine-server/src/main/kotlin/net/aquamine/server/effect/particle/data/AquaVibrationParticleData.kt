package net.aquamine.server.effect.particle.data

import net.aquamine.api.effect.particle.data.VibrationParticleData
import net.aquamine.api.util.Vec3d
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

@JvmRecord
data class AquaVibrationParticleData(override val destination: Vec3d, override val ticks: Int) : VibrationParticleData, Writable {

    constructor(reader: BinaryReader) : this(Vec3d(reader.readDouble(), reader.readDouble(), reader.readDouble()), reader.readInt())

    override fun write(writer: BinaryWriter) {
        // TODO: Sort this out when we have a new position source mechanism
        writer.writeDouble(destination.x)
        writer.writeDouble(destination.y)
        writer.writeDouble(destination.z)
        writer.writeInt(ticks)
    }
}

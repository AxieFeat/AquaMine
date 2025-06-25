package net.aquamine.server.effect.particle

import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.registry.KryptonRegistries

@JvmRecord
data class ParticleOptions(val type: ParticleType, val data: ParticleData?) : Writable {

    constructor(reader: BinaryReader) : this(reader, reader.readById(KryptonRegistries.PARTICLE_TYPE)!!)

    private constructor(reader: BinaryReader, type: ParticleType) : this(type, type.downcast().createData(reader))

    override fun write(writer: BinaryWriter) {
        if (data != null && data is Writable) data.write(writer)
    }
}

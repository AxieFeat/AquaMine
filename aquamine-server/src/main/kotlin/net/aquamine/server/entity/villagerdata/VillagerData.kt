package net.aquamine.server.entity.villagerdata

import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

/**
 * Various data for villagers. Used for entity metadata.
 */
@JvmRecord
data class VillagerData(val type: VillagerType, val profession: VillagerProfession, val level: Int) : Writable {

    constructor(reader: BinaryReader) : this(reader.readEnum(), reader.readEnum(), reader.readVarInt())

    override fun write(writer: BinaryWriter) {
        writer.writeEnum(type)
        writer.writeEnum(profession)
        writer.writeVarInt(level)
    }
}

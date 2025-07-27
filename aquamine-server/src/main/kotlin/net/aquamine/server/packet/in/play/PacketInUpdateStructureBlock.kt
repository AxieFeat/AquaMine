package net.aquamine.server.packet.`in`.play

import net.aquamine.api.util.Vec3d
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket
import net.aquamine.server.util.math.Mirror
import net.aquamine.server.util.math.Rotation

@JvmRecord
data class PacketInUpdateStructureBlock(
    val location: Vec3d,
    val action: Action,
    val mode: Mode,
    val name: String,
    val offset: Vec3d,
    val size: Vec3d,
    val mirror: Mirror,
    val rotation: Rotation,
    val metadata: String,
    val integrity: Float,
    val seed: Long,
    val flags: Byte
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        location = reader.readVec3d(),
        action = reader.readEnum(),
        mode = reader.readEnum(),
        name = reader.readString(),
        offset = reader.readVec3d(),
        size = reader.readVec3d(),
        mirror = reader.readEnum(),
        rotation = reader.readEnum(),
        metadata = reader.readString(),
        integrity = reader.readFloat(),
        seed = reader.readLong(),
        flags = reader.readByte()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVec3d(location)
        writer.writeEnum(action)
        writer.writeEnum(mode)
        writer.writeString(name)
        writer.writeVec3d(offset)
        writer.writeVec3d(size)
        writer.writeEnum(mirror)
        writer.writeEnum(rotation)
        writer.writeString(metadata)
        writer.writeFloat(integrity)
        writer.writeLong(seed)
        writer.writeByte(flags)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleUpdateStructureBlock(this)
    }

    enum class Action {
        UPDATE_DATA, SAVE, LOAD, DETECT_SIZE
    }

    enum class Mode {
        SAVE, LOAD, CORNER, DATA
    }
}

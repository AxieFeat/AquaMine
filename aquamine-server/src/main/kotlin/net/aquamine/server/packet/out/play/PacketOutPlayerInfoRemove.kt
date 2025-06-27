package net.aquamine.server.packet.out.play

import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.util.ImmutableLists
import java.util.UUID

@JvmRecord
data class PacketOutPlayerInfoRemove(val profileIds: List<UUID>) : Packet {

    constructor(player: AquaPlayer) : this(ImmutableLists.of(player.uuid))

    constructor(reader: BinaryReader) : this(reader.readList { it.readUUID() })

    override fun write(writer: BinaryWriter) {
        writer.writeCollection(profileIds, writer::writeUUID)
    }
}

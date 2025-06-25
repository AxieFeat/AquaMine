package net.aquamine.server.packet.`in`.handshake

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.HandshakePacketHandler
import net.aquamine.server.packet.InboundPacket
import net.aquamine.server.packet.PacketState

@JvmRecord
data class PacketInHandshake(
    val protocol: Int,
    val address: String,
    val port: Int,
    val nextState: PacketState
) : InboundPacket<HandshakePacketHandler> {

    constructor(reader: BinaryReader) : this(reader.readVarInt(), reader.readString(), reader.readUnsignedShort(), reader.readEnum())

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(protocol)
        writer.writeString(address)
        writer.writeShort(port.toShort())
        writer.writeEnum(nextState)
    }

    override fun handle(handler: HandshakePacketHandler) {
        handler.handleHandshake(this)
    }
}

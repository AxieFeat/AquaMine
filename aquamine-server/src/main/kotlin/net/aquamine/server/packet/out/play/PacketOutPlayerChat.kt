package net.aquamine.server.packet.out.play

import net.kyori.adventure.text.Component
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.chat.FilterMask
import net.aquamine.server.network.chat.MessageSignature
import net.aquamine.server.network.chat.RichChatType
import net.aquamine.server.network.chat.SignedMessageBody
import net.aquamine.server.packet.Packet
import java.util.UUID

@JvmRecord
data class PacketOutPlayerChat(
    val sender: UUID,
    val index: Int,
    val signature: MessageSignature?,
    val body: SignedMessageBody.Packed,
    val unsignedContent: Component?,
    val filterMask: FilterMask,
    val chatType: RichChatType.BoundNetwork
) : Packet {

    constructor(reader: BinaryReader) : this(reader.readUUID(), reader.readVarInt(), reader.readNullable(MessageSignature::read),
        SignedMessageBody.Packed(reader), reader.readNullable { it.readComponent() }, FilterMask.read(reader), RichChatType.BoundNetwork(reader))

    override fun write(writer: BinaryWriter) {
        writer.writeUUID(sender)
        writer.writeVarInt(index)
        writer.writeNullable(signature, MessageSignature::write)
        body.write(writer)
        writer.writeNullable(unsignedContent, BinaryWriter::writeComponent)
        FilterMask.write(writer, filterMask)
        chatType.write(writer)
    }
}

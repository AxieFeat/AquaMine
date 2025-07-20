package net.aquamine.server.packet.out.status

import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

/**
 * Response to the client's earlier [status request][net.aquamine.server.packet.in.status.PacketInStatusRequest] packet.
 */
@JvmRecord
data class PacketOutStatusResponse(
    val response: String
) : Packet {

    constructor(reader: BinaryReader) : this(
        response = reader.readString()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeString(response)
    }

    companion object {

        private val GSON = GsonComponentSerializer.gson().serializer().newBuilder()
            .registerTypeAdapter(ServerStatus.Players::class.java, ServerStatus.Players)
            .registerTypeAdapter(ServerStatus::class.java, ServerStatus)
            .create()

        @JvmStatic
        fun create(status: ServerStatus): PacketOutStatusResponse = PacketOutStatusResponse(GSON.toJson(status))
    }
}

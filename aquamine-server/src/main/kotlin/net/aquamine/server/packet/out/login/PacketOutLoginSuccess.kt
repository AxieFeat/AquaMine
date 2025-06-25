package net.aquamine.server.packet.out.login

import net.aquamine.api.auth.GameProfile
import net.aquamine.api.auth.ProfileProperty
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import java.util.UUID

/**
 * Sent to the client on successful login, to inform them of their own UUID.
 *
 * Not sure why we return their username to them though, as they already know
 * it because they told us it in login start.
 */
@JvmRecord
data class PacketOutLoginSuccess(val uuid: UUID, val username: String, val properties: List<ProfileProperty>) : Packet {

    constructor(reader: BinaryReader) : this(reader.readUUID(), reader.readString(), reader.readProfileProperties())

    override fun write(writer: BinaryWriter) {
        writer.writeUUID(uuid)
        writer.writeString(username)
        writer.writeProfileProperties(properties)
    }

    companion object {

        @JvmStatic
        fun create(profile: GameProfile): PacketOutLoginSuccess = PacketOutLoginSuccess(profile.uuid, profile.name, profile.properties)
    }
}

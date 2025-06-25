package net.aquamine.server.packet.out.play

import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.sound.SoundStop
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutStopSound(val source: Sound.Source?, val sound: Key?) : Packet {

    constructor(reader: BinaryReader) : this(reader, reader.readByte().toInt())

    private constructor(reader: BinaryReader, flags: Int) : this(
        if (flags and 1 > 0) reader.readEnum<Sound.Source>() else null,
        if (flags and 2 > 0) reader.readKey() else null
    )

    override fun write(writer: BinaryWriter) {
        if (source != null) {
            if (sound != null) {
                writer.writeByte(3)
                writer.writeEnum(source)
                writer.writeKey(sound)
                return
            }
            writer.writeByte(1)
            writer.writeEnum(source)
            return
        }
        if (sound != null) {
            writer.writeByte(2)
            writer.writeKey(sound)
            return
        }
        writer.writeByte(0)
    }

    companion object {

        @JvmStatic
        fun create(stop: SoundStop): PacketOutStopSound = PacketOutStopSound(stop.source(), stop.sound())
    }
}

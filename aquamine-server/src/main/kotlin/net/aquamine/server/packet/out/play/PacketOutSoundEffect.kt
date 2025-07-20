package net.aquamine.server.packet.out.play

import net.kyori.adventure.sound.Sound
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.server.effect.sound.AquaSoundEvent
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.registry.holder.Holder

@JvmRecord
data class PacketOutSoundEffect(
    val event: Holder<SoundEvent>,
    val source: Sound.Source,
    val x: Int,
    val y: Int,
    val z: Int,
    val volume: Float,
    val pitch: Float,
    val seed: Long
) : Packet {

    constructor(reader: BinaryReader) : this(
        event = reader.readById(AquaRegistries.SOUND_EVENT.asHolderIdMap(), AquaSoundEvent::read),
        source = reader.readEnum(),
        x = reader.readInt(),
        y = reader.readInt(),
        z = reader.readInt(),
        volume = reader.readFloat(),
        pitch = reader.readFloat(),
        seed = reader.readLong()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeId(AquaRegistries.SOUND_EVENT.asHolderIdMap(), event, AquaSoundEvent::write)
        writer.writeEnum(source)
        writer.writeInt(x)
        writer.writeInt(y)
        writer.writeInt(z)
        writer.writeFloat(volume)
        writer.writeFloat(pitch)
        writer.writeLong(seed)
    }

    companion object {

        @JvmStatic
        fun create(event: Holder<SoundEvent>, source: Sound.Source, x: Double, y: Double, z: Double, volume: Float, pitch: Float,
                   seed: Long): PacketOutSoundEffect {
            return PacketOutSoundEffect(event, source, (x * 8.0).toInt(), (y * 8.0).toInt(), (z * 8.0).toInt(), volume, pitch, seed)
        }
    }
}

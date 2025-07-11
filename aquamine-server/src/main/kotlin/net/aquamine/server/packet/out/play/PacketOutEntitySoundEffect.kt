package net.aquamine.server.packet.out.play

import net.kyori.adventure.sound.Sound
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.server.effect.sound.KryptonSoundEvent
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.registry.holder.Holder

@JvmRecord
data class PacketOutEntitySoundEffect(
    val event: Holder<SoundEvent>,
    val source: Sound.Source,
    override val entityId: Int,
    val volume: Float,
    val pitch: Float,
    val seed: Long
) : EntityPacket {

    constructor(reader: BinaryReader) : this(reader.readById(KryptonRegistries.SOUND_EVENT.asHolderIdMap(), KryptonSoundEvent::read),
        reader.readEnum(), reader.readVarInt(), reader.readFloat(), reader.readFloat(), reader.readLong())

    override fun write(writer: BinaryWriter) {
        writer.writeId(KryptonRegistries.SOUND_EVENT.asHolderIdMap(), event, KryptonSoundEvent::write)
        writer.writeEnum(source)
        writer.writeVarInt(entityId)
        writer.writeFloat(volume)
        writer.writeFloat(pitch)
        writer.writeLong(seed)
    }

    companion object {

        @JvmStatic
        fun create(source: KryptonEntity, sound: Sound, event: Holder<SoundEvent>, seed: Long): PacketOutEntitySoundEffect =
            PacketOutEntitySoundEffect(event, sound.source(), source.id, sound.volume(), sound.pitch(), seed)
    }
}

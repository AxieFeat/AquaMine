package net.aquamine.server.effect.sound

import net.kyori.adventure.key.Key
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.registry.network.RegistryFileCodec
import net.aquamine.server.util.Keys
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.codecs.RecordCodecBuilder
import java.util.Optional

@JvmRecord
data class AquaSoundEvent(private val key: Key, override val range: Float, private val newSystem: Boolean) : SoundEvent {

    fun getRange(volume: Float): Float {
        if (newSystem) return range
        return if (volume > 1F) DEFAULT_RANGE * volume else DEFAULT_RANGE
    }

    override fun key(): Key = key

    private fun fixedRange(): Optional<Float> = if (newSystem) Optional.of(range) else Optional.empty()

    companion object {

        // TODO: Replace most usages of this with the registry file codec.
        @JvmField
        val DIRECT_CODEC: Codec<SoundEvent> = RecordCodecBuilder.create { instance ->
            instance.group(
                Keys.CODEC.fieldOf("sound_id").getting { it.key() },
                Codec.FLOAT.optionalFieldOf("range").getting { it.downcast().fixedRange() }
            ).apply(instance) { id, range -> if (range.isPresent) AquaSoundEvent(id, range.get(), true) else createVariableRange(id) }
        }
        @JvmField
        val CODEC: Codec<Holder<SoundEvent>> = RegistryFileCodec.create(ResourceKeys.SOUND_EVENT, DIRECT_CODEC)
        // This is the default range that sounds travel (from vanilla) that the majority of sounds have from before ranged sounds were added.
        const val DEFAULT_RANGE: Float = 16F

        @JvmStatic
        fun createVariableRange(key: Key): AquaSoundEvent = AquaSoundEvent(key, DEFAULT_RANGE, false)

        @JvmStatic
        fun read(reader: BinaryReader): AquaSoundEvent {
            val key = reader.readKey()
            val newSystem = reader.readBoolean()
            val range = if (newSystem) reader.readFloat() else DEFAULT_RANGE
            return AquaSoundEvent(key, range, newSystem)
        }

        @JvmStatic
        fun write(writer: BinaryWriter, inputEvent: SoundEvent) {
            val event = inputEvent.downcast()
            writer.writeKey(event.key)
            writer.writeBoolean(event.newSystem)
            if (event.newSystem) writer.writeFloat(event.range)
        }
    }
}

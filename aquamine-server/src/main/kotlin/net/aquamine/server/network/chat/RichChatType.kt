package net.aquamine.server.network.chat

import net.kyori.adventure.chat.ChatType
import net.kyori.adventure.text.Component
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.registry.KryptonDynamicRegistries
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.codecs.RecordCodecBuilder

/**
 * Named as such to distinguish it from Adventure's ChatType.
 */
@JvmRecord
data class RichChatType(val chat: ChatTypeDecoration, val narration: ChatTypeDecoration) {

    fun bind(name: Component): Bound = Bound(this, name)

    @JvmRecord
    data class Bound(val chatType: RichChatType, val name: Component, val targetName: Component? = null) {

        fun decorate(message: Component): Component = chatType.chat.decorate(message, this)

        fun decorateNarration(message: Component): Component = chatType.narration.decorate(message, this)

        fun withTargetName(targetName: Component): Bound = Bound(chatType, name, targetName)

        fun toNetwork(): BoundNetwork = BoundNetwork(KryptonDynamicRegistries.CHAT_TYPE.getId(chatType), name, targetName)

        companion object {

            @JvmStatic
            fun from(adventure: ChatType.Bound): Bound {
                val type = checkNotNull(KryptonDynamicRegistries.CHAT_TYPE.get(adventure.type().key())) {
                    "Could not find corresponding rich chat type for Adventure type ${adventure.type()}!"
                }
                return Bound(type, adventure.name(), adventure.target())
            }
        }
    }

    @JvmRecord
    data class BoundNetwork(val chatType: Int, val name: Component, val targetName: Component?) : Writable {

        constructor(reader: BinaryReader) : this(reader.readVarInt(), reader.readComponent(), reader.readNullable(BinaryReader::readComponent))

        override fun write(writer: BinaryWriter) {
            writer.writeVarInt(chatType)
            writer.writeComponent(name)
            writer.writeNullable(targetName, BinaryWriter::writeComponent)
        }
    }

    companion object {

        @JvmField
        val CODEC: Codec<RichChatType> = RecordCodecBuilder.create { instance ->
            instance.group(
                ChatTypeDecoration.CODEC.fieldOf("chat").getting { it.chat },
                ChatTypeDecoration.CODEC.fieldOf("narration").getting { it.narration }
            ).apply(instance, ::RichChatType)
        }

        @JvmStatic
        fun bind(key: ResourceKey<RichChatType>, entity: KryptonEntity): Bound = bind(key, entity.displayName)

        @JvmStatic
        fun bind(key: ResourceKey<RichChatType>, source: CommandSourceStack): Bound = bind(key, source.displayName)

        @JvmStatic
        fun bind(key: ResourceKey<RichChatType>, name: Component): Bound = KryptonDynamicRegistries.CHAT_TYPE.getOrThrow(key).bind(name)
    }
}

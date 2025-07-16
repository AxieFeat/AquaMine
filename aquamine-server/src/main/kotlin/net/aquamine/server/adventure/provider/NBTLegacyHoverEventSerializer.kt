package net.aquamine.server.adventure.provider

import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.kyori.adventure.key.Key
import net.kyori.adventure.nbt.api.BinaryTagHolder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.serializer.json.LegacyHoverEventSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.kyori.adventure.util.Codec
import net.aquamine.server.util.nbt.SNBTParser
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.ImmutableCompoundTag
import java.io.IOException
import java.lang.RuntimeException
import java.util.UUID

/**
 * Serializes and deserializes hover events that used to be stored as
 * components and are now dedicated objects. This uses AquaMine's SNBT codec
 * and AquaMine NBT to parse NBT.
 */
object NBTLegacyHoverEventSerializer : LegacyHoverEventSerializer {

    private const val ITEM_TYPE = "id"
    private const val ITEM_COUNT = "Count"
    private const val ITEM_TAG = "tag"
    private const val ENTITY_TYPE = "type"
    private const val ENTITY_NAME = "name"
    private const val ENTITY_ID = "id"

    private val SNBT_CODEC: Codec<CompoundTag, String, CommandSyntaxException, RuntimeException> =
        Codec.codec({ SNBTParser.parse(it) }, { it.asString() })

    override fun deserializeShowItem(input: Component): HoverEvent.ShowItem {
        return try {
            val nbt = SNBT_CODEC.decode(PlainTextComponentSerializer.plainText().serialize(input))
            val tag = nbt.getCompound(ITEM_TAG)
            val holder = if (!tag.isEmpty()) BinaryTagHolder.encode(tag, SNBT_CODEC) else null
            HoverEvent.ShowItem.showItem(Key.key(nbt.getString(ITEM_TYPE)), nbt.getByte(ITEM_COUNT).toInt(), holder)
        } catch (exception: CommandSyntaxException) {
            throw IOException(exception)
        }
    }

    override fun deserializeShowEntity(input: Component, decoder: Codec.Decoder<Component, String, out RuntimeException>): HoverEvent.ShowEntity {
        return try {
            val nbt = SNBT_CODEC.decode(PlainTextComponentSerializer.plainText().serialize(input))
            val name = decoder.decode(nbt.getString(ENTITY_NAME))
            HoverEvent.ShowEntity.showEntity(Key.key(nbt.getString(ENTITY_TYPE)), UUID.fromString(nbt.getString(ENTITY_ID)), name)
        } catch (exception: CommandSyntaxException) {
            throw IOException(exception)
        }
    }

    override fun serializeShowItem(input: HoverEvent.ShowItem): Component {
        val tag = ImmutableCompoundTag.builder().putString(ITEM_TYPE, input.item().asString()).putByte(ITEM_COUNT, input.count().toByte())
        try {
            input.nbt()?.let { tag.put(ITEM_TAG, it.get(SNBT_CODEC)) }
        } catch (exception: CommandSyntaxException) {
            throw IOException(exception)
        }
        return Component.text(SNBT_CODEC.encode(tag.build()))
    }

    override fun serializeShowEntity(input: HoverEvent.ShowEntity, encoder: Codec.Encoder<Component, String, out RuntimeException>): Component {
        val tag = ImmutableCompoundTag.builder().putString(ENTITY_ID, input.id().toString()).putString(ENTITY_TYPE, input.type().asString())
        input.name()?.let { tag.putString(ENTITY_NAME, encoder.encode(it)) }
        return Component.text(SNBT_CODEC.encode(tag.build()))
    }
}

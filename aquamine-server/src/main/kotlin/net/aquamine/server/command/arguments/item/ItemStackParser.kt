package net.aquamine.server.command.arguments.item

import com.mojang.brigadier.StringReader
import net.kyori.adventure.key.Key
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.command.arguments.CommandExceptions
import net.aquamine.server.command.arguments.StringReading
import net.aquamine.server.item.AquaItemType
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.util.nbt.SNBTParser
import xyz.axie.nbt.CompoundTag

/**
 * A parser that can parse both item stacks and item stack predicates.
 */
object ItemStackParser { // TODO: Tags for ItemStackPredicate etc.

    private val ID_INVALID_EXCEPTION = CommandExceptions.dynamic("argument.item.id.invalid")
    private val TAG_DISALLOWED_EXCEPTION = CommandExceptions.simple("argument.item.tag.disallowed")
    @Suppress("UnusedPrivateMember")
    private val UNKNOWN_ITEM_TAG = CommandExceptions.dynamic("argument.item.tag.unknown")

    @JvmStatic
    fun parseItem(reader: StringReader): ItemStackArgument = ItemStackArgument(readItem(reader), readNBT(reader))

    @JvmStatic
    fun parsePredicate(reader: StringReader, allowTags: Boolean): ItemStackPredicate {
        var tag: String? = null
        var item: ItemType? = null
        var data: CompoundTag? = null

        if (reader.canRead() && reader.peek() == '#') tag = readTag(reader, allowTags) else item = readItem(reader)
        if (reader.canRead() && reader.peek() == '{') data = readNBT(reader)

        return ItemStackPredicate {
            if (item != null) {
                if (data != null) return@ItemStackPredicate data == it.meta.data
                return@ItemStackPredicate it.type == item
            }
            /* FIXME: When we implement command build context and holder lookups, rewrite this
            if (tag != null) {
                val tags = AquaTagManager.get(TagTypes.ITEMS, tag) ?: throw UNKNOWN_ITEM_TAG.create(tag.toString())
                return@ItemStackPredicate tags.contains(it.type)
            }
             */
            false
        }
    }

    @JvmStatic
    private fun readItem(reader: StringReader): AquaItemType {
        val keyString = StringReading.readKeyString(reader)
        val item = AquaRegistries.ITEM.get(Key.key(keyString))
        if (item === ItemTypes.AIR) throw ID_INVALID_EXCEPTION.createWithContext(reader, keyString)
        return item
    }

    @JvmStatic
    private fun readTag(reader: StringReader, allowTags: Boolean): String {
        if (allowTags) {
            reader.expect('#')
            return StringReading.readKeyString(reader)
        }
        throw TAG_DISALLOWED_EXCEPTION.createWithContext(reader)
    }

    @JvmStatic
    private fun readNBT(reader: StringReader): CompoundTag? {
        if (reader.canRead() && reader.peek() == '{') return SNBTParser(reader).readCompound()
        return null
    }
}

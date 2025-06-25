package net.aquamine.server.adventure

import com.mojang.brigadier.Message
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap
import net.kyori.adventure.inventory.Book
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.flattener.ComponentFlattener
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.item.meta.WrittenBookMeta
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.item.downcast
import net.aquamine.server.item.meta.KryptonWrittenBookMeta
import net.aquamine.server.util.gson.GsonHelper
import net.aquamine.server.util.Reflection

/**
 * Various things used by Krypton for supporting Adventure.
 */
object AquaAdventure {

    // We need to do this because the only other solution, which is to use the NAMES index, doesn't have the guaranteed ordering
    // that we require to map the IDs properly. This internal list has the ordering we need.
    private val NAMED_TEXT_COLORS = Reflection.accessField<NamedTextColor, List<NamedTextColor>>("VALUES")
    private val NAMED_TEXT_COLOR_ID_MAP = Object2IntArrayMap<NamedTextColor>(NAMED_TEXT_COLORS.size).apply {
        for (i in NAMED_TEXT_COLORS.indices) {
            put(NAMED_TEXT_COLORS.get(i), i)
        }
    }

    @JvmStatic
    fun colors(): List<NamedTextColor> = NAMED_TEXT_COLORS

    @JvmStatic
    fun getColorId(color: NamedTextColor): Int = NAMED_TEXT_COLOR_ID_MAP.getInt(color)

    @JvmStatic
    fun getColorFromId(id: Int): NamedTextColor = NAMED_TEXT_COLORS.get(id)

    @JvmStatic
    fun toItemStack(book: Book): KryptonItemStack {
        if (book is KryptonWrittenBookMeta) return KryptonItemStack(ItemTypes.WRITTEN_BOOK.get().downcast(), 1, book)
        return KryptonItemStack.Builder()
            .type(ItemTypes.WRITTEN_BOOK.get())
            .amount(1)
            .meta<_, WrittenBookMeta> {
                title(book.title())
                author(book.author())
                pages(book.pages())
            }
            .build()
    }

    @JvmStatic
    fun toStableJson(component: Component): String = GsonHelper.toStableString(GsonComponentSerializer.gson().serializeToTree(component))

    @JvmStatic
    fun asMessage(component: Component): Message = AquaAdventureMessage(component)

    @JvmStatic
    fun toPlainText(input: Component, maximumLength: Int): String {
        val result = StringBuilder()
        ComponentFlattener.basic().flatten(input) {
            val remaining = maximumLength - result.length
            if (remaining <= 0) return@flatten
            result.append(if (it.length <= remaining) it else it.substring(0, remaining))
        }
        return result.toString()
    }
}

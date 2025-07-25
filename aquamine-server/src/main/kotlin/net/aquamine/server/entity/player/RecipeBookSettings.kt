package net.aquamine.server.entity.player

import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import xyz.axie.nbt.CompoundTag
import java.util.EnumMap

class RecipeBookSettings private constructor(private val settings: MutableMap<RecipeBookType, TypeSettings>) : Writable {

    constructor() : this(EnumMap<_, TypeSettings>(RecipeBookType::class.java).apply { VALUES.forEach { put(it, TypeSettings.ALL_FALSE) } })

    fun isOpen(type: RecipeBookType): Boolean = getSettings(type, TypeSettings::open)

    fun isFiltered(type: RecipeBookType): Boolean = getSettings(type, TypeSettings::filtered)

    fun setOpen(type: RecipeBookType, open: Boolean) {
        modifySettings(type) { it.withOpen(open) }
    }

    fun setFiltered(type: RecipeBookType, filtered: Boolean) {
        modifySettings(type) { it.withFiltered(filtered) }
    }

    fun write(data: CompoundTag.Builder): CompoundTag.Builder = data.apply {
        TAG_FIELDS.forEach { (type, fields) ->
            val settings = settings.getOrDefault(type, TypeSettings.ALL_FALSE)
            putBoolean(fields.first, settings.open)
            putBoolean(fields.second, settings.filtered)
        }
    }

    override fun write(writer: BinaryWriter) {
        VALUES.forEach {
            val settings = settings.getOrDefault(it, TypeSettings.ALL_FALSE)
            writer.writeBoolean(settings.open)
            writer.writeBoolean(settings.filtered)
        }
    }

    private inline fun getSettings(type: RecipeBookType, block: (TypeSettings) -> Boolean): Boolean = settings.get(type)?.let(block) ?: false

    private inline fun modifySettings(type: RecipeBookType, crossinline modifier: (TypeSettings) -> TypeSettings) {
        settings.computeIfPresent(type) { _, settings -> modifier(settings) }
    }

    @JvmRecord
    private data class TypeSettings(val open: Boolean, val filtered: Boolean) {

        fun withOpen(value: Boolean): TypeSettings = if (open == value) this else TypeSettings(value, filtered)

        fun withFiltered(value: Boolean): TypeSettings = if (filtered == value) this else TypeSettings(open, value)

        companion object {

            @JvmField
            val ALL_FALSE: TypeSettings = TypeSettings(false, false)
        }
    }

    companion object {

        private val TAG_FIELDS = mapOf(
            RecipeBookType.CRAFTING to Pair("isGuiOpen", "isFilteringCraftable"),
            RecipeBookType.FURNACE to Pair("isFurnaceGuiOpen", "isFurnaceFilteringCraftable"),
            RecipeBookType.BLAST_FURNACE to Pair("isBlastingFurnaceGuiOpen", "isBlastingFurnaceFilteringCraftable"),
            RecipeBookType.SMOKER to Pair("isSmokerGuiOpen", "isSmokerFilteringCraftable")
        )
        private val VALUES = RecipeBookType.entries.toTypedArray()

        @JvmStatic
        fun read(data: CompoundTag): RecipeBookSettings {
            val map = EnumMap<_, TypeSettings>(RecipeBookType::class.java)
            TAG_FIELDS.forEach { (type, fields) -> map.put(type, TypeSettings(data.getBoolean(fields.first), data.getBoolean(fields.second))) }
            return RecipeBookSettings(map)
        }

        @JvmStatic
        fun read(reader: BinaryReader): RecipeBookSettings {
            val map = EnumMap<_, TypeSettings>(RecipeBookType::class.java)
            VALUES.forEach { map.put(it, TypeSettings(reader.readBoolean(), reader.readBoolean())) }
            return RecipeBookSettings(map)
        }
    }
}

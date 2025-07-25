package net.aquamine.server.entity.metadata

import net.aquamine.server.entity.components.BaseDataHolder
import space.vectrix.flare.fastutil.Int2ObjectSyncMap

// TODO: Possibly change this up to be less vanilla-like
class MetadataHolder(private val entity: BaseDataHolder) {

    private val itemsById = Int2ObjectSyncMap.hashmap<MutableEntry<*>>()
    private var dirty = false

    fun isDirty(): Boolean = dirty

    fun <T> define(key: MetadataKey<T>, value: T) {
        val id = key.id.toInt()
        require(id <= MAX_ID_VALUE) { "Data value id $id is too large! Maximum is $MAX_ID_VALUE!" }
        require(!itemsById.containsKey(id)) { "Duplicate id value for $id!" }
        require(MetadataSerializers.getId(key.serializer) >= 0) { "Unregistered serializer ${key.serializer} for $id!" }
        return createItem(key, value)
    }

    fun <T> get(key: MetadataKey<T>): T = entry(key).value

    fun <T> set(key: MetadataKey<T>, value: T) {
        val existing = entry(key)
        if (value == existing.value) return
        existing.value = value
        entity.onDataUpdate(key)
        existing.isDirty = true
        dirty = true
    }

    fun getFlag(key: MetadataKey<Byte>, flag: Int): Boolean = get(key).toInt() and (1 shl flag) != 0

    fun setFlag(key: MetadataKey<Byte>, flag: Int, state: Boolean) {
        val flags = get(key).toInt()
        val value = if (state) flags or (1 shl flag) else flags and (1 shl flag).inv()
        set(key, value.toByte())
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> entry(key: MetadataKey<T>): MutableEntry<T> = checkNotNull(itemsById.get(key.id.toInt()) as? MutableEntry<T>) {
        "Could not find key $key for entity of type ${entity.type}!"
    }

    private fun <T> createItem(key: MetadataKey<T>, value: T) {
        itemsById.put(key.id.toInt(), MutableEntry(key, value))
    }

    fun collectAll(): List<Entry<*>>? {
        var entries: MutableList<Entry<*>>? = null
        itemsById.values.forEach {
            if (entries == null) entries = ArrayList()
            entries.add(it.export())
        }
        return entries
    }

    fun collectDirty(): List<Entry<*>>? {
        var entries: MutableList<Entry<*>>? = null
        if (dirty) {
            itemsById.values.forEach {
                if (!it.isDirty) return@forEach
                it.isDirty = false
                if (entries == null) entries = ArrayList()
                entries.add(it.export())
            }
        }
        dirty = false
        return entries
    }

    @JvmRecord
    data class Entry<T>(val key: MetadataKey<T>, val value: T)

    private class MutableEntry<T>(val key: MetadataKey<T>, var value: T) {

        var isDirty: Boolean = true

        fun export(): Entry<T> = Entry(key, value)
    }

    companion object {

        private const val MAX_ID_VALUE = 254
    }
}

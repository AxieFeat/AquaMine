package net.aquamine.server.util.serialization.visitors

import xyz.axie.nbt.TagType

class FieldTree(val depth: Int, val selectedFields: MutableMap<String, TagType<*>>, val fieldsToRecurse: MutableMap<String, FieldTree>) {

    private constructor(depth: Int) : this(depth, HashMap(), HashMap())

    fun addEntry(selector: FieldSelector) {
        if (depth <= selector.path.size) {
            fieldsToRecurse.computeIfAbsent(selector.path[depth - 1]) { FieldTree(depth + 1) }.addEntry(selector)
        } else {
            selectedFields.put(selector.name, selector.type)
        }
    }

    fun isSelected(type: TagType<*>, name: String): Boolean = type == selectedFields[name]

    companion object {

        @JvmStatic
        fun createRoot(): FieldTree = FieldTree(1)
    }
}

package net.aquamine.server.util.serialization.visitors

import net.aquamine.server.util.ImmutableLists
import xyz.axie.nbt.TagType

@JvmRecord
data class FieldSelector(val path: List<String>, val type: TagType<*>, val name: String) {

    constructor(type: TagType<*>, name: String) : this(ImmutableLists.of(), type, name)

    constructor(element: String, type: TagType<*>, name: String) : this(ImmutableLists.of(element), type, name)

    constructor(first: String, second: String, type: TagType<*>, name: String) : this(ImmutableLists.of(first, second), type, name)
}

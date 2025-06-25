package net.aquamine.server.pack

import net.aquamine.server.pack.metadata.MetadataSectionSerializer
import net.aquamine.server.util.ImmutableMaps

class BuiltInMetadata private constructor(private val values: Map<MetadataSectionSerializer<*>, *>) {

    @Suppress("UNCHECKED_CAST")
    fun <T> get(serializer: MetadataSectionSerializer<T>): T = values.get(serializer) as T

    companion object {

        private val EMPTY = BuiltInMetadata(ImmutableMaps.of<_, Any>())

        @JvmStatic
        fun of(): BuiltInMetadata = EMPTY

        @JvmStatic
        fun <T> of(serializer: MetadataSectionSerializer<T>, value: T): BuiltInMetadata = BuiltInMetadata(ImmutableMaps.of(serializer, value))

        @JvmStatic
        fun <T1, T2> of(s1: MetadataSectionSerializer<T1>, v1: T1, s2: MetadataSectionSerializer<T2>, v2: T2): BuiltInMetadata =
            BuiltInMetadata(ImmutableMaps.of(s1, v1, s2, v2))
    }
}

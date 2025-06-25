package net.aquamine.server.util.map

import java.util.function.Function

// We use the platform class here to clean up the generated code, because we don't need to check for KMappedMarker,
// and we ideally want to call the JVM implementation directly, as that's the one with our desired semantics.
@Suppress("UNCHECKED_CAST", "PLATFORM_CLASS_MAPPED_TO_KOTLIN", "NOTHING_TO_INLINE")
inline fun <K, V> MutableMap<K, V>.nullableComputeIfAbsent(key: K, mappingFunction: Function<in K, out V?>): V? =
    (this as java.util.Map<K, V>).computeIfAbsent(key, mappingFunction)

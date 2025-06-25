package net.aquamine.server.util.map

/**
 * A bi-map of Int to T and T to Int.
 */
interface IntBiMap<T> : Iterable<T> {

    fun size(): Int

    fun get(id: Int): T?

    fun getId(value: T): Int
}

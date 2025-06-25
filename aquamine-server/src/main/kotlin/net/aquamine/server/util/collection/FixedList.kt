package net.aquamine.server.util.collection

import java.util.AbstractList

/**
 * A fixed-sized list that essentially acts as a wrapper around an array.
 *
 * This list does not support null elements.
 */
class FixedList<E : Any>(override val size: Int, private val fillElement: E) : AbstractList<E>() {

    @Suppress("UNCHECKED_CAST")
    private val array = Array<Any>(size) { fillElement } as Array<E>

    override fun get(index: Int): E = array[index]

    override fun set(index: Int, element: E): E {
        val old = array[index]
        array[index] = element
        return old
    }
}

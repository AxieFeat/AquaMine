package net.aquamine.server.util.collection

/**
 * A kind of forwarding list that downcasts elements to a more specific type using a user-specified downcast function.
 */
abstract class DowncastingList<U, D : U>(protected val delegate: List<U>) : List<D> {

    override val size: Int
        get() = delegate.size

    override fun isEmpty(): Boolean = delegate.isEmpty()

    override fun contains(element: D): Boolean = delegate.contains(element)

    override fun containsAll(elements: Collection<D>): Boolean = delegate.containsAll(elements)

    override fun get(index: Int): D = downcast(delegate.get(index))

    override fun indexOf(element: D): Int = delegate.indexOf(element)

    override fun lastIndexOf(element: D): Int = delegate.lastIndexOf(element)

    override fun iterator(): Iterator<D> = DowncastingIterator(delegate.iterator())

    override fun listIterator(): ListIterator<D> = DowncastingListIterator(delegate.listIterator())

    override fun listIterator(index: Int): ListIterator<D> = DowncastingListIterator(delegate.listIterator(index))

    override fun subList(fromIndex: Int, toIndex: Int): List<D> = createThis(delegate.subList(fromIndex, toIndex))

    protected abstract fun createThis(delegate: List<U>): DowncastingList<U, D>

    protected abstract fun downcast(element: U): D

    @Suppress("IteratorNotThrowingNoSuchElementException") // The delegate throws it. If it doesn't, that's an issue with the delegate, not us.
    private open inner class DowncastingIterator(private val delegate: Iterator<U>) : Iterator<D> {

        override fun hasNext(): Boolean = delegate.hasNext()

        override fun next(): D = downcast(delegate.next())
    }

    private inner class DowncastingListIterator(private val delegate: ListIterator<U>) : DowncastingIterator(delegate), ListIterator<D> {

        override fun hasPrevious(): Boolean = delegate.hasPrevious()

        override fun previous(): D = downcast(delegate.previous())

        override fun nextIndex(): Int = delegate.nextIndex()

        override fun previousIndex(): Int = delegate.previousIndex()
    }
}

package net.aquamine.server.util.bits

abstract class AbstractBitStorage(final override val size: Int) : BitStorage {

    protected fun checkIndex(index: Int) {
        require(index in 0 until size) { "Index must be between 0 and $size, was $index" }
    }
}

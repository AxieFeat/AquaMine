package net.aquamine.server.util.bits

class ZeroBitStorage(size: Int) : AbstractBitStorage(size) {

    override val bits: Int
        get() = 0
    override val data: LongArray
        get() = RAW

    override fun getAndSet(index: Int, value: Int): Int {
        checkIndex(index)
        checkValue(value)
        return 0
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return 0
    }

    override fun set(index: Int, value: Int) {
        checkIndex(index)
        checkValue(value)
    }

    private fun checkValue(value: Int) {
        require(value == 0) { "Value must be 0, was $value!" }
    }

    override fun forEach(consumer: StorageConsumer) {
        for (i in 0 until size) {
            consumer.accept(i, 0)
        }
    }

    override fun unpack(output: IntArray) {
        output.fill(0)
    }

    override fun copy(): BitStorage = this

    companion object {

        private val RAW = LongArray(0)
    }
}

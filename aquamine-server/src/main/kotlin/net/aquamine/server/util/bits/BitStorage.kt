package net.aquamine.server.util.bits

interface BitStorage {

    val data: LongArray
    val size: Int
    val bits: Int

    fun getAndSet(index: Int, value: Int): Int

    fun get(index: Int): Int

    fun set(index: Int, value: Int)

    fun forEach(consumer: StorageConsumer)

    fun unpack(output: IntArray)

    fun copy(): BitStorage
}

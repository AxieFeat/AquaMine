package net.aquamine.server.util.bits

fun interface StorageConsumer {

    fun accept(index: Int, value: Int)
}

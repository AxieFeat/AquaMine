package net.aquamine.server.util.crypto

fun interface SignatureUpdater {

    fun update(output: Output)

    fun interface Output {

        fun update(signature: ByteArray)
    }
}

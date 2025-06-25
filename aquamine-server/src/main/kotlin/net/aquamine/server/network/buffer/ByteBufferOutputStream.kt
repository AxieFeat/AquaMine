package net.aquamine.server.network.buffer

import java.io.OutputStream
import java.nio.ByteBuffer

class ByteBufferOutputStream(private val buffer: ByteBuffer) : OutputStream() {

    override fun write(b: Int) {
        buffer.put(b.toByte())
    }

    override fun write(b: ByteArray, off: Int, len: Int) {
        buffer.put(b, off, len)
    }
}

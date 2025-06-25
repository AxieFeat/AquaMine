package net.aquamine.server.network.buffer

import java.io.InputStream
import java.nio.ByteBuffer
import kotlin.math.min

/**
 * A simple input stream backed by a byte buffer.
 *
 * Source: https://stackoverflow.com/questions/4332264/wrapping-a-bytebuffer-with-an-inputstream/6603018#6603018
 */
class ByteBufferInputStream(private val buffer: ByteBuffer) : InputStream() {

    override fun read(): Int {
        if (!buffer.hasRemaining()) return -1
        return buffer.get().toInt() and 0xFF
    }

    override fun read(b: ByteArray, off: Int, len: Int): Int {
        if (!buffer.hasRemaining()) return -1
        val length = min(len, buffer.remaining())
        buffer.get(b, off, length)
        return length
    }
}

package net.aquamine.server.network

import net.aquamine.server.network.buffer.BinaryWriter

/**
 * Something that can write its data to a nio ByteBuffer.
 */
fun interface Writable {

    /**
     * Writes the data contained within this writable to the given [writer].
     */
    fun write(writer: BinaryWriter)
}

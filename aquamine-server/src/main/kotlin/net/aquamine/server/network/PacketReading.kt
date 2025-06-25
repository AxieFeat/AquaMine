package net.aquamine.server.network

import net.aquamine.server.network.buffer.BinaryBuffer
import net.aquamine.server.util.ObjectPool
import net.aquamine.server.util.readVarInt
import java.nio.BufferUnderflowException
import java.nio.ByteBuffer
import java.util.zip.DataFormatException
import java.util.zip.Inflater

object PacketReading {

    @JvmStatic
    fun readPackets(readBuffer: BinaryBuffer, compressed: Boolean, payloadConsumer: PayloadConsumer): BinaryBuffer? {
        var remaining: BinaryBuffer? = null
        val pool = ObjectPool.PACKET_POOL.get()

        while (readBuffer.readableBytes() > 0) {
            val beginMark = readBuffer.mark()
            try {
                // Ensure that the buffer contains the full packet (or wait for next socket read)
                val packetLength = readBuffer.readVarInt()
                val readerStart = readBuffer.readerIndex()
                if (!readBuffer.canRead(packetLength)) throw BufferUnderflowException()

                // Read packet
                var content = readBuffer
                var decompressedSize = packetLength
                if (compressed) {
                    val dataLength = readBuffer.readVarInt()
                    val payloadLength = packetLength - (readBuffer.readerIndex() - readerStart)
                    if (payloadLength < 0) throw DataFormatException("Negative payload length $payloadLength!")

                    if (dataLength == 0) {
                        decompressedSize = payloadLength
                    } else {
                        content = BinaryBuffer.wrap(pool)
                        decompressedSize = dataLength
                        val inflater = Inflater()
                        inflater.setInput(readBuffer.asByteBuffer(readBuffer.readerIndex(), payloadLength))
                        inflater.inflate(content.asByteBuffer(0, dataLength))
                        inflater.reset()
                    }
                }

                // Slice packet
                val payload = content.asByteBuffer(content.readerIndex(), decompressedSize)
                val packetId = payload.readVarInt()
                try {
                    payloadConsumer.consume(packetId, payload)
                } catch (_: Exception) {
                    // Don't care
                }

                // Position buffer to read the next packet
                readBuffer.readerIndex(readerStart + packetLength)
            } catch (_: BufferUnderflowException) {
                readBuffer.reset(beginMark)
                remaining = BinaryBuffer.copy(readBuffer)
                break
            }
        }
        ObjectPool.PACKET_POOL.add(pool)
        return remaining
    }

    fun interface PayloadConsumer {

        fun consume(id: Int, payload: ByteBuffer)
    }
}

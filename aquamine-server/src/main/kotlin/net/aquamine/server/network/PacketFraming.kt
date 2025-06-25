package net.aquamine.server.network

import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.FramedPacket
import net.aquamine.server.packet.Packet
import net.aquamine.server.packet.PacketRegistry
import net.aquamine.server.util.ObjectPool
import net.aquamine.server.util.writeEmptyVarIntHeader
import net.aquamine.server.util.writeVarIntHeader
import java.nio.ByteBuffer
import java.util.zip.Deflater

object PacketFraming {

    private val COMPRESSOR: ThreadLocal<Deflater> = ThreadLocal.withInitial { Deflater() }
    @Volatile
    private var compressionThreshold = 0

    @JvmStatic
    fun frame(packet: Packet): FramedPacket {
        ObjectPool.PACKET_POOL.hold().use { holder ->
            val temp = writeFramedPacket(holder.get(), packet)
            val size = temp.remaining()
            val buffer = ByteBuffer.allocateDirect(size).put(0, temp, 0, size)
            return FramedPacket(packet, buffer)
        }
    }

    @JvmStatic
    fun writeFramedPacket(buffer: ByteBuffer, packet: Packet): ByteBuffer = writeFramedPacket(buffer, packet, compressionThreshold > 0)

    @JvmStatic
    fun writeFramedPacket(buffer: ByteBuffer, packet: Packet, compressed: Boolean): ByteBuffer {
        val threshold = if (compressed) compressionThreshold else 0
        writeFramedPacket(buffer, PacketRegistry.getOutboundPacketId(packet.javaClass), packet, threshold)
        return buffer.flip()
    }

    @JvmStatic
    private fun writeFramedPacket(buffer: ByteBuffer, id: Int, writable: Writable, compressionThreshold: Int) {
        val writer = BinaryWriter(buffer)
        if (compressionThreshold <= 0) {
            val lengthIndex = buffer.writeEmptyVarIntHeader()
            writer.writeVarInt(id)
            writable.write(writer)
            val finalSize = buffer.position() - (lengthIndex + 3)
            buffer.writeVarIntHeader(lengthIndex, finalSize)
            return
        }
        val compressedIndex = buffer.writeEmptyVarIntHeader()
        val uncompressedIndex = buffer.writeEmptyVarIntHeader()

        val contentStart = buffer.position()
        writer.writeVarInt(id)
        writable.write(writer)

        val packetSize = buffer.position() - contentStart
        val compressed = packetSize >= compressionThreshold
        if (compressed) {
            ObjectPool.PACKET_POOL.hold().use { holder ->
                val input = holder.get().put(0, buffer, contentStart, packetSize)
                val compressor = COMPRESSOR.get()
                compressor.setInput(input.limit(packetSize))
                compressor.finish()
                compressor.deflate(buffer.position(contentStart))
                compressor.reset()
            }
        }

        buffer.writeVarIntHeader(compressedIndex, buffer.position() - uncompressedIndex)
        buffer.writeVarIntHeader(uncompressedIndex, if (compressed) packetSize else 0)
    }

    @JvmStatic
    fun setCompressionThreshold(threshold: Int) {
        compressionThreshold = threshold
    }
}

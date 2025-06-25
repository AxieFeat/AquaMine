package net.aquamine.server.packet

import net.aquamine.server.network.PacketFraming
import java.lang.ref.SoftReference
import java.nio.ByteBuffer
import java.util.function.Supplier

class CachedPacket(private val supplier: Supplier<Packet>) : GenericPacket {

    private var packet: SoftReference<FramedPacket>? = null

    fun packet(): Packet {
        val cache = updatedCache()
        return cache?.packet ?: supplier.get()
    }

    fun body(): ByteBuffer? {
        val cache = updatedCache()
        return cache?.body
    }

    fun invalidate() {
        packet = null
    }

    private fun updatedCache(): FramedPacket? {
        val ref = packet
        var cached: FramedPacket? = null
        if (ref == null || ref.get().also { cached = it } == null) {
            cached = PacketFraming.frame(supplier.get())
            packet = SoftReference(cached)
        }
        return cached
    }
}

package net.aquamine.server.network

import net.aquamine.server.AquaServer
import net.aquamine.server.commands.aqua.AquaColors
import net.aquamine.server.network.buffer.BinaryBuffer
import net.aquamine.server.network.handlers.HandshakePacketHandler
import net.aquamine.server.network.handlers.PacketHandler
import net.aquamine.server.network.handlers.TickablePacketHandler
import net.aquamine.server.network.interceptor.PacketInterceptorRegistry
import net.aquamine.server.network.socket.NetworkWorker
import net.aquamine.server.packet.*
import net.aquamine.server.packet.out.login.PacketOutSetCompression
import net.aquamine.server.packet.out.play.PacketOutDisconnect
import net.aquamine.server.util.ObjectPool
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import org.apache.logging.log4j.LogManager
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.ClosedChannelException
import java.nio.channels.SocketChannel
import java.security.Key
import java.util.concurrent.atomic.AtomicReference
import java.util.zip.DataFormatException
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.ShortBufferException
import javax.crypto.spec.IvParameterSpec
import kotlin.math.min

class NioConnection(
    private val server: AquaServer,
    private val worker: NetworkWorker,
    private val channel: SocketChannel,
    private var address: SocketAddress
) : NetworkConnection {

    private val workerQueue = worker.queue()

    private var latency = 0
    @Volatile
    private var currentState = PacketState.HANDSHAKE
    @Volatile
    private var connected = true
    @Volatile
    private var handler: PacketHandler = HandshakePacketHandler(server, this)

    @Volatile
    private var encryptionContext: EncryptionContext? = null
    @Volatile
    private var compressionEnabled = false

    private val waitingBuffers = ArrayList<BinaryBuffer>()
    private val tickBuffer = AtomicReference(ObjectPool.BUFFER_POOL.get())
    private var cacheBuffer: BinaryBuffer? = null

    override fun connectAddress(): SocketAddress = address

    fun setAddress(newAddress: SocketAddress) {
        address = newAddress
    }

    override fun latency(): Int = latency

    fun setState(newState: PacketState) {
        currentState = newState
    }

    fun enableCompression() {
        val threshold = server.config.server.compressionThreshold
        send(PacketOutSetCompression(threshold))
        compressionEnabled = true
    }

    fun enableEncryption(key: SecretKey) {
        val encryptCipher = createCipher(1, key)
        val decryptCipher = createCipher(2, key)
        encryptionContext = EncryptionContext(encryptCipher, decryptCipher)
    }

    fun tickHandler() {
        val handler = handler
        if (handler is TickablePacketHandler) handler.tick()
    }

    fun setHandler(handler: PacketHandler) {
        this.handler = handler
    }

    fun updateLatency(lastKeepAlive: Long) {
        latency = (latency * 3 + (System.currentTimeMillis() - lastKeepAlive).toInt()) / 3
    }

    fun processPackets(readBuffer: BinaryBuffer) {
        // Decrypt data if necessary
        val encryptionContext = encryptionContext
        if (encryptionContext != null) {
            val input = readBuffer.asByteBuffer(0, readBuffer.writerIndex())
            try {
                encryptionContext.decrypt.update(input, input.duplicate())
            } catch (exception: ShortBufferException) {
                LOGGER.error("Failed to decrypt packet!", exception)
                return
            }
        }

        // Read all packets
        try {
            cacheBuffer = PacketReading.readPackets(readBuffer, compressionEnabled) { id, payload ->
                if (!connected) return@readPackets // Prevent packet corruption

                var packet: InboundPacket<*>? = null
                try {
                    packet = processPacket(id, payload)
                } catch (exception: Exception) {
                    LOGGER.error("Failed to process packet with ID $id!", exception)
                } finally {
                    if (payload.position() != payload.limit()) {
                        LOGGER.warn("Packet 0x${Integer.toHexString(id)} was not fully read! Payload: $payload, Packet: $packet")
                    }
                }
            }
        } catch (exception: DataFormatException) {
            LOGGER.error("Failed to read packet!", exception)
            disconnect(INVALID_PACKET)
        }
    }

    private fun processPacket(packetId: Int, buffer: ByteBuffer): InboundPacket<*>? {
        val packet = getInboundPacket(packetId, buffer) ?: return null
        try {
            handleCap(packet, handler)
        } catch (_: ClassCastException) {
            // We could possibly throw and catch a different exception, however it's cleaner in the client code if we just try to do a generic
            // cast to the handler type and catch that if it fails.
            LOGGER.error("Received invalid packet from ${connectAddress()}!")
            disconnect(INVALID_PACKET)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            // TODO remake 'crash report'
            send(PacketOutDisconnect(
                Component.text("AquaMine", AquaColors.LIGHTER_PURPLE)
                    .append(Component.text(" | ", NamedTextColor.GRAY))
                    .append(Component.text("Crash report", NamedTextColor.WHITE))
                    .append(Component.text(" #71851", NamedTextColor.GRAY))
                    .appendNewline()
                    .appendNewline()
                    .append(
                        Component.text("What happened? ", TextColor.fromHexString("#eb3440")),
                        Component.text("Don't panic!", TextColor.fromHexString("#34eb6e"))
                            .appendNewline()
                            .appendNewline(),
                        Component.text("We just couldn't handle your connection,", TextColor.fromHexString("#f2fff4"))
                            .appendNewline(),
                        Component.text("please ", TextColor.fromHexString("#f2fff4"))
                            .append(Component.text("send this report to support", AquaColors.VIVID_SKY_BLUE))
                            .append(Component.text(":", TextColor.fromHexString("#f2fff4")))
                            .appendNewline()
                            .appendNewline()
                            .appendNewline()
                    )
                    .append(
                        Component.text("${packet::class.simpleName} ", AquaColors.STANDARD_PURPLE),
                        Component.text("(0x${Integer.toHexString(packetId)} $currentState)", NamedTextColor.GRAY),
                        Component.newline().appendNewline(),
                        Component.text("${throwable::class.java.packageName}.", NamedTextColor.GRAY),
                        Component.text("${throwable::class.simpleName}", TextColor.fromHexString("#eb9f34")),
                        Component.text(": ", NamedTextColor.GRAY),
                        Component.text("${throwable.message}", TextColor.fromHexString("#f2fff4")).appendNewline(),
                        Component.text("at ${
                            run {
                                val element = throwable.stackTrace.first()
                                "${element.className.split(".").last()}.${element.methodName}() (${element.fileName}:${element.lineNumber})"
                            }
                        }", NamedTextColor.DARK_GRAY)
                    )
                    .appendNewline()
                    .appendNewline()
                    .appendNewline()
                    .append(
                        Component.text("Discord: ", TextColor.fromHexString("#f2fff4"))
                            .append(Component.text("discord.aquamine.net", TextColor.fromHexString("#346eeb")))
                    )
            ))
            disconnect(Component.text("Some exception during handling packet"))
        }
        return packet
    }

    private fun getInboundPacket(id: Int, buffer: ByteBuffer): InboundPacket<*>? {
        val packet = PacketRegistry.getInboundPacket(currentState, id, buffer)
            ?: error("Received unknown packet with ID $id in state $currentState!")

        val interceptors = PacketInterceptorRegistry.all()
        if (interceptors.isEmpty()) return packet

        var result = packet
        for (interceptor in interceptors) {
            result = interceptor.onReceive(this, result) ?: return null
        }
        return result
    }

    fun consumeCache(buffer: BinaryBuffer) {
        val cache = cacheBuffer
        if (cache != null) {
            buffer.write(cache)
            cacheBuffer = null
        }
    }

    override fun disconnect(reason: Component?) {
        connected = false
        workerQueue.relaxedOffer {
            worker.disconnect(this, channel)
            val tick = tickBuffer.getAndSet(null)
            if (tick != null) ObjectPool.BUFFER_POOL.add(tick)
            for (buffer in waitingBuffers) {
                ObjectPool.BUFFER_POOL.add(buffer)
            }
            waitingBuffers.clear()
            handler.onDisconnect(reason)
        }
    }

    fun flush() {
        val channel = this.channel
        if (!channel.isConnected) throw ClosedChannelException()

        val waitingBuffers = this.waitingBuffers
        if (waitingBuffers.isEmpty()) {
            val localBuffer = tickBuffer.plain ?: return // Socket is closed
            localBuffer.writeChannel(channel)
            return
        }

        val iterator = waitingBuffers.iterator()
        while (iterator.hasNext()) {
            val waitingBuffer = iterator.next()
            if (!waitingBuffer.writeChannel(channel)) break
            iterator.remove()
            ObjectPool.BUFFER_POOL.add(waitingBuffer)
        }
    }

    override fun write(packet: GenericPacket) {
        val compressed = compressionEnabled
        workerQueue.relaxedOffer { writePacketSync(packet, compressed) }
    }

    private fun writePacketSync(packet: GenericPacket, compressed: Boolean) {
        if (!channel.isConnected) return
        val modified = modifyOutboundPacket(packet) ?: return
        when (modified) {
            is Packet -> writeStandardPacketSync(modified, compressed)
            is FramedPacket -> {
                val buffer = modified.body
                writeBufferSync(buffer, 0, buffer.limit())
            }
            is CachedPacket -> {
                val buffer = modified.body()
                if (buffer != null) {
                    writeBufferSync(buffer, buffer.position(), buffer.remaining())
                } else {
                    writeStandardPacketSync(modified.packet(), compressed)
                }
            }
            else -> error("Unknown packet type $modified!")
        }
    }

    private fun modifyOutboundPacket(packet: GenericPacket): GenericPacket? {
        val interceptors = PacketInterceptorRegistry.all()
        if (interceptors.isEmpty()) return packet

        var result = packet
        for (interceptor in interceptors) {
            result = interceptor.onSend(this, result) ?: return null
        }
        return result
    }

    private fun writeStandardPacketSync(packet: Packet, compressed: Boolean) {
        ObjectPool.PACKET_POOL.hold().use { holder ->
            val buffer = PacketFraming.writeFramedPacket(holder.get(), packet, compressed)
            writeBufferSync(buffer, 0, buffer.limit())
        }
    }

    private fun writeBufferSync(buffer: ByteBuffer, index: Int, length: Int) {
        val encryptionContext = encryptionContext
        if (encryptionContext == null) {
            writeBufferSync0(buffer, index, length)
            return
        }
        ObjectPool.PACKET_POOL.hold().use { holder ->
            val output = holder.get()
            try {
                val newLength = encryptionContext.encrypt.update(buffer.slice(index, length), output)
                writeBufferSync0(output, 0, newLength)
            } catch (exception: ShortBufferException) {
                LOGGER.error("Failed to encrypt packet!", exception)
            }
            return
        }
    }

    private fun writeBufferSync0(buffer: ByteBuffer, index: Int, length: Int) {
        var localBuffer = tickBuffer.plain ?: return // Socket is closed
        val capacity = localBuffer.capacity()
        if (length <= capacity) {
            if (!localBuffer.canWrite(length)) localBuffer = updateLocalBuffer()
            localBuffer.write(buffer, index, length)
            return
        }

        val bufferCount = length / capacity + 1
        for (i in 0 until bufferCount) {
            val sliceStart = i * capacity
            val sliceLength = min(length, sliceStart + capacity) - sliceStart
            if (!localBuffer.canWrite(sliceLength)) localBuffer = updateLocalBuffer()
            localBuffer.write(buffer, sliceStart, sliceLength)
        }
    }

    private fun updateLocalBuffer(): BinaryBuffer {
        val newBuffer = ObjectPool.BUFFER_POOL.get()
        waitingBuffers.add(tickBuffer.plain)
        tickBuffer.plain = newBuffer
        return newBuffer
    }

    @JvmRecord
    private data class EncryptionContext(val encrypt: Cipher, val decrypt: Cipher)

    companion object {

        private val LOGGER = LogManager.getLogger()
        private val INVALID_PACKET = Component.translatable("multiplayer.disconnect.invalid_packet")

        @JvmStatic
        private fun <H : PacketHandler> handleCap(packet: InboundPacket<H>, handler: PacketHandler) {
            @Suppress("UNCHECKED_CAST")
            packet.handle(handler as H)
        }

        @JvmStatic
        private fun createCipher(mode: Int, key: Key): Cipher {
            val cipher = Cipher.getInstance("AES/CFB8/NoPadding")
            cipher.init(mode, key, IvParameterSpec(key.encoded))
            return cipher
        }
    }
}

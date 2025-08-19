package net.aquamine.server.network.socket

import net.kyori.adventure.text.Component
import org.apache.logging.log4j.LogManager
import org.jctools.queues.MessagePassingQueue
import org.jctools.queues.MpscUnboundedXaddArrayQueue
import net.aquamine.server.AquaServer
import net.aquamine.server.network.NioConnection
import net.aquamine.server.network.buffer.BinaryBuffer
import net.aquamine.server.ticking.AquaThread
import net.aquamine.server.ticking.TickSchedulerThread
import net.aquamine.server.util.ObjectPool
import java.io.IOException
import java.net.InetSocketAddress
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.SocketChannel
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class NetworkWorker(
    private val server: AquaServer,
    private val networkServer: NetworkServer
) : AquaThread("Network Worker ${COUNTER.getAndIncrement()}") {

    val selector: Selector = Selector.open()
    private val connections = ConcurrentHashMap<SocketChannel, NioConnection>()
    private val queue = MpscUnboundedXaddArrayQueue<Runnable>(1024)

    override fun run() {
        while (networkServer.isOpen()) {
            try {
                try {
                    queue.drain { it.run() }
                } catch (exception: IOException) {
                    LOGGER.error("Error while writing packets!", exception)
                }
                for (connection in connections.values) {
                    try {
                        connection.tickHandler()
                        connection.flush()
                    } catch (_: IOException) {
                        connection.disconnect(null)
                    } catch (exception: Exception) {
                        LOGGER.error("Error while reading packets!", exception)
                        disconnectOnError(connection, exception)
                    }
                }
                selector.select({ key ->
                    val channel = key.channel() as SocketChannel
                    if (!channel.isOpen) return@select
                    if (!key.isReadable) return@select

                    val connection = connections.get(channel)
                    if (connection == null) {
                        try {
                            channel.close()
                        } catch (_: IOException) {
                            // Channel failed to close. This won't make a difference to anything, so we don't care.
                        }
                        return@select
                    }

                    try {
                        ObjectPool.PACKET_POOL.hold().use { holder ->
                            val readBuffer = BinaryBuffer.wrap(holder.get())
                            connection.consumeCache(readBuffer)
                            readBuffer.readChannel(channel)
                            connection.processPackets(readBuffer)
                        }
                    } catch (_: IOException) {
                        connection.disconnect(null)
                    } catch (exception: Throwable) {
                        LOGGER.error("Error while processing packets!", exception)
                        connection.disconnect(Component.translatable("disconnect.genericReason", Component.text("Internal Exception: $exception")))
                    }
                }, TickSchedulerThread.MILLIS_PER_TICK)
            } catch (exception: Exception) {
                LOGGER.error("Error while processing worker!", exception)
            }
        }
    }

    private fun disconnectOnError(connection: NioConnection, exception: Throwable) {
        connection.disconnect(Component.translatable("disconnect.genericReason", Component.text("Internal Exception: $exception")))
    }

    fun disconnect(connection: NioConnection, channel: SocketChannel) {
        connections.remove(channel)
        if (channel.isOpen) {
            try {
                connection.flush()
                channel.close()
            } catch (_: IOException) {
                // Socket operation may fail if the socket is already closed
            }
        }
    }

    fun handleConnection(channel: SocketChannel) {
        connections.put(channel, NioConnection(server, this, channel, channel.remoteAddress))
        channel.configureBlocking(false)
        channel.register(selector, SelectionKey.OP_READ)
        if (channel.localAddress is InetSocketAddress) {
            val socket = channel.socket()
            socket.sendBufferSize = NetworkServer.SOCKET_SEND_BUFFER_SIZE
            socket.receiveBufferSize = NetworkServer.SOCKET_RECEIVE_BUFFER_SIZE
            socket.tcpNoDelay = true
            socket.soTimeout = 30 * 1000 // 30 seconds
        }
        selector.wakeup()
    }

    fun queue(): MessagePassingQueue<Runnable> = queue

    companion object {

        private val LOGGER = LogManager.getLogger()
        private val COUNTER = AtomicInteger()
    }
}

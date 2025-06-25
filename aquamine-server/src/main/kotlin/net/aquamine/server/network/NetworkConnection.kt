package net.aquamine.server.network

import net.kyori.adventure.text.Component
import net.aquamine.server.packet.GenericPacket
import net.aquamine.server.packet.Packet
import java.net.SocketAddress

interface NetworkConnection {

    fun connectAddress(): SocketAddress

    fun latency(): Int

    fun send(packet: Packet) {
        write(packet)
    }

    fun write(packet: GenericPacket)

    fun disconnect(reason: Component?)
}

package net.aquamine.server.packet

import net.aquamine.server.network.handlers.PacketHandler
import net.aquamine.server.network.socket.NetworkServer

interface InboundPacket<H : PacketHandler> : Packet {

    fun handle(handler: H)

    fun handle(handler: H, server: NetworkServer) {}
}

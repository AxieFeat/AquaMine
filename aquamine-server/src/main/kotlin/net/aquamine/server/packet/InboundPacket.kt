package net.aquamine.server.packet

import net.aquamine.server.network.handlers.PacketHandler

interface InboundPacket<H : PacketHandler> : Packet {

    fun handle(handler: H)
}

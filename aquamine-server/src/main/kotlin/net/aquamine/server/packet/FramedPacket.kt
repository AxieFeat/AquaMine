package net.aquamine.server.packet

import java.nio.ByteBuffer

class FramedPacket(val packet: Packet, body: ByteBuffer) : GenericPacket {

    val body: ByteBuffer = body.position(0).asReadOnlyBuffer()
}

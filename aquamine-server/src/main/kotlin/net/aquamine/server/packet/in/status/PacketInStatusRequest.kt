package net.aquamine.server.packet.`in`.status

import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.StatusPacketHandler
import net.aquamine.server.network.socket.NetworkServer
import net.aquamine.server.packet.InboundPacket

/**
 * Sent by the client to request the server's status information.
 */
object PacketInStatusRequest : InboundPacket<StatusPacketHandler> {

    override fun write(writer: BinaryWriter) {
        // there is nothing to write here, and nothing is read for this either
    }

    override fun handle(handler: StatusPacketHandler) {

    }

    override fun handle(handler: StatusPacketHandler, server: NetworkServer) {
        handler.handleStatusRequest(server)
    }
}

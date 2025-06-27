package net.aquamine.server.network.handlers

import net.kyori.adventure.text.Component
import net.aquamine.server.AquaServer
import net.aquamine.server.packet.`in`.status.PacketInPingRequest
import net.aquamine.server.packet.out.status.PacketOutPingResponse
import net.aquamine.server.packet.out.status.PacketOutStatusResponse
import net.aquamine.server.network.NioConnection
import net.aquamine.server.packet.out.login.PacketOutLoginDisconnect

/**
 * Handles all inbound packets in the
 * [Status][net.aquamine.server.packet.PacketState.STATUS] state.
 *
 * There are two packets in this state that we handle:
 * - [Status request][net.aquamine.server.packet. in.status.PacketInStatusRequest] -
 *   sent by the client to request status information
 * - [Ping][net.aquamine.server.packet. in.status.PacketInPing] -
 *   pings the server (to calculate latency on its end)
 */
class StatusPacketHandler(private val server: AquaServer, private val connection: NioConnection) : PacketHandler {

    private var requestedStatus = false

    fun handleStatusRequest() {
        if (requestedStatus) {
            disconnect()
            return
        }
        requestedStatus = true
        connection.send(PacketOutStatusResponse.create(server.statusManager.status()))
    }

    fun handlePing(packet: PacketInPingRequest) {
        connection.send(PacketOutPingResponse(packet.payload))
        disconnect()
    }

    private fun disconnect() {
        connection.send(PacketOutLoginDisconnect(REQUEST_HANDLED))
        connection.disconnect(REQUEST_HANDLED)
    }

    companion object {

        private val REQUEST_HANDLED = Component.translatable("multiplayer.status.request_handled")
    }
}

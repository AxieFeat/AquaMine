package net.aquamine.server.network.interceptor

import net.aquamine.server.network.NioConnection
import net.aquamine.server.network.handlers.PacketHandler
import net.aquamine.server.packet.GenericPacket
import net.aquamine.server.packet.InboundPacket

/**
 * This interface is used to enable the interception of packets, both inbound and outbound.
 *
 * There are two hooks present in this interface, [onSend] and [onReceive]. These hooks are called when
 * their respective events happen, e.g. onSend when a packet is sent.
 */
interface PacketInterceptor {

    /**
     * Called when a packet is sent to a client.
     *
     * This works in a specific way, with the following rules:
     * - If you do not wish to modify the packet, return it.
     * - If you do not want the packet to be sent, return `null`.
     * - If you want to modify the packet, return the modified packet.
     */
    fun onSend(connection: NioConnection, packet: GenericPacket): GenericPacket?

    /**
     * Called when a packet is received from a client.
     *
     * This works in a specific way, with the following rules:
     * - If you do not wish to modify the packet, return it.
     * - If you do not want the packet to be processed, return `null`.
     * - If you want to modify the packet, return the modified packet.
     */
    fun <H : PacketHandler> onReceive(connection: NioConnection, packet: InboundPacket<H>): InboundPacket<H>?
}

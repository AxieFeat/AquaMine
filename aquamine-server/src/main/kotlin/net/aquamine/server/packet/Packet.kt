package net.aquamine.server.packet

import net.aquamine.server.network.Writable

/**
 * Super interface for all inbound and outbound packets.
 */
interface Packet : GenericPacket, Writable

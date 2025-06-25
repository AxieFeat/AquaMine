package net.aquamine.server.packet

/**
 * A superinterface for all types of packets, including ones that don't have a
 * [Packet.write] function, like [FramedPacket] and [CachedPacket].
 */
interface GenericPacket

package net.aquamine.server.packet

/**
 * A packet that contains an entity ID.
 */
interface EntityPacket : Packet {

    /**
     * The ID of the entity this packet is intended for.
     */
    val entityId: Int
}

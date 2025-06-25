package net.aquamine.server.packet

/**
 * A packet that signals movement.
 */
interface MovementPacket : Packet {

    /**
     * If the target of this movement packet is currently on terra firma.
     */
    val onGround: Boolean
}

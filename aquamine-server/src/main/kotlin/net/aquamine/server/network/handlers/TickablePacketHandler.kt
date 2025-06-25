package net.aquamine.server.network.handlers

interface TickablePacketHandler : PacketHandler {

    fun tick()
}

package net.aquamine.server.network

import net.aquamine.server.KryptonServer
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.packet.CachedPacket
import net.aquamine.server.packet.Packet
import java.util.function.Predicate

/**
 * Utility for sending grouped packets to players.
 *
 * Grouped packets are packets that are fully encoded, framed, and compressed once, and sent
 * to multiple players, which is much faster than encoding, framing, and especially
 * compressing the packet individually for each player.
 */
object PacketGrouping {

    @JvmStatic
    fun sendGroupedPacket(server: KryptonServer, packet: Packet) {
        sendGroupedPacket(server.playerManager.players(), packet, null)
    }

    @JvmStatic
    fun sendGroupedPacket(server: KryptonServer, packet: Packet, predicate: Predicate<KryptonPlayer>) {
        sendGroupedPacket(server.playerManager.players(), packet, predicate)
    }

    @JvmStatic
    fun sendGroupedPacket(players: Collection<KryptonPlayer>, packet: Packet) {
        sendGroupedPacket(players, packet, null)
    }

    @JvmStatic
    fun sendGroupedPacket(players: Collection<KryptonPlayer>, packet: Packet, predicate: Predicate<KryptonPlayer>?) {
        if (players.isEmpty()) {
            // Fast exit if there are no players to send to. This avoids encoding the packet entirely.
            return
        }

        val cachedPacket = CachedPacket { packet }
        players.forEach { player ->
            if (!player.isOnline()) return@forEach
            if (predicate == null || predicate.test(player)) player.connection.write(cachedPacket)
        }
    }
}

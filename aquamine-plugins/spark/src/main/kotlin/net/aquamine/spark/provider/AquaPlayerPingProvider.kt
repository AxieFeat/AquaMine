package net.aquamine.spark.provider

import me.lucko.spark.common.monitor.ping.PlayerPingProvider
import net.aquamine.api.Server

class AquaPlayerPingProvider(
    val server: Server
) : PlayerPingProvider {

    override fun poll(): Map<String, Int> {
        return server.players.associateWith { it.ping }.mapKeys { it.key.name }
    }
}

package net.aquamine.spark

import me.lucko.spark.common.monitor.ping.PlayerPingProvider
import net.aquamine.api.Server

class AquaPingProvider(
    val server: Server,
) : PlayerPingProvider {

    override fun poll(): Map<String, Int> {
        return server.players.associateWith { it.ping }.mapKeys { it.key.name }
    }

}
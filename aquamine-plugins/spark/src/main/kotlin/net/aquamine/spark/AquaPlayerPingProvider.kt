package net.aquamine.spark

import me.lucko.spark.common.monitor.ping.PlayerPingProvider
import net.aquamine.api.Server

class AquaPlayerPingProvider(
    val server: Server
) : PlayerPingProvider {

    override fun poll(): Map<String, Int> {
        return buildMap {
            server.players.forEach {
                put(it.name, it.ping)
            }
        }
    }

}
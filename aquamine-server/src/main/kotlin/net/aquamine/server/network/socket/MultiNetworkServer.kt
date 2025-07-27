package net.aquamine.server.network.socket

import net.aquamine.server.AquaServer
import org.apache.logging.log4j.LogManager
import java.net.SocketAddress

class MultiNetworkServer(
    private val server: AquaServer
) {

    val servers: MutableList<NetworkServer> = mutableListOf()

    fun initialize(vararg address: SocketAddress) {
        LOGGER.info("Initializing ${address.size} servers...")

        address.forEach { address ->
            servers.add(NetworkServer(server).also { it.initialize(address) })
        }
    }

    fun start() {
        servers.forEach { it.start() }
    }

    fun stop() {
        servers.forEach { it.stop() }
    }

    fun tryCloseUnusedServers() {
        if(servers.size <= 1) return

        servers.forEach { server ->
            if(server.connectionsCount <= 0) {
                server.stop()
            }
        }
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }

}
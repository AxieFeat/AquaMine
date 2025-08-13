package net.aquamine.client

import net.aquamine.client.server.IntegratedServerBootstrap

fun main(args: Array<String>) {
    val server = IntegratedServerBootstrap().startServer()
    val client = AquaClient(server)

    client.initialize()
}
package net.aquamine.server.entity.components

import net.aquamine.server.network.NetworkConnection

interface NetworkPlayer {

    val connection: NetworkConnection
}

package net.aquamine.server.network.forwarding

import net.aquamine.api.auth.ProfileProperty
import java.net.InetAddress
import java.util.UUID

@JvmRecord
data class VelocityForwardedData(
    val remoteAddress: InetAddress,
    val uuid: UUID,
    val username: String,
    val properties: List<ProfileProperty>
)

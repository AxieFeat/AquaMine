package net.aquamine.server.network.forwarding

import kotlinx.collections.immutable.PersistentList
import net.aquamine.api.auth.ProfileProperty
import java.util.UUID

interface ProxyForwardedData {

    val forwardedAddress: String
    val forwardedPort: Int

    val uuid: UUID?
        get() = null
    val properties: PersistentList<ProfileProperty>?
        get() = null
}

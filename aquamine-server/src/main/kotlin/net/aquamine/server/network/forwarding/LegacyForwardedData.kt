package net.aquamine.server.network.forwarding

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import net.aquamine.api.auth.ProfileProperty
import net.aquamine.server.auth.AquaProfileProperty
import net.aquamine.server.util.uuid.MojangUUIDTypeAdapter
import java.util.UUID

@JvmRecord
data class LegacyForwardedData(
    val originalAddress: String,
    override val forwardedAddress: String,
    override val uuid: UUID,
    override val properties: PersistentList<ProfileProperty>
) : ProxyForwardedData {

    override val forwardedPort: Int
        get() = -1

    companion object {

        @JvmStatic
        fun parse(string: String): LegacyForwardedData? {
            val split = string.split('\u0000')
            // We need to have the original IP, forwarded IP, and the UUID at bare minimum.
            if (split.size < 3) return null
            val properties = if (split.size > 3) AquaProfileProperty.Adapter.readJsonList(split.get(3)) else persistentListOf()
            return LegacyForwardedData(split.get(0), split.get(1), MojangUUIDTypeAdapter.fromString(split.get(2)), properties)
        }
    }
}

package net.aquamine.server.user

import net.aquamine.api.auth.GameProfile
import net.aquamine.api.entity.player.Player
import net.aquamine.api.user.User
import net.aquamine.server.AquaServer
import xyz.axie.nbt.CompoundTag
import java.time.Instant
import java.util.UUID

class AquaUser(override val profile: GameProfile, var data: CompoundTag, private val server: AquaServer) : User {

    private var firstJoinedCached: Instant? = null
    private var lastJoinedCached: Instant? = null

    override val name: String
        get() = profile.name
    override val uuid: UUID
        get() = profile.uuid
    override val hasJoinedBefore: Boolean
        get() = asPlayer()?.hasJoinedBefore ?: !data.isEmpty()
    override val firstJoined: Instant
        get() = asPlayer()?.firstJoined ?: getAndUpdateJoined("firstJoined", firstJoinedCached) { firstJoinedCached = it }
    override val lastJoined: Instant
        get() = asPlayer()?.lastJoined ?: getAndUpdateJoined("lastJoined", lastJoinedCached) { lastJoinedCached = it }

    override fun isOnline(): Boolean = asPlayer() != null

    override fun asPlayer(): Player? = server.getPlayer(uuid)

    private inline fun getAndUpdateJoined(key: String, cached: Instant?, updateCached: (Instant) -> Unit): Instant {
        if (cached != null) return cached
        val instant = Instant.ofEpochMilli(data.getLong(key))
        updateCached(instant)
        return instant
    }
}

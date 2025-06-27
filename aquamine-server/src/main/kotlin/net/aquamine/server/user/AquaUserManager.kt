package net.aquamine.server.user

import com.google.common.collect.MapMaker
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.user.User
import net.aquamine.api.user.UserManager
import net.aquamine.server.AquaServer
import net.aquamine.server.auth.requests.ApiService
import net.aquamine.server.util.executor.daemonThreadFactory
import net.aquamine.server.world.data.PlayerDataSerializer
import xyz.axie.nbt.CompoundTag
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

class AquaUserManager(private val server: AquaServer, private val dataSerializer: PlayerDataSerializer) : UserManager {

    private val users: MutableMap<UUID, AquaUser> = MapMaker().weakValues().makeMap()
    private val executor = Executors.newSingleThreadExecutor(daemonThreadFactory("AquaMine User Data Loader"))

    fun updateUser(uuid: UUID, data: CompoundTag) {
        users.get(uuid)?.data = data
    }

    override fun getUser(uuid: UUID): User? = users.get(uuid)

    override fun getUser(name: String): User? {
        val profile = server.profileCache.getProfile(name) ?: return null
        return users.get(profile.uuid)
    }

    override fun loadUser(uuid: UUID): CompletableFuture<User?> {
        val existing = getUser(uuid)
        if (existing != null) return CompletableFuture.completedFuture(existing)
        val cachedProfile = server.profileCache.getProfile(uuid)
        if (cachedProfile != null) return CompletableFuture.supplyAsync({ loadUser(cachedProfile) }, executor)
        return ApiService.lookupProfileById(uuid).thenApplyAsync({ loadUser(it) }, executor)
    }

    override fun loadUser(name: String): CompletableFuture<User?> {
        val existing = getUser(name)
        if (existing != null) return CompletableFuture.completedFuture(existing)
        val cachedProfile = server.profileCache.getProfile(name)
        if (cachedProfile != null) return CompletableFuture.supplyAsync({ loadUser(cachedProfile) }, executor)
        return ApiService.lookupProfileByName(name).thenApplyAsync({ loadUser(it) }, executor)
    }

    private fun loadUser(profile: GameProfile?): User? {
        if (profile == null) return null
        val nbt = dataSerializer.loadById(profile.uuid) ?: return null

        return AquaUser(profile, nbt, server)
    }
}

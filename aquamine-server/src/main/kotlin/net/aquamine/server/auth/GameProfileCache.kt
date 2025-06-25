package net.aquamine.server.auth

import com.google.common.collect.Collections2
import com.google.common.collect.Lists
import org.apache.logging.log4j.LogManager
import net.aquamine.api.auth.GameProfile
import net.aquamine.server.util.gson.array
import net.aquamine.server.util.gson.jsonReader
import net.aquamine.server.util.gson.jsonWriter
import net.aquamine.server.util.gson.readListTo
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.time.ZonedDateTime
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

class GameProfileCache(private val path: Path) : Iterable<GameProfile> {

    private val profilesByName = ConcurrentHashMap<String, ProfileHolder>()
    private val profilesByUUID = ConcurrentHashMap<UUID, ProfileHolder>()
    private val operations = AtomicLong()
    private var dirty = false

    fun addProfile(profile: GameProfile) {
        addHolder(ProfileHolder(profile, ZonedDateTime.now().plusMonths(1)))
    }

    fun getProfile(name: String): GameProfile? = updateAndGetProfile(profilesByName.get(name))

    fun getProfile(uuid: UUID): GameProfile? = updateAndGetProfile(profilesByUUID.get(uuid))

    private fun updateAndGetProfile(holder: ProfileHolder?): GameProfile? {
        if (holder == null) return null
        holder.setLastAccess(operations.incrementAndGet())
        return holder.profile
    }

    override fun iterator(): Iterator<GameProfile> {
        return Collections2.transform(profilesByUUID.values) { it.profile }.iterator()
    }

    private fun addHolder(holder: ProfileHolder) {
        holder.setLastAccess(operations.incrementAndGet())
        profilesByName.put(holder.profile.name, holder)
        profilesByUUID.put(holder.profile.uuid, holder)
        markDirty()
    }

    fun loadAll() {
        if (!Files.exists(path)) return
        val holders = ArrayList<ProfileHolder>()
        try {
            path.jsonReader().use { it.readListTo(holders, ProfileHolder.Adapter::read) }
        } catch (exception: IOException) {
            LOGGER.warn("Failed to read $path. You can delete it to force the server to recreate it.", exception)
        } catch (exception: IllegalStateException) {
            LOGGER.warn("Failed to parse JSON data from $path. You can delete it to force the server to recreate it.", exception)
        }
        if (holders.isEmpty()) return
        Lists.reverse(holders).forEach(::addHolder)
    }

    fun saveIfNeeded() {
        if (dirty) {
            save()
            dirty = false
        }
    }

    fun save() {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path)
            } catch (exception: IOException) {
                LOGGER.warn("Failed to create profile cache file $path.", exception)
                return
            }
        }
        try {
            path.jsonWriter().use { it.array(profilesByUUID.values.stream().sorted().limit(MRU_LIMIT), ProfileHolder.Adapter::write) }
        } catch (exception: IOException) {
            LOGGER.error("Error writing user cache file!", exception)
        }
    }

    private fun markDirty() {
        dirty = true
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
        private const val MRU_LIMIT = 1000L
    }
}

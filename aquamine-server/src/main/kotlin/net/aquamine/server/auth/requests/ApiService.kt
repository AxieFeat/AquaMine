package net.aquamine.server.auth.requests

import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Caffeine
import net.aquamine.api.auth.GameProfile
import net.aquamine.server.auth.AquaGameProfile
import net.aquamine.server.util.uuid.MojangUUIDTypeAdapter
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.time.Duration
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

/**
 * Used to make requests to the Mojang API.
 */
object ApiService {

    private const val USERNAME_BASE_URL = "https://api.mojang.com/users/profiles/minecraft/"
    private const val UUID_BASE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/"
    private val EXPIRE = Duration.ofMinutes(5)

    private val client = HttpClient.newHttpClient()
    private val usernameCache = createCache<String> { USERNAME_BASE_URL + it }
    private val uuidCache = createCache<UUID> { UUID_BASE_URL + MojangUUIDTypeAdapter.toString(it) }

    /**
     * Requests the profile with the given [name] from the Mojang API.
     */
    @JvmStatic
    fun lookupProfileByName(name: String): CompletableFuture<GameProfile?> = usernameCache.get(name)

    /**
     * Requests the profile with the given [uuid] from the Mojang API.
     */
    @JvmStatic
    fun lookupProfileById(uuid: UUID): CompletableFuture<GameProfile?> = uuidCache.get(uuid)

    @JvmStatic
    private fun loadProfile(url: String, executor: Executor): CompletableFuture<GameProfile?> =
        client.sendAsync(HttpRequest.newBuilder(URI(url)).build(), BodyHandlers.ofString())
            .thenApplyAsync({ AquaGameProfile.Adapter.fromJson(it.body()) }, executor)

    @JvmStatic
    private inline fun <K : Any> createCache(crossinline toUrl: (K) -> String): AsyncLoadingCache<K, GameProfile?> =
        Caffeine.newBuilder().expireAfterWrite(EXPIRE).maximumSize(128).buildAsync { key, executor -> loadProfile(toUrl(key), executor) }
}

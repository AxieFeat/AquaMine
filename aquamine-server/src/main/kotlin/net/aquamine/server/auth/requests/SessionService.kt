package net.aquamine.server.auth.requests

import com.github.benmanes.caffeine.cache.Caffeine
import org.apache.logging.log4j.LogManager
import net.aquamine.api.auth.GameProfile
import net.aquamine.server.auth.AquaGameProfile
import net.aquamine.server.util.crypto.Encryption
import java.math.BigInteger
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.security.MessageDigest
import java.security.PublicKey
import java.util.concurrent.TimeUnit

/**
 * Used for authenticating users and caching their profiles.
 */
object SessionService {

    private const val OK_RESPONSE_CODE = 200 // HTTP response code for 'OK' response.
    private const val BASE_URL = "https://sessionserver.mojang.com/session/minecraft/hasJoined"
    private val LOGGER = LogManager.getLogger()

    private val client = HttpClient.newHttpClient()
    private val profiles = Caffeine.newBuilder().expireAfterWrite(6, TimeUnit.HOURS).maximumSize(128).build<String, GameProfile>()

    @JvmStatic
    fun encodeServerInfo(serverId: String, secret: ByteArray, publicKey: PublicKey): String {
        val shaDigest = MessageDigest.getInstance("SHA-1")
        shaDigest.update(serverId.encodeToByteArray())
        shaDigest.update(secret)
        shaDigest.update(publicKey.encoded)
        return hexDigest(shaDigest)
    }

    /**
     * Authenticates a user with Mojang.
     */
    @JvmStatic
    fun hasJoined(username: String, secret: ByteArray, ip: String): GameProfile? {
        val cachedProfile = profiles.getIfPresent(username)
        if (cachedProfile != null) return cachedProfile

        val serverId = encodeServerInfo("", secret, Encryption.publicKey)
        val request = HttpRequest.newBuilder(URI("$BASE_URL?username=$username&serverId=$serverId&ip=$ip")).build()
        val response = client.send(request, BodyHandlers.ofString())
        if (response.statusCode() != OK_RESPONSE_CODE) { // Ensures no content responses are also a failure.
            LOGGER.debug("Error authenticating $username! Code: ${response.statusCode()}, body: ${response.body()}")
            LOGGER.error("Failed to verify username $username!")
            return null
        }

        val profile = AquaGameProfile.Adapter.fromJson(response.body())
        LOGGER.info("UUID of player ${profile.name} is ${profile.uuid}.")
        profiles.put(profile.name, profile)
        return profile
    }

    @JvmStatic
    private fun hexDigest(digest: MessageDigest): String = BigInteger(digest.digest()).toString(16)
}

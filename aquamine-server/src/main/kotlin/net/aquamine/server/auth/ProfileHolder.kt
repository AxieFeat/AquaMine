package net.aquamine.server.auth

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.UUID
import net.aquamine.api.auth.GameProfile

/**
 * This holds a game profile and other information that we need when storing
 * it, such as the expiry date, and the last time it was accessed.
 *
 * The reason why we store the last access time is so we can order profiles by
 * it, because we only store the [GameProfileCache.MRU_LIMIT] most recent
 * profiles to disk.
 */
class ProfileHolder(val profile: GameProfile, val expiryDate: ZonedDateTime) : Comparable<ProfileHolder> {

    @Volatile
    private var lastAccess = 0L

    fun setLastAccess(value: Long) {
        lastAccess = value
    }

    override fun compareTo(other: ProfileHolder): Int = other.lastAccess.compareTo(lastAccess)

    override fun equals(other: Any?): Boolean =
        this === other || other is ProfileHolder && profile == other.profile && expiryDate == other.expiryDate

    override fun hashCode(): Int {
        var result = 1
        result = 31 * result + profile.hashCode()
        result = 31 * result + expiryDate.hashCode()
        return result
    }

    override fun toString(): String = "ProfileHolder(profile=$profile, expiryDate=$expiryDate)"

    object Adapter : TypeAdapter<ProfileHolder>() {

        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z")

        override fun read(reader: JsonReader): ProfileHolder? {
            reader.beginObject()

            var uuid: UUID? = null
            var name: String? = null
            var expiryDate: ZonedDateTime? = null
            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "uuid" -> uuid = UUID.fromString(reader.nextString())
                    "name" -> name = reader.nextString()
                    "expiresOn" -> expiryDate = try {
                        ZonedDateTime.parse(reader.nextString(), DATE_FORMATTER)
                    } catch (ignored: DateTimeParseException) {
                        null
                    }
                }
            }

            reader.endObject()
            if (uuid == null || name == null || expiryDate == null) return null
            return ProfileHolder(AquaGameProfile.basic(uuid, name), expiryDate)
        }

        override fun write(writer: JsonWriter, value: ProfileHolder) {
            writer.beginObject()
            writer.name("uuid")
            writer.value(value.profile.uuid.toString())
            writer.name("name")
            writer.value(value.profile.name)
            writer.name("expiresOn")
            writer.value(DATE_FORMATTER.format(value.expiryDate))
            writer.endObject()
        }
    }
}

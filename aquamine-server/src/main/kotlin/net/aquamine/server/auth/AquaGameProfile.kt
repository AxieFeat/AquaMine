package net.aquamine.server.auth

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.auth.ProfileProperty
import net.aquamine.server.util.uuid.MojangUUIDTypeAdapter
import net.aquamine.server.util.uuid.UUIDUtil
import net.aquamine.server.util.gson.array
import net.aquamine.server.util.gson.readListTo
import java.util.UUID

class AquaGameProfile private constructor(
    override val uuid: UUID,
    override val name: String,
    override val properties: PersistentList<ProfileProperty>
) : GameProfile {

    override fun withProperties(properties: Iterable<ProfileProperty>): GameProfile = AquaGameProfile(uuid, name, properties.toPersistentList())

    override fun withProperty(property: ProfileProperty): GameProfile = AquaGameProfile(uuid, name, properties.add(property))

    override fun withoutProperty(index: Int): GameProfile = AquaGameProfile(uuid, name, properties.removeAt(index))

    override fun withoutProperty(property: ProfileProperty): GameProfile = AquaGameProfile(uuid, name, properties.remove(property))

    override fun equals(other: Any?): Boolean =
        this === other || other is AquaGameProfile && uuid == other.uuid && name == other.name && properties == other.properties

    override fun hashCode(): Int {
        var result = 1
        result = 31 * result + uuid.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + properties.hashCode()
        return result
    }

    override fun toString(): String = "GameProfile(uuid=$uuid, name=$name, properties=$properties)"

    object Factory : GameProfile.Factory {

        override fun of(name: String, uuid: UUID, properties: List<ProfileProperty>): GameProfile = full(uuid, name, properties)
    }

    object Adapter : TypeAdapter<GameProfile>() {

        override fun read(reader: JsonReader): GameProfile? {
            reader.beginObject()

            var uuid: UUID? = null
            var name: String? = null
            val properties = persistentListOf<ProfileProperty>().builder()

            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "id" -> uuid = MojangUUIDTypeAdapter.read(reader)
                    "name" -> name = reader.nextString()
                    "properties" -> reader.readListTo(properties, AquaProfileProperty.Adapter::read)

                    else -> reader.skipValue()
                }
            }

            reader.endObject()
            if (uuid == null || name == null) return null
            return full(uuid, name, properties.build())
        }

        override fun write(writer: JsonWriter, value: GameProfile) {
            writer.beginObject()
            writer.name("id")
            MojangUUIDTypeAdapter.write(writer, value.uuid)
            writer.name("name")
            writer.value(value.name)

            if (value.properties.isNotEmpty()) {
                writer.name("properties")
                writer.array(value.properties, AquaProfileProperty.Adapter::write)
            }
            writer.endObject()
        }
    }

    companion object {

        @JvmStatic
        fun partial(name: String): GameProfile = basic(UUIDUtil.NIL_UUID, name)

        @JvmStatic
        fun partial(uuid: UUID): GameProfile = basic(uuid, "")

        @JvmStatic
        fun basic(uuid: UUID, name: String): GameProfile = AquaGameProfile(uuid, name, persistentListOf())

        @JvmStatic
        fun full(uuid: UUID, name: String, properties: List<ProfileProperty>): GameProfile =
            AquaGameProfile(uuid, name, properties.toPersistentList())
    }
}

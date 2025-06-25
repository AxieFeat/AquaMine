package net.aquamine.server.auth

import kotlinx.collections.immutable.persistentListOf
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.auth.ProfileProperty
import net.aquamine.server.util.nbt.getUUID
import net.aquamine.server.util.nbt.hasUUID
import net.aquamine.server.util.nbt.putUUID
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.ImmutableCompoundTag
import xyz.axie.nbt.StringTag
import xyz.axie.nbt.compound

object GameProfileUtil {

    private const val NAME_TAG = "Name"
    private const val ID_TAG = "Id"
    private const val PROPERTIES_TAG = "Properties"
    private const val VALUE_TAG = "Value"
    private const val SIGNATURE_TAG = "Signature"

    @JvmStatic
    fun getProfile(data: CompoundTag, key: String): GameProfile? {
        if (data.contains(key, CompoundTag.ID)) return deserialize(data.getCompound(key))
        if (data.contains(key, StringTag.ID)) {
            val name = data.getString(key)
            if (name.isNotBlank()) return AquaGameProfile.partial(name)
        }
        return null
    }

    @JvmStatic
    fun deserialize(data: CompoundTag): GameProfile? {
        val uuid = if (data.hasUUID(ID_TAG)) data.getUUID(ID_TAG) else return null
        val name = if (data.contains(NAME_TAG, StringTag.ID)) data.getString(NAME_TAG) else return null
        if (!data.contains(PROPERTIES_TAG, CompoundTag.ID)) return AquaGameProfile.basic(uuid, name)

        val properties = persistentListOf<ProfileProperty>().builder()
        data.getCompound(PROPERTIES_TAG).forEachList { profileKey, list ->
            list.forEachCompound {
                val value = it.getString(VALUE_TAG)
                val signature = if (it.contains(SIGNATURE_TAG, StringTag.ID)) it.getString(SIGNATURE_TAG) else null
                properties.add(AquaProfileProperty(profileKey, value, signature))
            }
        }
        return AquaGameProfile.full(uuid, name, properties.build())
    }

    @JvmStatic
    fun putProfile(data: CompoundTag, key: String, profile: GameProfile?): CompoundTag =
        if (profile == null) data else data.put(key, serialize(profile))

    @JvmStatic
    fun putProfile(data: CompoundTag.Builder, key: String, profile: GameProfile?): CompoundTag.Builder =
        if (profile == null) data else data.put(key, serialize(profile))

    @JvmStatic
    fun serialize(profile: GameProfile): CompoundTag {
        val builder = ImmutableCompoundTag.builder().putString(NAME_TAG, profile.name).putUUID(ID_TAG, profile.uuid)
        if (profile.properties.isEmpty()) return builder.build()

        return builder.compound(PROPERTIES_TAG) {
            profile.properties.forEach { property ->
                val data = ImmutableCompoundTag.builder().putString(VALUE_TAG, property.value)
                if (property.signature != null) data.putString(SIGNATURE_TAG, property.signature!!)
                putList(property.name) { it.add(data.build()) }
            }
        }.build()
    }
}

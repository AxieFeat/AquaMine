package net.aquamine.server.entity.serializer.animal

import net.aquamine.api.entity.animal.type.FoxVariant
import net.aquamine.server.entity.animal.KryptonFox
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.uuid.UUIDUtil
import net.aquamine.server.util.nbt.addNullable
import net.aquamine.server.util.nbt.addUUID
import net.aquamine.server.util.nbt.putStringEnum
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.IntArrayTag
import xyz.axie.nbt.ListTag
import xyz.axie.nbt.StringTag
import xyz.axie.nbt.list
import java.util.UUID

object FoxSerializer : EntitySerializer<KryptonFox> {

    private const val TRUSTED_TAG = "Trusted"
    private const val SLEEPING_TAG = "Sleeping"
    private const val TYPE_TAG = "Type"
    private const val SITTING_TAG = "Sitting"
    private const val CROUCHING_TAG = "Crouching"
    private val TYPE_NAMES = FoxVariant.values().associateBy { it.name.lowercase() }

    override fun load(entity: KryptonFox, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        data.getList(TRUSTED_TAG, IntArrayTag.ID).forEachIntArray { addTrustedId(entity, UUIDUtil.fromIntArray(it)) }
        entity.isSleeping = data.getBoolean(SLEEPING_TAG)
        if (data.contains(TYPE_TAG, StringTag.ID)) entity.variant = deserializeType(data.getString(TYPE_TAG))
        entity.isSitting = data.getBoolean(SITTING_TAG)
        entity.isCrouching = data.getBoolean(CROUCHING_TAG)
    }

    override fun save(entity: KryptonFox): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        list(TRUSTED_TAG) {
            addNullable(entity.firstTrusted, ListTag.Builder::addUUID)
            addNullable(entity.secondTrusted, ListTag.Builder::addUUID)
        }
        putBoolean(SLEEPING_TAG, entity.isSleeping)
        putStringEnum(TYPE_TAG, entity.variant)
        putBoolean(SITTING_TAG, entity.isSitting)
        putBoolean(CROUCHING_TAG, entity.isCrouching)
    }

    @JvmStatic
    private fun addTrustedId(entity: KryptonFox, uuid: UUID?) {
        if (entity.firstTrusted != null) entity.secondTrusted = uuid else entity.firstTrusted = uuid
    }

    @JvmStatic
    private fun deserializeType(name: String): FoxVariant = TYPE_NAMES.get(name)!!
}

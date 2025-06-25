package net.aquamine.server.entity.serializer.vehicle

import net.aquamine.api.entity.vehicle.BoatVariant
import net.aquamine.server.entity.serializer.BaseEntitySerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.vehicle.KryptonBoat
import net.aquamine.server.util.nbt.putStringEnum
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.StringTag

object BoatSerializer : EntitySerializer<KryptonBoat> {

    private const val TYPE_TAG = "Type"
    private val TYPE_NAMES = BoatVariant.values().associateBy { it.name.lowercase() }

    override fun load(entity: KryptonBoat, data: CompoundTag) {
        BaseEntitySerializer.load(entity, data)
        if (data.contains(TYPE_TAG, StringTag.ID)) entity.variant = TYPE_NAMES.getOrDefault(data.getString(TYPE_TAG), BoatVariant.OAK)
    }

    override fun save(entity: KryptonBoat): CompoundTag.Builder = BaseEntitySerializer.save(entity).apply {
        putStringEnum(TYPE_TAG, entity.variant)
    }
}

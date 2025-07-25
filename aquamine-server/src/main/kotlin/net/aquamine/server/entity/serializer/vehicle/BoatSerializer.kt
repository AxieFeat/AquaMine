package net.aquamine.server.entity.serializer.vehicle

import net.aquamine.api.entity.vehicle.BoatVariant
import net.aquamine.server.entity.serializer.BaseEntitySerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.vehicle.AquaBoat
import net.aquamine.server.util.nbt.putStringEnum
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.StringTag

object BoatSerializer : EntitySerializer<AquaBoat> {

    private const val TYPE_TAG = "Type"
    private val TYPE_NAMES = BoatVariant.entries.associateBy { it.name.lowercase() }

    override fun load(entity: AquaBoat, data: CompoundTag) {
        BaseEntitySerializer.load(entity, data)
        if (data.contains(TYPE_TAG, StringTag.ID)) entity.variant = TYPE_NAMES.getOrDefault(data.getString(TYPE_TAG), BoatVariant.OAK)
    }

    override fun save(entity: AquaBoat): CompoundTag.Builder = BaseEntitySerializer.save(entity).apply {
        putStringEnum(TYPE_TAG, entity.variant)
    }
}

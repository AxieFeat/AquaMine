package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.KryptonMooshroom
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.putStringEnum
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.StringTag

object MooshroomSerializer : EntitySerializer<KryptonMooshroom> {

    private const val TYPE_TAG = "Type"

    override fun load(entity: KryptonMooshroom, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        if (data.contains(TYPE_TAG, StringTag.ID)) entity.variant = KryptonMooshroom.deserializeType(data.getString(TYPE_TAG))
    }

    override fun save(entity: KryptonMooshroom): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putStringEnum(TYPE_TAG, entity.variant)
    }
}

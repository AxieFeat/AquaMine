package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.AquaChicken
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.hasNumber
import xyz.axie.nbt.CompoundTag

object ChickenSerializer : EntitySerializer<AquaChicken> {

    private const val JOCKEY_TAG = "IsChickenJockey"
    private const val EGG_LAY_TIME_TAG = "EggLayTime"

    override fun load(entity: AquaChicken, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        entity.isJockey = data.getBoolean(JOCKEY_TAG)
        if (data.hasNumber(EGG_LAY_TIME_TAG)) entity.eggCooldownTime = data.getInt(EGG_LAY_TIME_TAG)
    }

    override fun save(entity: AquaChicken): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putBoolean(JOCKEY_TAG, entity.isJockey)
        putInt(EGG_LAY_TIME_TAG, entity.eggCooldownTime)
    }
}

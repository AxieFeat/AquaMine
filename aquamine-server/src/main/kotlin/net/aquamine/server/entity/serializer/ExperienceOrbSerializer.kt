package net.aquamine.server.entity.serializer

import net.aquamine.server.entity.AquaExperienceOrb
import xyz.axie.nbt.CompoundTag

object ExperienceOrbSerializer : EntitySerializer<AquaExperienceOrb> {

    private const val AGE_TAG = "Age"
    private const val COUNT_TAG = "Count"
    private const val HEALTH_TAG = "Health"
    private const val VALUE_TAG = "Value"

    override fun load(entity: AquaExperienceOrb, data: CompoundTag) {
        BaseEntitySerializer.load(entity, data)
        entity.age = data.getShort(AGE_TAG).toInt()
        entity.count = data.getInt(COUNT_TAG)
        entity.health = data.getShort(HEALTH_TAG).toInt()
        entity.experience = data.getShort(VALUE_TAG).toInt()
    }

    override fun save(entity: AquaExperienceOrb): CompoundTag.Builder = BaseEntitySerializer.save(entity).apply {
        putShort(AGE_TAG, entity.age.toShort())
        putInt(COUNT_TAG, entity.count)
        putShort(HEALTH_TAG, entity.health.toShort())
        putShort(VALUE_TAG, entity.experience.toShort())
    }
}

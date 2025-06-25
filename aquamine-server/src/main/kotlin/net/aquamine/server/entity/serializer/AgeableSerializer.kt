package net.aquamine.server.entity.serializer

import net.aquamine.server.entity.KryptonAgeable
import xyz.axie.nbt.CompoundTag

object AgeableSerializer : EntitySerializer<KryptonAgeable> {

    private const val AGE_TAG = "Age"
    private const val FORCED_AGE_TAG = "ForcedAge"

    override fun load(entity: KryptonAgeable, data: CompoundTag) {
        MobSerializer.load(entity, data)
        entity.age = data.getInt(AGE_TAG)
        entity.forcedAge = data.getInt(FORCED_AGE_TAG)
    }

    override fun save(entity: KryptonAgeable): CompoundTag.Builder = MobSerializer.save(entity).apply {
        putInt(AGE_TAG, entity.age)
        putInt(FORCED_AGE_TAG, entity.forcedAge)
    }
}

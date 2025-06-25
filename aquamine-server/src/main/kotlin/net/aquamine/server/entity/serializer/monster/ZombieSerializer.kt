package net.aquamine.server.entity.serializer.monster

import net.aquamine.server.entity.monster.KryptonZombie
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.MobSerializer
import net.aquamine.server.util.nbt.hasNumber
import xyz.axie.nbt.CompoundTag

object ZombieSerializer : EntitySerializer<KryptonZombie> {

    private const val BABY_TAG = "IsBaby"
    private const val CONVERSION_TIME_TAG = "DrownedConversionTime"

    override fun load(entity: KryptonZombie, data: CompoundTag) {
        MobSerializer.load(entity, data)
        entity.isBaby = data.getBoolean(BABY_TAG)
        if (data.hasNumber(CONVERSION_TIME_TAG) && data.getInt(CONVERSION_TIME_TAG) > -1) {
            entity.conversionTime = data.getInt(CONVERSION_TIME_TAG)
            entity.isConverting = true
        }
    }

    override fun save(entity: KryptonZombie): CompoundTag.Builder = MobSerializer.save(entity).apply {
        putBoolean(BABY_TAG, entity.isBaby)
        putInt(CONVERSION_TIME_TAG, entity.conversionTime)
    }
}

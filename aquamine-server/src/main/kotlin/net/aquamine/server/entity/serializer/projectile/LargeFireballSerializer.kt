package net.aquamine.server.entity.serializer.projectile

import net.aquamine.server.entity.projectile.AquaLargeFireball
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.hasNumber
import xyz.axie.nbt.CompoundTag

object LargeFireballSerializer : EntitySerializer<AquaLargeFireball> {

    private const val EXPLOSION_POWER_TAG = "ExplosionPower"

    override fun load(entity: AquaLargeFireball, data: CompoundTag) {
        FireballSerializer.load(entity, data)
        if (data.hasNumber(EXPLOSION_POWER_TAG)) entity.explosionPower = data.getByte(EXPLOSION_POWER_TAG).toInt()
    }

    override fun save(entity: AquaLargeFireball): CompoundTag.Builder = FireballSerializer.save(entity).apply {
        putByte(EXPLOSION_POWER_TAG, entity.explosionPower.toByte())
    }
}

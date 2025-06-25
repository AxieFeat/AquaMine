package net.aquamine.server.entity.serializer.monster

import net.aquamine.server.entity.monster.KryptonCreeper
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.MobSerializer
import xyz.axie.nbt.CompoundTag

object CreeperSerializer : EntitySerializer<KryptonCreeper> {

    private const val POWERED_TAG = "powered"
    private const val IGNITED_TAG = "ignited"
    private const val FUSE_TAG = "Fuse"
    private const val EXPLOSION_RADIUS_TAG = "ExplosionRadius"

    override fun load(entity: KryptonCreeper, data: CompoundTag) {
        MobSerializer.load(entity, data)
        entity.isCharged = data.getBoolean(POWERED_TAG)
        entity.setIgnited(data.getBoolean(IGNITED_TAG))
        entity.fuse = data.getShort(FUSE_TAG).toInt()
        entity.explosionRadius = data.getInt(EXPLOSION_RADIUS_TAG)
    }

    override fun save(entity: KryptonCreeper): CompoundTag.Builder = MobSerializer.save(entity).apply {
        putBoolean(POWERED_TAG, entity.isCharged)
        putBoolean(IGNITED_TAG, entity.isIgnited)
        putShort(FUSE_TAG, entity.fuse.toShort())
        putInt(EXPLOSION_RADIUS_TAG, entity.explosionRadius)
    }
}

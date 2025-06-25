package net.aquamine.server.entity.serializer.ambient

import net.aquamine.server.entity.ambient.KryptonBat
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.MobSerializer
import xyz.axie.nbt.CompoundTag

object BatSerializer : EntitySerializer<KryptonBat> {

    private const val FLAGS_TAG = "BatFlags"

    override fun load(entity: KryptonBat, data: CompoundTag) {
        MobSerializer.load(entity, data)
        entity.data.set(MetadataKeys.Bat.FLAGS, data.getByte(FLAGS_TAG))
    }

    override fun save(entity: KryptonBat): CompoundTag.Builder = MobSerializer.save(entity).apply {
        putByte(FLAGS_TAG, entity.data.get(MetadataKeys.Bat.FLAGS))
    }
}

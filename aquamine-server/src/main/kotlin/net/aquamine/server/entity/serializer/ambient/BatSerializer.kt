package net.aquamine.server.entity.serializer.ambient

import net.aquamine.server.entity.ambient.AquaBat
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.MobSerializer
import xyz.axie.nbt.CompoundTag

object BatSerializer : EntitySerializer<AquaBat> {

    private const val FLAGS_TAG = "BatFlags"

    override fun load(entity: AquaBat, data: CompoundTag) {
        MobSerializer.load(entity, data)
        entity.data.set(MetadataKeys.Bat.FLAGS, data.getByte(FLAGS_TAG))
    }

    override fun save(entity: AquaBat): CompoundTag.Builder = MobSerializer.save(entity).apply {
        putByte(FLAGS_TAG, entity.data.get(MetadataKeys.Bat.FLAGS))
    }
}

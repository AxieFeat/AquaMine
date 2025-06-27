package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.AquaPig
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag

object PigSerializer : EntitySerializer<AquaPig> {

    private const val SADDLE_TAG = "Saddle"

    override fun load(entity: AquaPig, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        entity.isSaddled = data.getBoolean(SADDLE_TAG)
    }

    override fun save(entity: AquaPig): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putBoolean(SADDLE_TAG, entity.isSaddled)
    }
}

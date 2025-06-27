package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.AquaGoat
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag

object GoatSerializer : EntitySerializer<AquaGoat> {

    private const val SCREAMING_TAG = "IsScreamingGoat"

    override fun load(entity: AquaGoat, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        entity.canScream = data.getBoolean(SCREAMING_TAG)
    }

    override fun save(entity: AquaGoat): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putBoolean(SCREAMING_TAG, entity.canScream)
    }
}

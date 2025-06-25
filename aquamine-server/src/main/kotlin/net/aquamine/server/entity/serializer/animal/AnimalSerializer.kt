package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.KryptonAnimal
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.getUUID
import net.aquamine.server.util.nbt.hasUUID
import net.aquamine.server.util.nbt.putNullable
import net.aquamine.server.util.nbt.putUUID
import xyz.axie.nbt.CompoundTag

object AnimalSerializer : EntitySerializer<KryptonAnimal> {

    private const val LOVE_TAG = "InLove"
    private const val LOVE_CAUSE_TAG = "LoveCause"

    override fun load(entity: KryptonAnimal, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        entity.inLoveTime = data.getInt(LOVE_TAG)
        entity.loveCause = if (data.hasUUID(LOVE_CAUSE_TAG)) data.getUUID(LOVE_CAUSE_TAG) else null
    }

    override fun save(entity: KryptonAnimal): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putInt(LOVE_TAG, entity.inLoveTime)
        putNullable(LOVE_CAUSE_TAG, entity.loveCause, CompoundTag.Builder::putUUID)
    }
}

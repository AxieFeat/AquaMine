package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.AquaRabbit
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag

object RabbitSerializer : EntitySerializer<AquaRabbit> {

    private const val TYPE_TAG = "RabbitType"
    private const val MORE_CARROT_TICKS_TAG = "MoreCarrotTicks"

    override fun load(entity: AquaRabbit, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        entity.data.set(MetadataKeys.Rabbit.TYPE, data.getInt(TYPE_TAG))
        entity.moreCarrotTicks = data.getInt(MORE_CARROT_TICKS_TAG)
    }

    override fun save(entity: AquaRabbit): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putInt(TYPE_TAG, entity.data.get(MetadataKeys.Rabbit.TYPE))
        putInt(MORE_CARROT_TICKS_TAG, entity.moreCarrotTicks)
    }
}

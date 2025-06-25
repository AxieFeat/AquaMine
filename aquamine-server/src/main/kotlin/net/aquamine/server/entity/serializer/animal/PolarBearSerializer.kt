package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.components.Neutral
import net.aquamine.server.entity.animal.KryptonPolarBear
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag

object PolarBearSerializer : EntitySerializer<KryptonPolarBear> {

    override fun load(entity: KryptonPolarBear, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        Neutral.loadAngerData(entity, data)
    }

    override fun save(entity: KryptonPolarBear): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        Neutral.saveAngerData(entity, this)
    }
}

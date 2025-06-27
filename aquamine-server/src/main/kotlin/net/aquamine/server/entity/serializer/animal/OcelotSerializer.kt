package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.AquaOcelot
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag

object OcelotSerializer : EntitySerializer<AquaOcelot> {

    private const val TRUSTING_TAG = "Trusting"

    override fun load(entity: AquaOcelot, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        entity.isTrusting = data.getBoolean(TRUSTING_TAG)
    }

    override fun save(entity: AquaOcelot): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putBoolean(TRUSTING_TAG, entity.isTrusting)
    }
}

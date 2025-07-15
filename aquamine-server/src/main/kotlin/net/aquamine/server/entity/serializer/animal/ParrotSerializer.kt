package net.aquamine.server.entity.serializer.animal

import net.aquamine.api.entity.animal.type.ParrotVariant
import net.aquamine.server.entity.animal.AquaParrot
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag

object ParrotSerializer : EntitySerializer<AquaParrot> {

    private val TYPES = ParrotVariant.entries.toTypedArray()
    private const val VARIANT_TAG = "Variant"

    override fun load(entity: AquaParrot, data: CompoundTag) {
        TamableSerializer.load(entity, data)
        entity.variant = TYPES.getOrNull(data.getInt(VARIANT_TAG)) ?: ParrotVariant.RED_AND_BLUE
    }

    override fun save(entity: AquaParrot): CompoundTag.Builder = TamableSerializer.save(entity).apply {
        putInt(VARIANT_TAG, entity.variant.ordinal)
    }
}

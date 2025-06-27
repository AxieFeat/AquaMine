package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.AquaSheep
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.enumhelper.DyeColors
import xyz.axie.nbt.CompoundTag

object SheepSerializer : EntitySerializer<AquaSheep> {

    private const val SHEARED_TAG = "Sheared"
    private const val COLOR_TAG = "Color"

    override fun load(entity: AquaSheep, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        entity.isSheared = data.getBoolean(SHEARED_TAG)
        entity.woolColor = DyeColors.fromId(data.getByte(COLOR_TAG).toInt())
    }

    override fun save(entity: AquaSheep): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putBoolean(SHEARED_TAG, entity.isSheared)
        putByte(COLOR_TAG, entity.woolColor.ordinal.toByte())
    }
}

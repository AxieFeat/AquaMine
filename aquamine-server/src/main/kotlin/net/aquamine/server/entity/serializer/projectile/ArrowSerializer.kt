package net.aquamine.server.entity.serializer.projectile

import net.aquamine.api.util.Color
import net.aquamine.server.entity.projectile.AquaArrow
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.hasNumber
import xyz.axie.nbt.CompoundTag

object ArrowSerializer : EntitySerializer<AquaArrow> {

    private const val COLOR_TAG = "Color"
    private const val RGB_MAX_VALUE = java.awt.Color.BITMASK

    override fun load(entity: AquaArrow, data: CompoundTag) {
        ArrowLikeSerializer.load(entity, data)
        if (data.hasNumber(COLOR_TAG)) {
            val rgb = data.getInt(COLOR_TAG)
            if (rgb >= 0 && rgb <= RGB_MAX_VALUE) entity.color = Color(rgb)
        }
    }

    override fun save(entity: AquaArrow): CompoundTag.Builder = ArrowLikeSerializer.save(entity).apply {
        putInt(COLOR_TAG, entity.color.encode())
    }
}

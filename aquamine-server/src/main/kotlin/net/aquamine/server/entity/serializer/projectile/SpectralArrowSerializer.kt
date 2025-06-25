package net.aquamine.server.entity.serializer.projectile

import net.aquamine.server.entity.projectile.KryptonSpectralArrow
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag

object SpectralArrowSerializer : EntitySerializer<KryptonSpectralArrow> {

    private const val DURATION_TAG = "Duration"

    override fun load(entity: KryptonSpectralArrow, data: CompoundTag) {
        ArrowLikeSerializer.load(entity, data)
        entity.duration = data.getInt(DURATION_TAG)
    }

    override fun save(entity: KryptonSpectralArrow): CompoundTag.Builder = ArrowLikeSerializer.save(entity).apply {
        putInt(DURATION_TAG, entity.duration)
    }
}

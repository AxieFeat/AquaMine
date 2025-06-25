package net.aquamine.server.entity.serializer

import net.aquamine.api.util.Color
import net.aquamine.server.entity.KryptonAreaEffectCloud
import xyz.axie.nbt.CompoundTag

object AreaEffectCloudSerializer : EntitySerializer<KryptonAreaEffectCloud> {

    private const val AGE_TAG = "Age"
    private const val DURATION_TAG = "Duration"
    private const val RADIUS_TAG = "Radius"
    private const val COLOR_TAG = "Color"

    override fun load(entity: KryptonAreaEffectCloud, data: CompoundTag) {
        BaseEntitySerializer.load(entity, data)
        entity.age = data.getInt(AGE_TAG)
        entity.duration = data.getInt(DURATION_TAG)
        entity.radius = data.getFloat(RADIUS_TAG)
        entity.color = Color(data.getInt(COLOR_TAG))
    }

    override fun save(entity: KryptonAreaEffectCloud): CompoundTag.Builder = BaseEntitySerializer.save(entity).apply {
        putInt(AGE_TAG, entity.age)
        putInt(DURATION_TAG, entity.duration)
        putFloat(RADIUS_TAG, entity.radius)
        putInt(COLOR_TAG, entity.color.encode())
    }
}

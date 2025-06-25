package net.aquamine.server.entity.serializer.hanging

import net.kyori.adventure.key.Key
import net.aquamine.server.entity.hanging.KryptonPainting
import net.aquamine.server.entity.serializer.BaseEntitySerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.util.enumhelper.Directions
import net.aquamine.server.util.nbt.putNullable
import net.aquamine.server.util.nbt.putKeyed
import xyz.axie.nbt.CompoundTag

object PaintingSerializer : EntitySerializer<KryptonPainting> {

    private const val VARIANT_TAG = "variant"
    private const val FACING_TAG = "facing"

    override fun load(entity: KryptonPainting, data: CompoundTag) {
        BaseEntitySerializer.load(entity, data)
        entity.variant = KryptonRegistries.PAINTING_VARIANT.get(Key.key(data.getString(VARIANT_TAG)))
        entity.direction = Directions.of2D(data.getByte(FACING_TAG).toInt())
    }

    override fun save(entity: KryptonPainting): CompoundTag.Builder = BaseEntitySerializer.save(entity).apply {
        putNullable(VARIANT_TAG, entity.variant, CompoundTag.Builder::putKeyed)
        putByte(FACING_TAG, Directions.data2D(entity.direction).toByte())
    }
}

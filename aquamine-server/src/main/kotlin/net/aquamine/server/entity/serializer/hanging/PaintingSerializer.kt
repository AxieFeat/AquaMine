package net.aquamine.server.entity.serializer.hanging

import net.kyori.adventure.key.Key
import net.aquamine.server.entity.hanging.AquaPainting
import net.aquamine.server.entity.serializer.BaseEntitySerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.util.enumhelper.Directions
import net.aquamine.server.util.nbt.putNullable
import net.aquamine.server.util.nbt.putKeyed
import xyz.axie.nbt.CompoundTag

object PaintingSerializer : EntitySerializer<AquaPainting> {

    private const val VARIANT_TAG = "variant"
    private const val FACING_TAG = "facing"

    override fun load(entity: AquaPainting, data: CompoundTag) {
        BaseEntitySerializer.load(entity, data)
        entity.variant = AquaRegistries.PAINTING_VARIANT.get(Key.key(data.getString(VARIANT_TAG)))
        entity.direction = Directions.of2D(data.getByte(FACING_TAG).toInt())
    }

    override fun save(entity: AquaPainting): CompoundTag.Builder = BaseEntitySerializer.save(entity).apply {
        putNullable(VARIANT_TAG, entity.variant, CompoundTag.Builder::putKeyed)
        putByte(FACING_TAG, Directions.data2D(entity.direction).toByte())
    }
}

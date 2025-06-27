package net.aquamine.server.entity.serializer.hanging

import net.aquamine.server.entity.hanging.AquaHangingEntity
import net.aquamine.server.entity.serializer.BaseEntitySerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.getBlockPos
import net.aquamine.server.util.nbt.putBlockPosParts
import xyz.axie.nbt.CompoundTag

object HangingEntitySerializer : EntitySerializer<AquaHangingEntity> {

    private const val TILE_PREFIX = "Tile"

    override fun load(entity: AquaHangingEntity, data: CompoundTag) {
        BaseEntitySerializer.load(entity, data)
        entity.centerPosition = data.getBlockPos(TILE_PREFIX)
    }

    override fun save(entity: AquaHangingEntity): CompoundTag.Builder = BaseEntitySerializer.save(entity).apply {
        entity.centerPosition?.let { putBlockPosParts(it, TILE_PREFIX) }
    }
}

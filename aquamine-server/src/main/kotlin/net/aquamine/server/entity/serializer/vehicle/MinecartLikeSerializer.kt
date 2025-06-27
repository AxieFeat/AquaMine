package net.aquamine.server.entity.serializer.vehicle

import net.aquamine.server.entity.serializer.BaseEntitySerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.vehicle.AquaMinecartLike
import net.aquamine.server.world.block.BlockStateSerialization
import xyz.axie.nbt.CompoundTag

object MinecartLikeSerializer : EntitySerializer<AquaMinecartLike> {

    private const val CUSTOM_DISPLAY_TAG = "CustomDisplayTile"
    private const val DISPLAY_STATE_TAG = "DisplayState"
    private const val DISPLAY_OFFSET_TAG = "DisplayOffset"

    override fun load(entity: AquaMinecartLike, data: CompoundTag) {
        BaseEntitySerializer.load(entity, data)
        if (!data.getBoolean(CUSTOM_DISPLAY_TAG)) return
        entity.setCustomBlock(BlockStateSerialization.decode(data.getCompound(DISPLAY_STATE_TAG)))
        entity.customBlockOffset = data.getInt(DISPLAY_OFFSET_TAG)
    }

    override fun save(entity: AquaMinecartLike): CompoundTag.Builder = BaseEntitySerializer.save(entity).apply {
        if (entity.showCustomBlock) {
            putBoolean(CUSTOM_DISPLAY_TAG, true)
            put(DISPLAY_STATE_TAG, BlockStateSerialization.encode(entity.customBlock()))
            putInt(DISPLAY_OFFSET_TAG, entity.customBlockOffset)
        }
    }
}

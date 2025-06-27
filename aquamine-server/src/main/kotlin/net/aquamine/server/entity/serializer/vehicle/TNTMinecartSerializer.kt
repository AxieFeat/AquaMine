package net.aquamine.server.entity.serializer.vehicle

import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.vehicle.AquaTNTMinecart
import net.aquamine.server.util.nbt.hasNumber
import xyz.axie.nbt.CompoundTag

object TNTMinecartSerializer : EntitySerializer<AquaTNTMinecart> {

    private const val FUSE_TAG = "TNTFuse"

    override fun load(entity: AquaTNTMinecart, data: CompoundTag) {
        MinecartLikeSerializer.load(entity, data)
        if (data.hasNumber(FUSE_TAG)) entity.fuse = data.getInt(FUSE_TAG)
    }

    override fun save(entity: AquaTNTMinecart): CompoundTag.Builder = MinecartLikeSerializer.save(entity).apply {
        putInt(FUSE_TAG, entity.fuse)
    }
}

package net.aquamine.server.entity.serializer.vehicle

import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.vehicle.AquaFurnaceMinecart
import xyz.axie.nbt.CompoundTag

object FurnaceMinecartSerializer : EntitySerializer<AquaFurnaceMinecart> {

    private const val FUEL_TAG = "Fuel"

    override fun load(entity: AquaFurnaceMinecart, data: CompoundTag) {
        MinecartLikeSerializer.load(entity, data)
        entity.setFuel(data.getShort(FUEL_TAG).toInt())
    }

    override fun save(entity: AquaFurnaceMinecart): CompoundTag.Builder = MinecartLikeSerializer.save(entity).apply {
        putShort(FUEL_TAG, entity.fuel.toShort())
    }
}

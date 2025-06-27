package net.aquamine.server.entity.vehicle

import net.aquamine.api.entity.vehicle.FurnaceMinecart
import net.aquamine.api.entity.vehicle.MinecartVariant
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.vehicle.FurnaceMinecartSerializer
import net.aquamine.server.world.AquaWorld

class AquaFurnaceMinecart(world: AquaWorld) : AquaMinecartLike(world), FurnaceMinecart {

    override val type: AquaEntityType<AquaFurnaceMinecart>
        get() = AquaEntityTypes.FURNACE_MINECART
    override val serializer: EntitySerializer<AquaFurnaceMinecart>
        get() = FurnaceMinecartSerializer

    override val variant: MinecartVariant
        get() = MinecartVariant.FURNACE
    private var remainingFuel = 0

    override val fuel: Int
        get() = remainingFuel

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.FurnaceMinecart.HAS_FUEL, false)
    }

    override fun hasFuel(): Boolean = data.get(MetadataKeys.FurnaceMinecart.HAS_FUEL)

    private fun setHasFuel(has: Boolean) {
        data.set(MetadataKeys.FurnaceMinecart.HAS_FUEL, has)
    }

    fun setFuel(fuel: Int) {
        remainingFuel = fuel
        if (fuel != 0) setHasFuel(true)
    }

    override fun addFuel(amount: Int) {
        remainingFuel += amount
        if (amount != 0) setHasFuel(true)
    }

    override fun removeFuel(amount: Int) {
        remainingFuel -= amount
        if (remainingFuel <= 0) resetFuel()
    }

    override fun resetFuel() {
        remainingFuel = 0
        setHasFuel(false)
    }
}

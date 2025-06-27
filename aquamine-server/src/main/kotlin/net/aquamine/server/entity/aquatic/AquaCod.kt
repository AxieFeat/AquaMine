package net.aquamine.server.entity.aquatic

import net.aquamine.api.entity.aquatic.Cod
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.world.AquaWorld

class AquaCod(world: AquaWorld) : AquaSchoolingFish(world), Cod {

    override val type: AquaEntityType<AquaCod>
        get() = AquaEntityTypes.COD
    override val bucketType: ItemType
        get() = ItemTypes.COD_BUCKET.get()
}

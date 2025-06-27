package net.aquamine.server.entity.aquatic

import net.aquamine.api.entity.aquatic.Salmon
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.world.AquaWorld

class AquaSalmon(world: AquaWorld) : AquaSchoolingFish(world), Salmon {

    override val type: AquaEntityType<AquaSalmon>
        get() = AquaEntityTypes.SALMON
    override val bucketType: ItemType
        get() = ItemTypes.SALMON_BUCKET.get()
}

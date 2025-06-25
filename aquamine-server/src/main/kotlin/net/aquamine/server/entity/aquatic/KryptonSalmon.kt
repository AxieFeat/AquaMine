package net.aquamine.server.entity.aquatic

import net.aquamine.api.entity.aquatic.Salmon
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.world.KryptonWorld

class KryptonSalmon(world: KryptonWorld) : KryptonSchoolingFish(world), Salmon {

    override val type: KryptonEntityType<KryptonSalmon>
        get() = KryptonEntityTypes.SALMON
    override val bucketType: ItemType
        get() = ItemTypes.SALMON_BUCKET.get()
}

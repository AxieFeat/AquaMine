package net.aquamine.server.entity.aquatic

import net.aquamine.api.entity.aquatic.Cod
import net.aquamine.api.item.ItemType
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.world.KryptonWorld

class KryptonCod(world: KryptonWorld) : KryptonSchoolingFish(world), Cod {

    override val type: KryptonEntityType<KryptonCod>
        get() = KryptonEntityTypes.COD
    override val bucketType: ItemType
        get() = ItemTypes.COD_BUCKET.get()
}

package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.Snowball
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.world.AquaWorld

class AquaSnowball(world: AquaWorld) : AquaThrowableProjectile(world), Snowball {

    override val type: AquaEntityType<AquaSnowball>
        get() = AquaEntityTypes.SNOWBALL

    override fun defaultItem(): AquaItemStack = DEFAULT_ITEM

    companion object {

        private val DEFAULT_ITEM = AquaItemStack(ItemTypes.SNOWBALL.get())
    }
}

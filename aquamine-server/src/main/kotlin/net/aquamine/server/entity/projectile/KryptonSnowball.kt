package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.Snowball
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.world.KryptonWorld

class KryptonSnowball(world: KryptonWorld) : KryptonThrowableProjectile(world), Snowball {

    override val type: KryptonEntityType<KryptonSnowball>
        get() = KryptonEntityTypes.SNOWBALL

    override fun defaultItem(): KryptonItemStack = DEFAULT_ITEM

    companion object {

        private val DEFAULT_ITEM = KryptonItemStack(ItemTypes.SNOWBALL.get())
    }
}

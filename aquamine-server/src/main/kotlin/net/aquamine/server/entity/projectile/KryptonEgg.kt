package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.Egg
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.world.KryptonWorld

class KryptonEgg(world: KryptonWorld) : KryptonThrowableProjectile(world), Egg {

    override val type: KryptonEntityType<KryptonEgg>
        get() = KryptonEntityTypes.EGG

    override fun defaultItem(): KryptonItemStack = DEFAULT_ITEM

    companion object {

        private val DEFAULT_ITEM = KryptonItemStack(ItemTypes.EGG.get())
    }
}

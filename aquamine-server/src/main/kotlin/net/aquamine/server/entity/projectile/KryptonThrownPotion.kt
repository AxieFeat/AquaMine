package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.ThrownPotion
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.world.KryptonWorld

class KryptonThrownPotion(world: KryptonWorld) : KryptonThrowableProjectile(world), ThrownPotion {

    override val type: KryptonEntityType<KryptonThrownPotion>
        get() = KryptonEntityTypes.POTION

    override fun defaultItem(): KryptonItemStack = DEFAULT_ITEM

    companion object {

        private val DEFAULT_ITEM = KryptonItemStack(ItemTypes.POTION.get())
    }
}

package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.EnderPearl
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.world.KryptonWorld

class KryptonEnderPearl(world: KryptonWorld) : KryptonThrowableProjectile(world), EnderPearl {

    override val type: KryptonEntityType<KryptonEnderPearl>
        get() = KryptonEntityTypes.ENDER_PEARL

    override fun defaultItem(): KryptonItemStack = DEFAULT_ITEM

    companion object {

        private val DEFAULT_ITEM = KryptonItemStack(ItemTypes.ENDER_PEARL.get())
    }
}

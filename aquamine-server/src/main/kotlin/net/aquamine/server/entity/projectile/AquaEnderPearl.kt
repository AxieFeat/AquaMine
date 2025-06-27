package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.EnderPearl
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.world.AquaWorld

class AquaEnderPearl(world: AquaWorld) : AquaThrowableProjectile(world), EnderPearl {

    override val type: AquaEntityType<AquaEnderPearl>
        get() = AquaEntityTypes.ENDER_PEARL

    override fun defaultItem(): AquaItemStack = DEFAULT_ITEM

    companion object {

        private val DEFAULT_ITEM = AquaItemStack(ItemTypes.ENDER_PEARL.get())
    }
}

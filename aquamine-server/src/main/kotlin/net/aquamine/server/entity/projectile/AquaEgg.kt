package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.Egg
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.world.AquaWorld

class AquaEgg(world: AquaWorld) : AquaThrowableProjectile(world), Egg {

    override val type: AquaEntityType<AquaEgg>
        get() = AquaEntityTypes.EGG

    override fun defaultItem(): AquaItemStack = DEFAULT_ITEM

    companion object {

        private val DEFAULT_ITEM = AquaItemStack(ItemTypes.EGG.get())
    }
}

package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.ThrownPotion
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.world.AquaWorld

class AquaThrownPotion(world: AquaWorld) : AquaThrowableProjectile(world), ThrownPotion {

    override val type: AquaEntityType<AquaThrownPotion>
        get() = AquaEntityTypes.POTION

    override fun defaultItem(): AquaItemStack = DEFAULT_ITEM

    companion object {

        private val DEFAULT_ITEM = AquaItemStack(ItemTypes.POTION.get())
    }
}

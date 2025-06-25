package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.Fireball
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.FireballSerializer
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.world.KryptonWorld

abstract class KryptonFireball(world: KryptonWorld) : KryptonAcceleratingProjectile(world), Fireball {

    override val serializer: EntitySerializer<out KryptonFireball>
        get() = FireballSerializer

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Fireball.ITEM, KryptonItemStack.EMPTY)
    }

    final override fun asItem(): ItemStack {
        val item = data.get(MetadataKeys.Fireball.ITEM)
        if (item.isEmpty()) return DEFAULT_ITEM
        return item
    }

    companion object {

        private val DEFAULT_ITEM = KryptonItemStack(ItemTypes.FIRE_CHARGE.get())
    }
}

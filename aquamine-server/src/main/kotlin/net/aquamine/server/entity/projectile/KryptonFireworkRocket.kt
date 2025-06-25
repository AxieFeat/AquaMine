package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.projectile.FireworkRocket
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.world.KryptonWorld
import java.util.OptionalInt

class KryptonFireworkRocket(world: KryptonWorld) : KryptonProjectile(world), FireworkRocket {

    override val type: KryptonEntityType<KryptonFireworkRocket>
        get() = KryptonEntityTypes.FIREWORK_ROCKET

    override var attachedEntity: Entity? = null
    override var life: Int = 0
    override var lifetime: Int = 0

    override var wasShotAtAngle: Boolean
        get() = data.get(MetadataKeys.FireworkRocket.SHOT_AT_ANGLE)
        set(value) = data.set(MetadataKeys.FireworkRocket.SHOT_AT_ANGLE, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.FireworkRocket.ITEM, KryptonItemStack.EMPTY)
        data.define(MetadataKeys.FireworkRocket.ATTACHED, OptionalInt.empty())
        data.define(MetadataKeys.FireworkRocket.SHOT_AT_ANGLE, false)
    }

    override fun asItem(): ItemStack {
        val item = data.get(MetadataKeys.FireworkRocket.ITEM)
        if (item.isEmpty()) return DEFAULT_ITEM
        return item
    }

    companion object {

        private val DEFAULT_ITEM = KryptonItemStack(ItemTypes.FIREWORK_ROCKET.get())
    }
}

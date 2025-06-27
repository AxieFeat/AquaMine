package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.projectile.FireworkRocket
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.world.AquaWorld
import java.util.OptionalInt

class AquaFireworkRocket(world: AquaWorld) : AquaProjectile(world), FireworkRocket {

    override val type: AquaEntityType<AquaFireworkRocket>
        get() = AquaEntityTypes.FIREWORK_ROCKET

    override var attachedEntity: Entity? = null
    override var life: Int = 0
    override var lifetime: Int = 0

    override var wasShotAtAngle: Boolean
        get() = data.get(MetadataKeys.FireworkRocket.SHOT_AT_ANGLE)
        set(value) = data.set(MetadataKeys.FireworkRocket.SHOT_AT_ANGLE, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.FireworkRocket.ITEM, AquaItemStack.EMPTY)
        data.define(MetadataKeys.FireworkRocket.ATTACHED, OptionalInt.empty())
        data.define(MetadataKeys.FireworkRocket.SHOT_AT_ANGLE, false)
    }

    override fun asItem(): ItemStack {
        val item = data.get(MetadataKeys.FireworkRocket.ITEM)
        if (item.isEmpty()) return DEFAULT_ITEM
        return item
    }

    companion object {

        private val DEFAULT_ITEM = AquaItemStack(ItemTypes.FIREWORK_ROCKET.get())
    }
}

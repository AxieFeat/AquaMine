package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.ExperienceBottle
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.world.KryptonWorld

class KryptonExperienceBottle(world: KryptonWorld) : KryptonThrowableProjectile(world), ExperienceBottle {

    override val type: KryptonEntityType<KryptonExperienceBottle>
        get() = KryptonEntityTypes.EXPERIENCE_BOTTLE

    override fun defaultItem(): KryptonItemStack = DEFAULT_ITEM

    companion object {

        private val DEFAULT_ITEM = KryptonItemStack(ItemTypes.EXPERIENCE_BOTTLE.get())
    }
}

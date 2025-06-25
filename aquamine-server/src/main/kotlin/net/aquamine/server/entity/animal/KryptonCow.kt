package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Cow
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.world.KryptonWorld

open class KryptonCow(world: KryptonWorld) : KryptonAnimal(world), Cow {

    override val type: KryptonEntityType<KryptonCow>
        get() = KryptonEntityTypes.COW

    override fun soundVolume(): Float = 0.4F

    companion object {

        private const val DEFAULT_MAX_HEALTH = 10.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.2

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
    }
}

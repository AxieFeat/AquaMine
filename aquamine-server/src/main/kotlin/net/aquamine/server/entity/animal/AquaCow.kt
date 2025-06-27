package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Cow
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.world.AquaWorld

open class AquaCow(world: AquaWorld) : AquaAnimal(world), Cow {

    override val type: AquaEntityType<AquaCow>
        get() = AquaEntityTypes.COW

    override fun soundVolume(): Float = 0.4F

    companion object {

        private const val DEFAULT_MAX_HEALTH = 10.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.2

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
    }
}

package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Giant
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.world.AquaWorld

class AquaGiant(world: AquaWorld) : AquaMonster(world), Giant {

    override val type: AquaEntityType<AquaGiant>
        get() = AquaEntityTypes.GIANT

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMonster.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, 100.0)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, 0.5)
            .add(AquaAttributeTypes.ATTACK_DAMAGE, 50.0)
    }
}

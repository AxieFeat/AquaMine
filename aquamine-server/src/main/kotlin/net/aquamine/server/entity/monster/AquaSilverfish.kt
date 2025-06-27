package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Silverfish
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.world.AquaWorld

class AquaSilverfish(world: AquaWorld) : AquaMonster(world), Silverfish {

    override val type: AquaEntityType<AquaSilverfish>
        get() = AquaEntityTypes.SILVERFISH

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMonster.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, 8.0)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, 0.25)
            .add(AquaAttributeTypes.ATTACK_DAMAGE, 1.0)
    }
}

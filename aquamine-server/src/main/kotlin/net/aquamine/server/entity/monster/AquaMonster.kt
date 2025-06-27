package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Monster
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.world.AquaWorld

@Suppress("UnnecessaryAbstractClass") // This class is designed for inheritance, not instantiation.
abstract class AquaMonster(world: AquaWorld) : AquaMob(world), Monster {

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes().add(AquaAttributeTypes.ATTACK_DAMAGE)
    }
}

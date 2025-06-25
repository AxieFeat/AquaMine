package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Monster
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.world.KryptonWorld

@Suppress("UnnecessaryAbstractClass") // This class is designed for inheritance, not instantiation.
abstract class KryptonMonster(world: KryptonWorld) : KryptonMob(world), Monster {

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes().add(KryptonAttributeTypes.ATTACK_DAMAGE)
    }
}

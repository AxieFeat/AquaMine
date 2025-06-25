package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Giant
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.world.KryptonWorld

class KryptonGiant(world: KryptonWorld) : KryptonMonster(world), Giant {

    override val type: KryptonEntityType<KryptonGiant>
        get() = KryptonEntityTypes.GIANT

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMonster.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, 100.0)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, 0.5)
            .add(KryptonAttributeTypes.ATTACK_DAMAGE, 50.0)
    }
}

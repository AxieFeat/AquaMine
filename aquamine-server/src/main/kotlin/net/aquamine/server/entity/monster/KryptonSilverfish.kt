package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Silverfish
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.world.KryptonWorld

class KryptonSilverfish(world: KryptonWorld) : KryptonMonster(world), Silverfish {

    override val type: KryptonEntityType<KryptonSilverfish>
        get() = KryptonEntityTypes.SILVERFISH

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMonster.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, 8.0)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, 0.25)
            .add(KryptonAttributeTypes.ATTACK_DAMAGE, 1.0)
    }
}

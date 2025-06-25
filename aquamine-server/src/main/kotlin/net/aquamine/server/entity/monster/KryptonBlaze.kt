package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Blaze
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.world.KryptonWorld

class KryptonBlaze(world: KryptonWorld) : KryptonMonster(world), Blaze {

    override val type: KryptonEntityType<KryptonBlaze>
        get() = KryptonEntityTypes.BLAZE

    override var isOnFire: Boolean
        get() = data.getFlag(MetadataKeys.Blaze.FLAGS, 0)
        set(value) = data.setFlag(MetadataKeys.Blaze.FLAGS, 0, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Blaze.FLAGS, 0)
    }

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMonster.attributes()
            .add(KryptonAttributeTypes.ATTACK_DAMAGE, 6.0)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, 0.23F.toDouble())
            .add(KryptonAttributeTypes.FOLLOW_RANGE, 48.0)
    }
}

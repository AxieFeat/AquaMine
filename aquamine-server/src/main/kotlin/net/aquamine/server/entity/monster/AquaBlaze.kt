package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Blaze
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.world.AquaWorld

class AquaBlaze(world: AquaWorld) : AquaMonster(world), Blaze {

    override val type: AquaEntityType<AquaBlaze>
        get() = AquaEntityTypes.BLAZE

    override var isOnFire: Boolean
        get() = data.getFlag(MetadataKeys.Blaze.FLAGS, 0)
        set(value) = data.setFlag(MetadataKeys.Blaze.FLAGS, 0, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Blaze.FLAGS, 0)
    }

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMonster.attributes()
            .add(AquaAttributeTypes.ATTACK_DAMAGE, 6.0)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, 0.23F.toDouble())
            .add(AquaAttributeTypes.FOLLOW_RANGE, 48.0)
    }
}

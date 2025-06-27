package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Guardian
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.world.AquaWorld

class AquaGuardian(world: AquaWorld) : AquaMonster(world), Guardian {

    override val type: AquaEntityType<AquaGuardian>
        get() = AquaEntityTypes.GUARDIAN

    override var isMoving: Boolean
        get() = data.get(MetadataKeys.Guardian.MOVING)
        set(value) = data.set(MetadataKeys.Guardian.MOVING, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Guardian.MOVING, false)
        data.define(MetadataKeys.Guardian.TARGET_ID, 0)
    }

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMonster.attributes()
            .add(AquaAttributeTypes.ATTACK_DAMAGE, 6.0)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, 0.5)
            .add(AquaAttributeTypes.FOLLOW_RANGE, 16.0)
            .add(AquaAttributeTypes.MAX_HEALTH, 30.0)
    }
}

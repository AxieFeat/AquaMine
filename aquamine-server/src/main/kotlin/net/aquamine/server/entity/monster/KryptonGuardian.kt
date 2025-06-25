package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Guardian
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.world.KryptonWorld

class KryptonGuardian(world: KryptonWorld) : KryptonMonster(world), Guardian {

    override val type: KryptonEntityType<KryptonGuardian>
        get() = KryptonEntityTypes.GUARDIAN

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
        fun attributes(): AttributeSupplier.Builder = KryptonMonster.attributes()
            .add(KryptonAttributeTypes.ATTACK_DAMAGE, 6.0)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, 0.5)
            .add(KryptonAttributeTypes.FOLLOW_RANGE, 16.0)
            .add(KryptonAttributeTypes.MAX_HEALTH, 30.0)
    }
}

package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Goat
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.GoatSerializer
import net.aquamine.server.world.AquaWorld

class AquaGoat(world: AquaWorld) : AquaAnimal(world), Goat {

    override val type: AquaEntityType<AquaGoat>
        get() = AquaEntityTypes.GOAT
    override val serializer: EntitySerializer<AquaGoat>
        get() = GoatSerializer

    override var canScream: Boolean
        get() = data.get(MetadataKeys.Goat.SCREAMING)
        set(value) = data.set(MetadataKeys.Goat.SCREAMING, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Goat.SCREAMING, false)
    }

    override fun onAgeTransformation() {
        getAttribute(AquaAttributeTypes.ATTACK_DAMAGE)?.baseValue = if (isBaby) BABY_ATTACK_DAMAGE else ADULT_ATTACK_DAMAGE
    }

    companion object {

        private const val BABY_ATTACK_DAMAGE = 1.0
        private const val ADULT_ATTACK_DAMAGE = 2.0

        private const val DEFAULT_MAX_HEALTH = 10.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.2
        private const val DEFAULT_ATTACK_DAMAGE = 2.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(AquaAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
    }
}

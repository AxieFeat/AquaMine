package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Goat
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.GoatSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonGoat(world: KryptonWorld) : KryptonAnimal(world), Goat {

    override val type: KryptonEntityType<KryptonGoat>
        get() = KryptonEntityTypes.GOAT
    override val serializer: EntitySerializer<KryptonGoat>
        get() = GoatSerializer

    override var canScream: Boolean
        get() = data.get(MetadataKeys.Goat.SCREAMING)
        set(value) = data.set(MetadataKeys.Goat.SCREAMING, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Goat.SCREAMING, false)
    }

    override fun onAgeTransformation() {
        getAttribute(KryptonAttributeTypes.ATTACK_DAMAGE)?.baseValue = if (isBaby) BABY_ATTACK_DAMAGE else ADULT_ATTACK_DAMAGE
    }

    companion object {

        private const val BABY_ATTACK_DAMAGE = 1.0
        private const val ADULT_ATTACK_DAMAGE = 2.0

        private const val DEFAULT_MAX_HEALTH = 10.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.2
        private const val DEFAULT_ATTACK_DAMAGE = 2.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(KryptonAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
    }
}

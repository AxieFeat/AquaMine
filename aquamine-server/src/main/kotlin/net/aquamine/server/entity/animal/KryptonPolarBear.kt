package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.PolarBear
import net.aquamine.api.item.ItemStack
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.components.Neutral
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.PolarBearSerializer
import net.aquamine.server.util.provider.UniformInt
import net.aquamine.server.world.KryptonWorld
import java.util.UUID

class KryptonPolarBear(world: KryptonWorld) : KryptonAnimal(world), PolarBear, Neutral {

    override val type: KryptonEntityType<KryptonPolarBear>
        get() = KryptonEntityTypes.POLAR_BEAR
    override val serializer: EntitySerializer<KryptonPolarBear>
        get() = PolarBearSerializer

    override var remainingAngerTime: Int = 0
    override var angerTarget: UUID? = null

    override var isStanding: Boolean
        get() = data.get(MetadataKeys.PolarBear.STANDING)
        set(value) = data.set(MetadataKeys.PolarBear.STANDING, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.PolarBear.STANDING, false)
    }

    override fun startAngerTimer() {
        remainingAngerTime = PERSISTENT_ANGER_TIME.sample(random)
    }

    override fun isFood(item: ItemStack): Boolean = false

    companion object {

        private val PERSISTENT_ANGER_TIME = UniformInt(20 * 20, 39 * 20)
        private const val DEFAULT_MAX_HEALTH = 30.0
        private const val DEFAULT_FOLLOW_RANGE = 20.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.25
        private const val DEFAULT_ATTACK_DAMAGE = 6.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(KryptonAttributeTypes.FOLLOW_RANGE, DEFAULT_FOLLOW_RANGE)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(KryptonAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
    }
}

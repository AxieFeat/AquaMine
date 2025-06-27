package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.PolarBear
import net.aquamine.api.item.ItemStack
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.components.Neutral
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.PolarBearSerializer
import net.aquamine.server.util.provider.UniformInt
import net.aquamine.server.world.AquaWorld
import java.util.UUID

class AquaPolarBear(world: AquaWorld) : AquaAnimal(world), PolarBear, Neutral {

    override val type: AquaEntityType<AquaPolarBear>
        get() = AquaEntityTypes.POLAR_BEAR
    override val serializer: EntitySerializer<AquaPolarBear>
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
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.FOLLOW_RANGE, DEFAULT_FOLLOW_RANGE)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(AquaAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
    }
}

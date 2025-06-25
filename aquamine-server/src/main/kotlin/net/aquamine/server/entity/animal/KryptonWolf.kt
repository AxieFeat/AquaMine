package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Animal
import net.aquamine.api.entity.animal.Wolf
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.data.DyeColor
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.components.Neutral
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.WolfSerializer
import net.aquamine.server.util.enumhelper.DyeColors
import net.aquamine.server.util.provider.UniformInt
import net.aquamine.server.world.KryptonWorld
import java.util.UUID

class KryptonWolf(world: KryptonWorld) : KryptonTamable(world), Wolf, Neutral {

    override val type: KryptonEntityType<KryptonWolf>
        get() = KryptonEntityTypes.WOLF
    override val serializer: EntitySerializer<KryptonWolf>
        get() = WolfSerializer

    override var angerTarget: UUID? = null
    override var isAngry: Boolean
        get() = remainingAngerTime > 0
        set(value) = if (value) startAngerTimer() else stopBeingAngry()

    override var collarColor: DyeColor
        get() = DyeColors.fromId(data.get(MetadataKeys.Wolf.COLLAR_COLOR))
        set(value) = data.set(MetadataKeys.Wolf.COLLAR_COLOR, value.ordinal)
    override var isBeggingForFood: Boolean
        get() = data.get(MetadataKeys.Wolf.BEGGING)
        set(value) = data.set(MetadataKeys.Wolf.BEGGING, value)
    override var remainingAngerTime: Int
        get() = data.get(MetadataKeys.Wolf.ANGER_TIME)
        set(value) = data.set(MetadataKeys.Wolf.ANGER_TIME, value)

    override var isTamed: Boolean
        get() = super.isTamed
        set(value) {
            super.isTamed = value
            if (value) {
                getAttribute(KryptonAttributeTypes.MAX_HEALTH)?.baseValue = TAMED_HEALTH
                health = TAMED_HEALTH.toFloat()
            } else {
                getAttribute(KryptonAttributeTypes.MAX_HEALTH)?.baseValue = UNTAMED_HEALTH
            }
            getAttribute(KryptonAttributeTypes.ATTACK_DAMAGE)?.baseValue = TAME_UPDATE_ATTACK_DAMAGE
        }

    init {
        isTamed = false
    }

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Wolf.BEGGING, false)
        data.define(MetadataKeys.Wolf.COLLAR_COLOR, DyeColor.RED.ordinal)
        data.define(MetadataKeys.Wolf.ANGER_TIME, 0)
    }

    override fun startAngerTimer() {
        remainingAngerTime = PERSISTENT_ANGER_TIME.sample(random)
    }

    // TODO: Check if item type is meat
    override fun isFood(item: ItemStack): Boolean = item.type.isEdible

    override fun canMate(target: Animal): Boolean {
        if (target === this) return false
        if (!isTamed) return false
        if (target !is Wolf) return false
        if (!target.isTamed) return false
        if (target.isSitting) return false
        return isInLove() && target.isInLove()
    }

    override fun soundVolume(): Float = 0.4F

    companion object {

        private const val TAMED_HEALTH = 20.0
        private const val UNTAMED_HEALTH = 8.0
        private const val TAME_UPDATE_ATTACK_DAMAGE = 4.0
        private val PERSISTENT_ANGER_TIME = UniformInt(20 * 20, 39 * 20)

        private const val DEFAULT_MOVEMENT_SPEED = 0.3
        private const val DEFAULT_MAX_HEALTH = 8.0
        private const val DEFAULT_ATTACK_DAMAGE = 2.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes()
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(KryptonAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
    }
}

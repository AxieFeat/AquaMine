package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Bee
import net.aquamine.api.item.ItemStack
import net.aquamine.api.tags.ItemTags
import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.components.Neutral
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.BeeSerializer
import net.aquamine.server.item.downcast
import net.aquamine.server.util.provider.UniformInt
import net.aquamine.server.world.KryptonWorld
import java.util.UUID

class KryptonBee(world: KryptonWorld) : KryptonAnimal(world), Bee, Neutral {

    override val type: KryptonEntityType<KryptonBee>
        get() = KryptonEntityTypes.BEE
    override val serializer: EntitySerializer<KryptonBee>
        get() = BeeSerializer

    override var cannotEnterHiveTicks: Int = 0
    override var hive: Vec3i? = null
    override var flower: Vec3i? = null
    override var angerTarget: UUID? = null
    internal var timeSincePollination = 0
    internal var cropsGrownSincePollination = 0

    override var isAngry: Boolean
        get() = data.getFlag(MetadataKeys.Bee.FLAGS, FLAG_ANGRY)
        set(value) = data.setFlag(MetadataKeys.Bee.FLAGS, FLAG_ANGRY, value)
    override var hasStung: Boolean
        get() = data.getFlag(MetadataKeys.Bee.FLAGS, FLAG_STUNG)
        set(value) = data.setFlag(MetadataKeys.Bee.FLAGS, FLAG_STUNG, value)
    override var hasNectar: Boolean
        get() = data.getFlag(MetadataKeys.Bee.FLAGS, FLAG_NECTAR)
        set(value) {
            if (value) timeSincePollination = 0
            data.setFlag(MetadataKeys.Bee.FLAGS, FLAG_NECTAR, value)
        }
    override var remainingAngerTime: Int
        get() = data.get(MetadataKeys.Bee.ANGER_TIME)
        set(value) = data.set(MetadataKeys.Bee.ANGER_TIME, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Bee.FLAGS, 0)
        data.define(MetadataKeys.Bee.ANGER_TIME, 0)
    }

    override fun startAngerTimer() {
        remainingAngerTime = PERSISTENT_ANGER_TIME.sample(random)
    }

    override fun isFood(item: ItemStack): Boolean = item.type.downcast().eq(ItemTags.FLOWERS)

    override fun soundVolume(): Float = 0.4F

    companion object {

        private const val FLAG_ANGRY = 1
        private const val FLAG_STUNG = 2
        private const val FLAG_NECTAR = 3
        private val PERSISTENT_ANGER_TIME = UniformInt(20 * 20, 39 * 20)

        private const val DEFAULT_MAX_HEALTH = 10.0
        private const val DEFAULT_FLYING_SPEED = 0.6
        private const val DEFAULT_MOVEMENT_SPEED = 0.3
        private const val DEFAULT_ATTACK_DAMAGE = 2.0
        private const val DEFAULT_FOLLOW_RANGE = 48.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(KryptonAttributeTypes.FLYING_SPEED, DEFAULT_FLYING_SPEED)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(KryptonAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
            .add(KryptonAttributeTypes.FOLLOW_RANGE, DEFAULT_FOLLOW_RANGE)
    }
}

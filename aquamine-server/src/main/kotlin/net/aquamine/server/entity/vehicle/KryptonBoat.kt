package net.aquamine.server.entity.vehicle

import net.aquamine.api.entity.vehicle.Boat
import net.aquamine.api.entity.vehicle.BoatVariant
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.vehicle.BoatSerializer
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.damage.KryptonDamageSource

open class KryptonBoat(world: KryptonWorld) : KryptonEntity(world), Boat {

    override val type: KryptonEntityType<KryptonBoat>
        get() = KryptonEntityTypes.BOAT
    override val serializer: EntitySerializer<out KryptonBoat>
        get() = BoatSerializer

    override var variant: BoatVariant
        get() = TYPES.getOrNull(data.get(MetadataKeys.Boat.TYPE)) ?: BoatVariant.OAK
        set(value) = data.set(MetadataKeys.Boat.TYPE, value.ordinal)
    override var damageTaken: Float
        get() = data.get(MetadataKeys.Boat.DAMAGE)
        set(value) = data.set(MetadataKeys.Boat.DAMAGE, value)
    override var damageTimer: Int
        get() = data.get(MetadataKeys.Boat.HURT_TIMER)
        set(value) = data.set(MetadataKeys.Boat.HURT_TIMER, value)
    override var isLeftPaddleTurning: Boolean
        get() = data.get(MetadataKeys.Boat.LEFT_PADDLE_TURNING)
        set(value) = data.set(MetadataKeys.Boat.LEFT_PADDLE_TURNING, value)
    override var isRightPaddleTurning: Boolean
        get() = data.get(MetadataKeys.Boat.RIGHT_PADDLE_TURNING)
        set(value) = data.set(MetadataKeys.Boat.RIGHT_PADDLE_TURNING, value)
    private var damageDirection: Int
        get() = data.get(MetadataKeys.Boat.HURT_DIRECTION)
        set(value) = data.set(MetadataKeys.Boat.HURT_DIRECTION, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Boat.HURT_TIMER, 0)
        data.define(MetadataKeys.Boat.HURT_DIRECTION, 1)
        data.define(MetadataKeys.Boat.DAMAGE, 0F)
        data.define(MetadataKeys.Boat.TYPE, BoatVariant.OAK.ordinal)
        data.define(MetadataKeys.Boat.LEFT_PADDLE_TURNING, false)
        data.define(MetadataKeys.Boat.RIGHT_PADDLE_TURNING, false)
        data.define(MetadataKeys.Boat.SPLASH_TIMER, 0)
    }

    override fun damage(source: KryptonDamageSource, damage: Float): Boolean {
        if (isInvulnerableTo(source)) return false
        if (isRemoved()) return true
        damageDirection = -damageDirection
        damageTimer = DEFAULT_DAMAGE_TIMER
        damageTaken += damage * DAMAGE_INCREASE_MULTIPLIER
        markDamaged()
        return true
    }

    companion object {

        private const val DEFAULT_DAMAGE_TIMER = 10
        private const val DAMAGE_INCREASE_MULTIPLIER = 10F

        private val TYPES = BoatVariant.values()
    }
}

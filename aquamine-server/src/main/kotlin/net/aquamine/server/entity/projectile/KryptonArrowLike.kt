package net.aquamine.server.entity.projectile

import net.aquamine.api.block.BlockState
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.entity.projectile.ArrowLike
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.ArrowLikeSerializer
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.block.state.downcast

abstract class KryptonArrowLike(world: KryptonWorld) : KryptonProjectile(world), ArrowLike {

    override val serializer: EntitySerializer<out KryptonArrowLike>
        get() = ArrowLikeSerializer

    final override var baseDamage: Double = 2.0
    final override var isInGround: Boolean = false
    var life: Int = 0
    var shakeTime: Int = 0
    var sound: SoundEvent = defaultHitGroundSound()
    final override var stuckInBlock: BlockState?
        get() = internalStuckInBlock
        set(value) {
            internalStuckInBlock = value?.downcast()
        }
    var internalStuckInBlock: KryptonBlockState? = null
    final override var pickupRule: ArrowLike.PickupRule = ArrowLike.PickupRule.DISALLOWED

    final override var isCritical: Boolean
        get() = data.getFlag(MetadataKeys.ArrowLike.FLAGS, FLAG_CRITICAL)
        set(value) = data.setFlag(MetadataKeys.ArrowLike.FLAGS, FLAG_CRITICAL, value)
    final override var ignoresPhysics: Boolean
        get() = data.getFlag(MetadataKeys.ArrowLike.FLAGS, FLAG_IGNORES_PHYSICS)
        set(value) = data.setFlag(MetadataKeys.ArrowLike.FLAGS, FLAG_IGNORES_PHYSICS, value)
    final override var wasShotFromCrossbow: Boolean
        get() = data.getFlag(MetadataKeys.ArrowLike.FLAGS, FLAG_WAS_SHOT_FROM_CROSSBOW)
        set(value) = data.setFlag(MetadataKeys.ArrowLike.FLAGS, FLAG_WAS_SHOT_FROM_CROSSBOW, value)
    final override var piercingLevel: Int
        get() = data.get(MetadataKeys.ArrowLike.PIERCING_LEVEL).toInt()
        set(value) = data.set(MetadataKeys.ArrowLike.PIERCING_LEVEL, value.toByte())

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.ArrowLike.FLAGS, 0)
        data.define(MetadataKeys.ArrowLike.PIERCING_LEVEL, 0)
    }

    internal open fun defaultHitGroundSound(): SoundEvent = SoundEvents.ARROW_HIT.get()

    companion object {

        private const val FLAG_CRITICAL = 0
        private const val FLAG_IGNORES_PHYSICS = 1
        private const val FLAG_WAS_SHOT_FROM_CROSSBOW = 2
    }
}

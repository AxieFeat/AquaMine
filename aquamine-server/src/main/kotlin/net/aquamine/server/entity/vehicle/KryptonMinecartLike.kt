package net.aquamine.server.entity.vehicle

import net.aquamine.api.block.BlockState
import net.aquamine.api.entity.vehicle.MinecartLike
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.vehicle.MinecartLikeSerializer
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.block.KryptonBlock
import net.aquamine.server.world.block.KryptonBlocks
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.block.state.downcast
import net.aquamine.server.world.damage.KryptonDamageSource
import net.aquamine.server.world.damage.KryptonEntityDamageSource

abstract class KryptonMinecartLike(world: KryptonWorld) : KryptonEntity(world), MinecartLike {

    override val serializer: EntitySerializer<out KryptonMinecartLike>
        get() = MinecartLikeSerializer

    override var damageTaken: Float
        get() = data.get(MetadataKeys.MinecartLike.DAMAGE)
        set(value) = data.set(MetadataKeys.MinecartLike.DAMAGE, value)
    override var damageTimer: Int
        get() = data.get(MetadataKeys.MinecartLike.HURT_TIMER)
        set(value) = data.set(MetadataKeys.MinecartLike.HURT_TIMER, value)
    override var showCustomBlock: Boolean
        get() = data.get(MetadataKeys.MinecartLike.SHOW_CUSTOM_BLOCK)
        set(value) = data.set(MetadataKeys.MinecartLike.SHOW_CUSTOM_BLOCK, value)
    override var customBlock: BlockState
        get() = customBlock()
        set(value) {
            setCustomBlock(value.downcast())
        }
    override var customBlockOffset: Int
        get() = data.get(MetadataKeys.MinecartLike.CUSTOM_BLOCK_OFFSET)
        set(value) {
            data.set(MetadataKeys.MinecartLike.CUSTOM_BLOCK_OFFSET, value)
            showCustomBlock = value != defaultCustomBlockOffset()
        }
    private var hurtDirection: Int
        get() = data.get(MetadataKeys.MinecartLike.HURT_DIRECTION)
        set(value) = data.set(MetadataKeys.MinecartLike.HURT_DIRECTION, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.MinecartLike.HURT_TIMER, 0)
        data.define(MetadataKeys.MinecartLike.HURT_DIRECTION, 1)
        data.define(MetadataKeys.MinecartLike.DAMAGE, 0F)
        data.define(MetadataKeys.MinecartLike.CUSTOM_BLOCK_ID, KryptonRegistries.BLOCK.getId(KryptonBlocks.AIR))
        data.define(MetadataKeys.MinecartLike.CUSTOM_BLOCK_OFFSET, DEFAULT_CUSTOM_BLOCK_OFFSET)
        data.define(MetadataKeys.MinecartLike.SHOW_CUSTOM_BLOCK, false)
    }

    override fun damage(source: KryptonDamageSource, damage: Float): Boolean {
        if (isRemoved()) return true
        if (isInvulnerableTo(source)) return false
        hurtDirection = -hurtDirection
        damageTimer = DEFAULT_DAMAGE_TIMER
        markDamaged()
        damageTaken += damage * DAMAGE_INCREASE_MULTIPLIER
        val canInstantlyBuild = source is KryptonEntityDamageSource && source.entity is KryptonPlayer && source.entity.abilities.canInstantlyBuild
        if (canInstantlyBuild || damageTaken > MAX_DAMAGE) {
            ejectPassengers()
            remove()
            //if (canInstantlyBuild && customName == null) remove()
        }
        return true
    }

    fun customBlock(): KryptonBlockState {
        if (!showCustomBlock) return defaultCustomBlock()
        return KryptonBlock.stateFromId(data.get(MetadataKeys.MinecartLike.CUSTOM_BLOCK_ID))
    }

    fun setCustomBlock(block: KryptonBlockState) {
        data.set(MetadataKeys.MinecartLike.CUSTOM_BLOCK_ID, KryptonBlock.idOf(block))
        showCustomBlock = block !== defaultCustomBlock()
    }

    protected open fun defaultCustomBlock(): KryptonBlockState = KryptonBlocks.AIR.defaultState

    protected open fun defaultCustomBlockOffset(): Int = 0

    companion object {

        private const val DEFAULT_DAMAGE_TIMER = 10
        private const val DAMAGE_INCREASE_MULTIPLIER = 10F
        private const val MAX_DAMAGE = 40F
        private const val DEFAULT_CUSTOM_BLOCK_OFFSET = 6
    }
}

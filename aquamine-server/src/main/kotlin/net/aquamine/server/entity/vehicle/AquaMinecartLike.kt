package net.aquamine.server.entity.vehicle

import net.aquamine.api.block.BlockState
import net.aquamine.api.entity.vehicle.MinecartLike
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.vehicle.MinecartLikeSerializer
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.block.state.downcast
import net.aquamine.server.world.damage.AquaDamageSource
import net.aquamine.server.world.damage.AquaEntityDamageSource

abstract class AquaMinecartLike(world: AquaWorld) : AquaEntity(world), MinecartLike {

    override val serializer: EntitySerializer<out AquaMinecartLike>
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
        data.define(MetadataKeys.MinecartLike.CUSTOM_BLOCK_ID, AquaRegistries.BLOCK.getId(AquaBlocks.AIR))
        data.define(MetadataKeys.MinecartLike.CUSTOM_BLOCK_OFFSET, DEFAULT_CUSTOM_BLOCK_OFFSET)
        data.define(MetadataKeys.MinecartLike.SHOW_CUSTOM_BLOCK, false)
    }

    override fun damage(source: AquaDamageSource, damage: Float): Boolean {
        if (isRemoved()) return true
        if (isInvulnerableTo(source)) return false
        hurtDirection = -hurtDirection
        damageTimer = DEFAULT_DAMAGE_TIMER
        markDamaged()
        damageTaken += damage * DAMAGE_INCREASE_MULTIPLIER
        val canInstantlyBuild = source is AquaEntityDamageSource && source.entity is AquaPlayer && source.entity.abilities.canInstantlyBuild
        if (canInstantlyBuild || damageTaken > MAX_DAMAGE) {
            ejectPassengers()
            remove()
            //if (canInstantlyBuild && customName == null) remove()
        }
        return true
    }

    fun customBlock(): AquaBlockState {
        if (!showCustomBlock) return defaultCustomBlock()
        return AquaBlock.stateFromId(data.get(MetadataKeys.MinecartLike.CUSTOM_BLOCK_ID))
    }

    fun setCustomBlock(block: AquaBlockState) {
        data.set(MetadataKeys.MinecartLike.CUSTOM_BLOCK_ID, AquaBlock.idOf(block))
        showCustomBlock = block !== defaultCustomBlock()
    }

    protected open fun defaultCustomBlock(): AquaBlockState = AquaBlocks.AIR.defaultState

    protected open fun defaultCustomBlockOffset(): Int = 0

    companion object {

        private const val DEFAULT_DAMAGE_TIMER = 10
        private const val DAMAGE_INCREASE_MULTIPLIER = 10F
        private const val MAX_DAMAGE = 40F
        private const val DEFAULT_CUSTOM_BLOCK_OFFSET = 6
    }
}

package net.aquamine.server.entity.vehicle

import net.kyori.adventure.sound.Sound
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.entity.vehicle.MinecartVariant
import net.aquamine.api.entity.vehicle.TNTMinecart
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.vehicle.TNTMinecartSerializer
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.damage.AquaDamageSource

class AquaTNTMinecart(world: AquaWorld) : AquaMinecartLike(world), TNTMinecart {

    override val type: AquaEntityType<AquaTNTMinecart>
        get() = AquaEntityTypes.TNT_MINECART
    override val serializer: EntitySerializer<AquaTNTMinecart>
        get() = TNTMinecartSerializer

    override val variant: MinecartVariant
        get() = MinecartVariant.TNT
    override var fuse: Int = -1

    override fun isPrimed(): Boolean = fuse > -1

    override fun prime() {
        fuse = PRIMED_FUSE
        if (!isSilent) world.playSound(this, SoundEvents.TNT_PRIMED.get(), Sound.Source.BLOCK, 1F, 1F)
    }

    @Suppress("ExpressionBodySyntax") // There will be logic here
    override fun damage(source: AquaDamageSource, damage: Float): Boolean {
        /* TODO: Explode the minecart if shot with a flaming arrow
        val entity = if (source is KryptonIndirectEntityDamageSource) source.entity else null
        if (entity is KryptonArrowLike && entity.isOnFire) {
        }
         */
        return super.damage(source, damage)
    }

    override fun defaultCustomBlock(): AquaBlockState = AquaBlocks.TNT.defaultState

    companion object {

        private const val PRIMED_FUSE = 80
    }
}

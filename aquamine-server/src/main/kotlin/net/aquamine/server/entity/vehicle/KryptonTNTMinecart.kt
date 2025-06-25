package net.aquamine.server.entity.vehicle

import net.kyori.adventure.sound.Sound
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.entity.vehicle.MinecartVariant
import net.aquamine.api.entity.vehicle.TNTMinecart
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.vehicle.TNTMinecartSerializer
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.block.KryptonBlocks
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.damage.KryptonDamageSource

class KryptonTNTMinecart(world: KryptonWorld) : KryptonMinecartLike(world), TNTMinecart {

    override val type: KryptonEntityType<KryptonTNTMinecart>
        get() = KryptonEntityTypes.TNT_MINECART
    override val serializer: EntitySerializer<KryptonTNTMinecart>
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
    override fun damage(source: KryptonDamageSource, damage: Float): Boolean {
        /* TODO: Explode the minecart if shot with a flaming arrow
        val entity = if (source is KryptonIndirectEntityDamageSource) source.entity else null
        if (entity is KryptonArrowLike && entity.isOnFire) {
        }
         */
        return super.damage(source, damage)
    }

    override fun defaultCustomBlock(): KryptonBlockState = KryptonBlocks.TNT.defaultState

    companion object {

        private const val PRIMED_FUSE = 80
    }
}

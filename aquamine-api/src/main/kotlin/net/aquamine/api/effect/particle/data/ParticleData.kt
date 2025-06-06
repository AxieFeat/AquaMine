package net.aquamine.api.effect.particle.data

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import net.aquamine.api.AquaMine
import net.aquamine.api.block.BlockState
import net.aquamine.api.item.ItemStack
import net.aquamine.api.util.Color
import net.aquamine.api.util.Vec3d

/**
 * The supertype of all particle data. Merely serves as a marker interface for
 * its subtypes.
 */
@ImmutableType
interface ParticleData {

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun directional(direction: Vec3d?, velocity: Float): DirectionalParticleData

        fun item(item: ItemStack): ItemParticleData

        fun block(block: BlockState): BlockParticleData

        fun color(color: Color): ColorParticleData

        fun dust(color: Color, scale: Float): DustParticleData

        fun transition(from: Color, scale: Float, to: Color): DustTransitionParticleData

        fun note(note: Byte): NoteParticleData

        fun vibration(destination: Vec3d, ticks: Int): VibrationParticleData
    }

    companion object {

        @JvmSynthetic
        internal fun factory(): Factory = AquaMine.factory()
    }
}

package net.aquamine.server.effect.particle.data

import net.aquamine.api.block.BlockState
import net.aquamine.api.effect.particle.data.BlockParticleData
import net.aquamine.api.effect.particle.data.ColorParticleData
import net.aquamine.api.effect.particle.data.DirectionalParticleData
import net.aquamine.api.effect.particle.data.DustParticleData
import net.aquamine.api.effect.particle.data.DustTransitionParticleData
import net.aquamine.api.effect.particle.data.ItemParticleData
import net.aquamine.api.effect.particle.data.NoteParticleData
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.api.effect.particle.data.VibrationParticleData
import net.aquamine.api.item.ItemStack
import net.aquamine.api.util.Color
import net.aquamine.api.util.Vec3d
import net.aquamine.server.item.downcast
import net.aquamine.server.world.block.state.downcast

object AquaParticleDataFactory : ParticleData.Factory {

    override fun block(block: BlockState): BlockParticleData = AquaBlockParticleData(block.downcast())

    override fun color(color: Color): ColorParticleData = AquaColorParticleData(color)

    override fun directional(direction: Vec3d?, velocity: Float): DirectionalParticleData = AquaDirectionalParticleData(direction, velocity)

    override fun dust(color: Color, scale: Float): DustParticleData = AquaDustParticleData(color, scale)

    override fun item(item: ItemStack): ItemParticleData = AquaItemParticleData(item.downcast())

    override fun note(note: Byte): NoteParticleData = AquaNoteParticleData(note)

    override fun transition(from: Color, scale: Float, to: Color): DustTransitionParticleData = AquaDustTransitionParticleData(from, scale, to)

    override fun vibration(destination: Vec3d, ticks: Int): VibrationParticleData = AquaVibrationParticleData(destination, ticks)
}

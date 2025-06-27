package net.aquamine.server.effect.particle.data

import net.aquamine.api.effect.particle.data.BlockParticleData
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.state.AquaBlockState

@JvmRecord
data class AquaBlockParticleData(override val block: AquaBlockState) : BlockParticleData, Writable {

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(AquaBlock.idOf(block))
    }
}

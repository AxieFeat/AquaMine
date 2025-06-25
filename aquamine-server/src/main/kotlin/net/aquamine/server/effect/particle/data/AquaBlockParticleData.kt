package net.aquamine.server.effect.particle.data

import net.aquamine.api.effect.particle.data.BlockParticleData
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.world.block.KryptonBlock
import net.aquamine.server.world.block.state.KryptonBlockState

@JvmRecord
data class AquaBlockParticleData(override val block: KryptonBlockState) : BlockParticleData, Writable {

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(KryptonBlock.idOf(block))
    }
}

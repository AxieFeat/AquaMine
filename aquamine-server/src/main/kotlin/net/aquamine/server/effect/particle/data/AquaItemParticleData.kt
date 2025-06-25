package net.aquamine.server.effect.particle.data

import net.aquamine.api.effect.particle.data.ItemParticleData
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryWriter

@JvmRecord
data class AquaItemParticleData(override val item: KryptonItemStack) : ItemParticleData, Writable {

    override fun write(writer: BinaryWriter) {
        writer.writeItem(item)
    }
}

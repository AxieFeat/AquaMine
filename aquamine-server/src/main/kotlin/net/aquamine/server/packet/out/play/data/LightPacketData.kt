package net.aquamine.server.packet.out.play.data

import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.world.chunk.KryptonChunk
import java.util.BitSet

@JvmRecord
data class LightPacketData(
    val trustEdges: Boolean,
    val skyMask: BitSet,
    val blockMask: BitSet,
    val emptySkyMask: BitSet,
    val emptyBlockMask: BitSet,
    val skyLights: List<ByteArray>,
    val blockLights: List<ByteArray>
) : Writable {

    constructor(reader: BinaryReader) : this(
        reader.readBoolean(),
        reader.readBitSet(),
        reader.readBitSet(),
        reader.readBitSet(),
        reader.readBitSet(),
        reader.readList { it.readByteArray() },
        reader.readList { it.readByteArray() }
    )

    override fun write(writer: BinaryWriter) {
        writer.writeBoolean(trustEdges)

        writer.writeBitSet(skyMask)
        writer.writeBitSet(blockMask)
        writer.writeBitSet(emptySkyMask)
        writer.writeBitSet(emptyBlockMask)

        writer.writeCollection(skyLights, writer::writeByteArray)
        writer.writeCollection(blockLights, writer::writeByteArray)
    }

    companion object {

        @JvmStatic
        fun fromChunk(chunk: KryptonChunk, trustEdges: Boolean): LightPacketData {
            val sections = chunk.sections()

            val skyMask = BitSet()
            val blockMask = BitSet()
            val emptySkyMask = BitSet()
            val emptyBlockMask = BitSet()
            val skyLights = ArrayList<ByteArray>()
            val blockLights = ArrayList<ByteArray>()

            for (i in sections.indices) {
                val section = sections[i]

                // Deal with sky light data
                if (hasNonZeroData(section.skyLight)) {
                    skyMask.set(i)
                    skyLights.add(section.skyLight!!)
                } else {
                    emptySkyMask.set(i)
                }

                // Deal with block light data
                if (hasNonZeroData(section.blockLight)) {
                    blockMask.set(i)
                    blockLights.add(section.blockLight!!)
                } else {
                    emptyBlockMask.set(i)
                }
            }

            return LightPacketData(trustEdges, skyMask, blockMask, emptySkyMask, emptyBlockMask, skyLights, blockLights)
        }

        @JvmStatic
        private fun hasNonZeroData(array: ByteArray?): Boolean {
            if (array == null) return false
            for (i in array.indices) {
                if (array[i] != 0.toByte()) return true
            }
            return false
        }
    }
}

package net.aquamine.server.packet.out.play.data

import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.world.chunk.KryptonChunk
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.ImmutableCompoundTag
import java.nio.ByteBuffer

@JvmRecord
@Suppress("ArrayInDataClass")
data class ChunkPacketData(val heightmaps: CompoundTag, val data: ByteArray) : Writable {

    constructor(reader: BinaryReader) : this(reader.readNBT(), reader.readByteArray()) {
        reader.readVarInt() // Number of block entities
    }

    override fun write(writer: BinaryWriter) {
        writer.writeNBT(heightmaps) // Heightmaps
        writer.writeByteArray(data) // Actual chunk data

        // TODO: When block entities are added, make use of this here
        writer.writeVarInt(0) // Number of block entities
    }

    companion object {

        @JvmStatic
        fun fromChunk(chunk: KryptonChunk): ChunkPacketData = ChunkPacketData(extractHeightmaps(chunk), extractData(chunk))

        @JvmStatic
        private fun extractHeightmaps(chunk: KryptonChunk): CompoundTag {
            if (chunk.heightmaps().isEmpty()) return CompoundTag.EMPTY
            val heightmaps = ImmutableCompoundTag.builder()
            chunk.heightmaps().forEach { if (it.key.sendToClient()) heightmaps.putLongArray(it.key.name, it.value.rawData()) }
            return heightmaps.build()
        }

        @JvmStatic
        private fun extractData(chunk: KryptonChunk): ByteArray {
            val result = ByteArray(chunk.sections().sumOf { it.calculateSerializedSize() })
            val writer = BinaryWriter(ByteBuffer.wrap(result))
            chunk.sections().forEach { it.write(writer) }
            return result
        }
    }
}

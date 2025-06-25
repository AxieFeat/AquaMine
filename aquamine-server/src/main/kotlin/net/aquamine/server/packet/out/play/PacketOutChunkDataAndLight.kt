package net.aquamine.server.packet.out.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.packet.out.play.data.ChunkPacketData
import net.aquamine.server.packet.out.play.data.LightPacketData
import net.aquamine.server.world.chunk.KryptonChunk

@JvmRecord
data class PacketOutChunkDataAndLight(val x: Int, val z: Int, val chunkData: ChunkPacketData, val lightData: LightPacketData) : Packet {

    constructor(reader: BinaryReader) : this(reader.readInt(), reader.readInt(), ChunkPacketData(reader), LightPacketData(reader))

    override fun write(writer: BinaryWriter) {
        writer.writeInt(x)
        writer.writeInt(z)
        chunkData.write(writer)
        lightData.write(writer)
    }

    companion object {

        @JvmStatic
        fun fromChunk(chunk: KryptonChunk, trustEdges: Boolean): PacketOutChunkDataAndLight {
            return PacketOutChunkDataAndLight(chunk.position.x, chunk.position.z, ChunkPacketData.fromChunk(chunk),
                LightPacketData.fromChunk(chunk, trustEdges))
        }
    }
}

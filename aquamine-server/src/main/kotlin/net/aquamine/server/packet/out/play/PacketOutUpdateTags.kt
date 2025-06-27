package net.aquamine.server.packet.out.play

import net.aquamine.api.registry.Registry
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.resource.AquaResourceKeys
import net.aquamine.server.tags.TagSerializer

@JvmRecord
data class PacketOutUpdateTags(val tags: Map<ResourceKey<out Registry<*>>, TagSerializer.NetworkPayload>) : Packet {

    constructor(reader: BinaryReader) : this(readTags(reader))

    override fun write(writer: BinaryWriter) {
        writer.writeMap(tags, BinaryWriter::writeResourceKey) { w, payload -> payload.write(w) }
    }

    companion object {

        @JvmStatic
        private fun readTags(reader: BinaryReader): Map<ResourceKey<out Registry<*>>, TagSerializer.NetworkPayload> {
            return reader.readMap({ ResourceKey.of(AquaResourceKeys.PARENT, it.readKey()) }, TagSerializer::NetworkPayload)
        }
    }
}

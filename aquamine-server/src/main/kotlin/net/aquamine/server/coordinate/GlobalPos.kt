package net.aquamine.server.coordinate

import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.World
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.util.serialization.Codecs
import net.aquamine.serialization.Codec
import net.aquamine.serialization.codecs.RecordCodecBuilder

@JvmRecord
data class GlobalPos(
    val dimension: ResourceKey<World>,
    val position: Vec3i
) : Writable {

    constructor(reader: BinaryReader) : this(
        dimension = ResourceKey.of(ResourceKeys.DIMENSION, reader.readKey()),
        position = reader.readBlockPos()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeResourceKey(dimension)
        writer.writeBlockPos(position)
    }

    companion object {

        @JvmField
        val CODEC: Codec<GlobalPos> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codecs.DIMENSION.fieldOf("dimension").getting { it.dimension },
                BlockPos.CODEC.fieldOf("pos").getting { it.position }
            ).apply(instance) { dimension, pos -> GlobalPos(dimension, pos) }
        }
    }
}

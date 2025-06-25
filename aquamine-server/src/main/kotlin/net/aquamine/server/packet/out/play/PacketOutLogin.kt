package net.aquamine.server.packet.out.play

import com.google.common.collect.Sets
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.api.world.GameMode
import net.aquamine.api.world.World
import net.aquamine.api.world.dimension.DimensionType
import net.aquamine.server.packet.EntityPacket
import net.aquamine.server.util.enumhelper.GameModes
import net.aquamine.server.coordinate.GlobalPos
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.registry.network.RegistrySerialization

@JvmRecord
data class PacketOutLogin(
    override val entityId: Int,
    val isHardcore: Boolean,
    val gameMode: GameMode,
    val oldGameMode: GameMode?,
    val dimensions: Set<ResourceKey<World>>,
    val registryHolder: RegistryHolder,
    val dimensionType: ResourceKey<DimensionType>,
    val dimension: ResourceKey<World>,
    val seed: Long,
    val maxPlayers: Int,
    val viewDistance: Int,
    val simulationDistance: Int,
    val reducedDebugInfo: Boolean,
    val enableRespawnScreen: Boolean,
    val isDebug: Boolean,
    val isFlat: Boolean,
    val deathLocation: GlobalPos?
) : EntityPacket {

    constructor(reader: BinaryReader) : this(
        reader.readInt(),
        reader.readBoolean(),
        GameModes.fromId(reader.readByte().toInt())!!,
        GameModes.fromId(reader.readByte().toInt())!!,
        reader.readCollection({ Sets.newHashSetWithExpectedSize(it) }) { ResourceKey.of(ResourceKeys.DIMENSION, reader.readKey()) },
        reader.decode(RegistrySerialization.NETWORK_CODEC),
        ResourceKey.of(ResourceKeys.DIMENSION_TYPE, reader.readKey()),
        ResourceKey.of(ResourceKeys.DIMENSION, reader.readKey()),
        reader.readLong(),
        reader.readVarInt(),
        reader.readVarInt(),
        reader.readVarInt(),
        reader.readBoolean(),
        reader.readBoolean(),
        reader.readBoolean(),
        reader.readBoolean(),
        reader.readNullable { GlobalPos(it) }
    )

    override fun write(writer: BinaryWriter) {
        writer.writeInt(entityId)
        writer.writeBoolean(isHardcore)
        writer.writeByte(gameMode.ordinal.toByte())
        val oldGameModeId = oldGameMode?.ordinal ?: -1
        writer.writeByte(oldGameModeId.toByte())
        writer.writeCollection(dimensions, writer::writeResourceKey)
        writer.encode(RegistrySerialization.NETWORK_CODEC, registryHolder)
        writer.writeResourceKey(dimensionType)
        writer.writeResourceKey(dimension)
        writer.writeLong(seed)
        writer.writeVarInt(maxPlayers)
        writer.writeVarInt(viewDistance)
        writer.writeVarInt(simulationDistance)
        writer.writeBoolean(reducedDebugInfo)
        writer.writeBoolean(enableRespawnScreen)
        writer.writeBoolean(isDebug)
        writer.writeBoolean(isFlat)
        writer.writeNullable(deathLocation) { _, pos -> pos.write(writer) }
    }
}

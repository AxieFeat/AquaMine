package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket
import net.aquamine.server.potion.AquaPotionType
import net.aquamine.server.registry.AquaRegistries

@JvmRecord
data class PacketInSetBeaconEffect(
    val primaryEffect: AquaPotionType?,
    val secondaryEffect: AquaPotionType?,
) : InboundPacket<PlayPacketHandler> {

    constructor(reader: BinaryReader) : this(
        primaryEffect = if(reader.readBoolean()) AquaRegistries.POTION_TYPE.get(reader.readVarInt()) else null,
        secondaryEffect = if(reader.readBoolean()) AquaRegistries.POTION_TYPE.get(reader.readVarInt()) else null,
    )

    override fun write(writer: BinaryWriter) {
        writer.writeBoolean(primaryEffect != null)
        if(primaryEffect != null) {
            writer.writeVarInt(AquaRegistries.POTION_TYPE.getId(primaryEffect))
        }
        writer.writeBoolean(secondaryEffect != null)
        if(secondaryEffect != null) {
            writer.writeVarInt(AquaRegistries.POTION_TYPE.getId(secondaryEffect))
        }
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleSetBeaconEffect(this)
    }
}

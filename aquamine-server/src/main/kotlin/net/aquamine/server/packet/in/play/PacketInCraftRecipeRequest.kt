package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInCraftRecipeRequest(
    val containerId: Int,
    val recipe: String,
    val makeAll: Boolean
) : InboundPacket<PlayPacketHandler> {

    init {
        require(recipe.length <= RECIPE_MAX_LENGTH) { "Recipe too long! Max: $RECIPE_MAX_LENGTH" }
    }

    constructor(reader: BinaryReader) : this(
        containerId = reader.readVarInt(),
        recipe = reader.readString(),
        makeAll = reader.readBoolean()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeVarInt(containerId)
        writer.writeString(recipe)
        writer.writeBoolean(makeAll)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleCraftRecipeRequest(this)
    }

    companion object {

        private const val RECIPE_MAX_LENGTH = 256
    }
}
package net.aquamine.server.packet.`in`.play

import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.network.handlers.PlayPacketHandler
import net.aquamine.server.packet.InboundPacket

@JvmRecord
data class PacketInSetDisplayedRecipe(
    val recipeId: String
) : InboundPacket<PlayPacketHandler> {

    init {
        require(recipeId.length <= RECIPE_MAX_LENGTH) { "Recipe too long! Max: $RECIPE_MAX_LENGTH" }
    }

    constructor(reader: BinaryReader) : this(
        recipeId = reader.readString()
    )

    override fun write(writer: BinaryWriter) {
        writer.writeString(recipeId)
    }

    override fun handle(handler: PlayPacketHandler) {
        handler.handleSetDisplayedRecipe(this)
    }

    companion object {

        private const val RECIPE_MAX_LENGTH = 256
    }
}
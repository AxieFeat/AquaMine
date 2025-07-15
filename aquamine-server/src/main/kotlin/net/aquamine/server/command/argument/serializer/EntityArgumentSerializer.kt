package net.aquamine.server.command.argument.serializer

import net.aquamine.server.command.arguments.entities.EntityArgumentType
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter

/**
 * Entity argument types are serialized with flags indicating if the selector
 * has a single target, and if it only targets players.
 *
 * See [here](https://minecraft.wiki/w/Java_Edition_protocol/Command_data#minecraft:entity)
 */
object EntityArgumentSerializer : FlaggedArgumentSerializer<EntityArgumentType> {

    override fun read(reader: BinaryReader, flags: Int): EntityArgumentType = EntityArgumentType.from(flags and 1 != 0, flags and 2 != 0)

    override fun write(writer: BinaryWriter, value: EntityArgumentType) {
        writer.writeByte(createFlags(value.singleTarget, value.onlyPlayers))
    }
}

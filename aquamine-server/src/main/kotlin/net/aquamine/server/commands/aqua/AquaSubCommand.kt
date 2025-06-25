package net.aquamine.server.commands.aqua

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.aquamine.server.command.CommandSourceStack
import java.util.stream.Stream

fun interface AquaSubCommand {

    fun aliases(): Stream<String> = Stream.empty()

    fun register(): LiteralArgumentBuilder<CommandSourceStack>
}

package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.locale.CommandMessages

object SeedCommand {

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("seed") {
            requiresPermission(AquaPermission.SEED)
            runs { CommandMessages.SEED.sendSuccess(it.source, it.source.world.seed.toString(), true) }
        })
    }
}

package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.locale.CommandMessages
import kotlin.system.exitProcess

object StopCommand {

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("stop") {
            requiresPermission(AquaPermission.STOP)
            runs {
                it.source.sendSuccess(CommandMessages.STOP, true)
                // We use exit rather than stop because if this is executed from the console, the server will shut down before
                // it is finished, due to the console handler thread being a daemon thread.
                // Therefore, to avoid this, we use exit, which will call the shutdown hook that calls stop.
                exitProcess(0)
            }
        })
    }
}

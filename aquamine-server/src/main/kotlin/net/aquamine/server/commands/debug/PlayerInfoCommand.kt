package net.aquamine.server.commands.debug

import com.mojang.brigadier.CommandDispatcher
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.entities.EntityArgumentType
import net.aquamine.server.commands.runs
import net.aquamine.server.network.NioConnection
import net.kyori.adventure.text.Component.newline
import net.kyori.adventure.text.Component.text

object PlayerInfoCommand {

    private const val TARGET = "target"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("info") {
            argument(TARGET, EntityArgumentType.player()) {
                runs { context ->
                    val sender = context.source.sender
                    val target = EntityArgumentType.getPlayers(context, TARGET).first()

                    val connection = target.connection as NioConnection

                    sender.sendMessage(
                        text {
                            it.append(
                                text("Information about ${target.name}:"),
                                newline(),
                                text("Connected via ${connection.worker.networkServer.address}:${connection.worker.networkServer.port} [B-${connection.worker.networkServer.number} | W-${connection.worker.number}]")
                            )
                        }
                    )
                }
            }
        })
    }
}
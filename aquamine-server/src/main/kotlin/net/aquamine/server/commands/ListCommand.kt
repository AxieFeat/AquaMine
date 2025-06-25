package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.aquamine.api.command.literal
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.locale.CommandMessages

object ListCommand {

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("list") {
            requiresPermission(KryptonPermission.LIST)
            runs { context -> sendNames(context.source) { PlainTextComponentSerializer.plainText().serialize(it.displayName) } }
            literal("uuids") {
                runs { context ->
                    sendNames(context.source) { "${PlainTextComponentSerializer.plainText().serialize(it.displayName)} (${it.uuid})" }
                }
            }
        })
    }

    @JvmStatic
    private inline fun sendNames(source: CommandSourceStack, nameGetter: (KryptonPlayer) -> String) {
        val names = source.server.players.map(nameGetter)
        CommandMessages.LIST_PLAYERS.sendSuccess(source, source.server.config.status.maxPlayers, names, false)
    }
}

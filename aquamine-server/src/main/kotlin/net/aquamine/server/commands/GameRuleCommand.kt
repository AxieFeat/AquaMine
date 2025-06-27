package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.locale.CommandMessages
import net.aquamine.server.world.rule.GameRuleKeys
import net.aquamine.server.world.rule.WorldGameRules

object GameRuleCommand {

    private const val VALUE = "value"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        val command = literalCommand("gamerule") { requiresPermission(AquaPermission.GAME_RULE) }
        GameRuleKeys.visitTypes(object : WorldGameRules.TypeVisitor {
            override fun <T : WorldGameRules.Value<T>> visit(key: WorldGameRules.Key<T>, type: WorldGameRules.Type<T>) {
                command.then(literalCommand(key.id) {
                    executes { queryRule(it.source, key) }
                    then(type.createArgument(VALUE).executes { setRule(it, key) })
                })
            }
        })
        dispatcher.register(command)
    }

    @JvmStatic
    private fun <T : WorldGameRules.Value<T>> setRule(context: CommandContext<CommandSourceStack>, key: WorldGameRules.Key<T>): Int {
        val value = context.source.server.worldManager.default.gameRules().getRule(key)
        value.setFromArgument(context, VALUE)
        CommandMessages.GAME_RULE_SET.sendSuccess(context.source, key.id, value.toString(), true)
        return value.commandResult()
    }

    @JvmStatic
    private fun <T : WorldGameRules.Value<T>> queryRule(source: CommandSourceStack, key: WorldGameRules.Key<T>): Int {
        val value = source.server.worldManager.default.gameRules().getRule(key)
        CommandMessages.GAME_RULE_QUERY.sendSuccess(source, key.id, value.toString(), false)
        return value.commandResult()
    }
}

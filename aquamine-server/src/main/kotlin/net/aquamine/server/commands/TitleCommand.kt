package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.kyori.adventure.text.Component
import net.aquamine.api.command.argument
import net.aquamine.api.command.literal
import net.aquamine.api.command.literalCommand
import net.aquamine.api.entity.player.Player
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.entities.EntityArgumentType
import net.aquamine.server.entity.player.AquaPlayer

object TitleCommand {

    private const val TARGETS = "targets"
    private const val FADE_IN = "fadeIn"
    private const val STAY = "stay"
    private const val FADE_OUT = "fadeOut"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("title") {
            requiresPermission(AquaPermission.TITLE)
            argument(TARGETS, EntityArgumentType.players()) {
                title(this@literalCommand, "actionbar", AquaPlayer::sendActionBar)
                title(this@literalCommand, "title", AquaPlayer::sendTitle)
                title(this@literalCommand, "subtitle", AquaPlayer::sendSubtitle)
                clearOrReset(this@literalCommand, "clear", "cleared", Player::clearTitle)
                clearOrReset(this@literalCommand, "reset", "reset", Player::resetTitle)
                times(this@literalCommand)
            }
        })
    }

    @JvmStatic
    private inline fun title(builder: LiteralArgumentBuilder<CommandSourceStack>, name: String,
                             crossinline action: (AquaPlayer, Component) -> Unit): LiteralArgumentBuilder<CommandSourceStack> {
        return builder.literal(name) {
            argument("message", StringArgumentType.string()) {
                runs { context ->
                    val targets = EntityArgumentType.getPlayers(context, TARGETS)
                    val message = Component.text(context.getArgument("message", String::class.java))
                    targets.forEach { action(it, message) }
                    sendFeedback(context.source, "commands.title.show.$name", targets.size)
                }
            }
        }
    }

    @JvmStatic
    private inline fun clearOrReset(builder: LiteralArgumentBuilder<CommandSourceStack>, name: String, translationKey: String,
                                    crossinline action: (AquaPlayer) -> Unit): LiteralArgumentBuilder<CommandSourceStack> {
        return builder.literal(name) {
            runs { context ->
                val targets = EntityArgumentType.getPlayers(context, TARGETS)
                targets.forEach(action)
                sendFeedback(context.source, "commands.title.$translationKey", targets.size)
            }
        }
    }

    @JvmStatic
    private fun times(builder: LiteralArgumentBuilder<CommandSourceStack>): LiteralArgumentBuilder<CommandSourceStack> = builder.literal("times") {
        argument(FADE_IN, IntegerArgumentType.integer()) {
            argument(STAY, IntegerArgumentType.integer()) {
                argument(FADE_OUT, IntegerArgumentType.integer()) {
                    runs { context ->
                        val targets = EntityArgumentType.getPlayers(context, TARGETS)
                        targets.forEach { it.sendTitleTimes(context.getArgument(FADE_IN), context.getArgument(STAY), context.getArgument(FADE_OUT)) }
                        sendFeedback(context.source, "commands.title.times", targets.size)
                    }
                }
            }
        }
    }

    @JvmStatic
    private fun sendFeedback(source: CommandSourceStack, key: String, targets: Int) {
        val feedbackKey = if (targets == 1) "$key.single" else "$key.multiple"
        val feedbackArgument = if (targets == 1) source.displayName else Component.text(targets)
        source.sendSuccess(Component.translatable(feedbackKey, feedbackArgument), true)
    }
}

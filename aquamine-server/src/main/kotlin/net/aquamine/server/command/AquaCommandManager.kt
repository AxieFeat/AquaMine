package net.aquamine.server.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.ParseResults
import com.mojang.brigadier.ResultConsumer
import com.mojang.brigadier.StringReader
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.tree.RootCommandNode
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.apache.logging.log4j.LogManager
import net.aquamine.api.adventure.AdventureMessage
import net.aquamine.api.command.BrigadierCommand
import net.aquamine.api.command.CommandManager
import net.aquamine.api.command.InvocableCommand
import net.aquamine.api.command.RawCommand
import net.aquamine.api.command.Sender
import net.aquamine.api.command.SimpleCommand
import net.aquamine.api.command.CommandMeta
import net.aquamine.api.entity.player.Player
import net.aquamine.server.commands.ClearCommand
import net.aquamine.server.commands.DifficultyCommand
import net.aquamine.server.commands.GameModeCommand
import net.aquamine.server.commands.GameRuleCommand
import net.aquamine.server.commands.GiveCommand
import net.aquamine.server.commands.KickCommand
import net.aquamine.server.commands.ListCommand
import net.aquamine.server.commands.MeCommand
import net.aquamine.server.commands.MessageCommand
import net.aquamine.server.commands.SayCommand
import net.aquamine.server.commands.SeedCommand
import net.aquamine.server.commands.StopCommand
import net.aquamine.server.commands.SummonCommand
import net.aquamine.server.commands.TeleportCommand
import net.aquamine.server.commands.TitleCommand
import net.aquamine.server.command.registrar.BrigadierCommandRegistrar
import net.aquamine.server.command.registrar.RawCommandRegistrar
import net.aquamine.server.command.registrar.SimpleCommandRegistrar
import net.aquamine.server.commands.aqua.AquaCommand
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.event.player.KryptonPlayerUpdateCommandsEvent
import net.aquamine.server.packet.out.play.PacketOutCommands
import net.aquamine.server.util.downcastApiType
import java.util.concurrent.CompletableFuture
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.annotation.concurrent.GuardedBy
import kotlin.concurrent.read
import kotlin.concurrent.write
import kotlin.math.max
import kotlin.math.min

class KryptonCommandManager : CommandManager {

    @GuardedBy("lock")
    private val dispatcher = CommandDispatcher<Source>() // Reads and writes MUST be locked by this lock!
    private val lock = ReentrantReadWriteLock()

    private val brigadierCommandRegistrar = BrigadierCommandRegistrar(lock.writeLock())
    private val simpleCommandRegistrar = SimpleCommandRegistrar(lock.writeLock())
    private val rawCommandRegistrar = RawCommandRegistrar(lock.writeLock())

    override fun register(command: BrigadierCommand, meta: CommandMeta) {
        brigadierCommandRegistrar.register(dispatcher.root, command, meta)
    }

    override fun register(command: InvocableCommand<*>, meta: CommandMeta) {
        when (command) {
            is SimpleCommand -> simpleCommandRegistrar.register(dispatcher.root, command, meta)
            is RawCommand -> rawCommandRegistrar.register(dispatcher.root, command, meta)
        }
    }

    override fun unregister(alias: String) {
        lock.write { dispatcher.root.removeChildByName(alias.lowercase()) }
    }

    override fun dispatch(sender: Sender, command: String): Boolean =
        dispatch(sender.downcast().createCommandSourceStack(), command, NO_OP_RESULT_CONSUMER)

    fun dispatch(source: Source, command: String): Boolean = dispatch(source, command, NO_OP_RESULT_CONSUMER)

    fun dispatch(source: Source, command: String, resultCallback: ResultConsumer<Source>): Boolean {
        val normalized = normalizeInput(command)
        return try {
            val parseResults = parse(source, normalized)
            dispatcher.execute(parseResults, resultCallback)
            true
        } catch (exception: CommandSyntaxException) {
            // The exception formatting here is mostly based on that of vanilla, so we can actually report all of the useful
            // information that we may want for exception messages thrown by commands.
            val rawMessage = exception.rawMessage
            val message = if (rawMessage is AdventureMessage) rawMessage.asComponent() else Component.text(exception.message.orEmpty())
            source.sendFailure(message.color(NamedTextColor.RED))

            // This will process extra stuff that we want for proper error reporting to clients.
            if (exception.input != null && exception.cursor >= 0) {
                val inputLength = min(exception.input.length, exception.cursor)
                val errorMessage = Component.text().style {
                    it.color(NamedTextColor.GRAY)
                    it.clickEvent(ClickEvent.suggestCommand(command))
                }

                // If the length of the input is too long, we shorten it by appending ... at the beginning.
                if (inputLength > ERROR_MESSAGE_CUTOFF_THRESHOLD) errorMessage.append(Component.text("..."))
                errorMessage.append(Component.text(exception.input.substring(max(0, inputLength - ERROR_MESSAGE_CUTOFF_THRESHOLD), inputLength)))

                if (inputLength < exception.input.length) {
                    errorMessage.append(Component.text(exception.input.substring(inputLength), NamedTextColor.RED, TextDecoration.UNDERLINED))
                }

                // Append the "[HERE]" text (locale-specific) to the end, just like vanilla.
                errorMessage.append(Component.translatable("command.context.here", NamedTextColor.RED, TextDecoration.ITALIC))
                source.sendFailure(Component.text().append(errorMessage).color(NamedTextColor.RED).build())
            }
            false
        } catch (naughty: Throwable) { // We catch Throwable because plugins like to do stupid things sometimes.
            LOGGER.error("Unable to dispatch command $command from $source!", naughty)
            throw RuntimeException("Unable to dispatch command $command from $source!", naughty)
        }
    }

    fun suggest(results: ParseResults<Source>, cursor: Int): CompletableFuture<Suggestions> = dispatcher.getCompletionSuggestions(results, cursor)

    fun suggest(results: ParseResults<Source>): CompletableFuture<Suggestions> = dispatcher.getCompletionSuggestions(results)

    override fun updateCommands(player: Player) {
        if (player !is KryptonPlayer) return
        // We copy the root node to avoid a command changing whilst we're trying to send it to the client.
        val node = RootCommandNode<CommandSourceStack>()
        lock.read {
            dispatcher.root.children.forEach { if (it.requirement.test(player.createCommandSourceStack())) node.addChild(it) }
        }
        player.server.eventNode.fire(KryptonPlayerUpdateCommandsEvent(player, node))
        player.connection.send(PacketOutCommands.createFromRootNode(node))
    }

    fun registerBuiltins() {
        StopCommand.register(dispatcher)
        TeleportCommand.register(dispatcher)
        SummonCommand.register(dispatcher)
        GameModeCommand.register(dispatcher)
        ListCommand.register(dispatcher)
        SeedCommand.register(dispatcher)
        SayCommand.register(dispatcher)
        MeCommand.register(dispatcher)
        MessageCommand.register(dispatcher)
        TitleCommand.register(dispatcher)
        DifficultyCommand.register(dispatcher)
        GameRuleCommand.register(dispatcher)
        KickCommand.register(dispatcher)
        GiveCommand.register(dispatcher)
        ClearCommand.register(dispatcher)
        AquaCommand.register(dispatcher)
    }

    fun parse(sender: Source, input: String): ParseResults<Source> = lock.read { dispatcher.parse(input, sender) }

    fun parse(sender: Source, reader: StringReader): ParseResults<Source> = lock.read { dispatcher.parse(reader, sender) }

    companion object {

        private val LOGGER = LogManager.getLogger()
        private const val ERROR_MESSAGE_CUTOFF_THRESHOLD = 10
        private val NO_OP_RESULT_CONSUMER = ResultConsumer<Source> { _, _, _ ->}

        @JvmStatic
        private fun normalizeInput(input: String): String {
            val command = input.trim()
            val firstSeparator = command.indexOf(CommandDispatcher.ARGUMENT_SEPARATOR_CHAR)
            if (firstSeparator != -1) return command.substring(0, firstSeparator).lowercase() + command.substring(firstSeparator)
            return command.lowercase()
        }
    }
}

private typealias Source = CommandSourceStack

private fun Sender.downcast(): AquaSender = downcastApiType("Sender")

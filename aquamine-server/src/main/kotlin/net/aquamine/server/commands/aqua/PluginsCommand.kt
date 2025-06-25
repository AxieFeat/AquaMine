package net.aquamine.server.commands.aqua

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.commands.runs
import net.aquamine.server.plugin.KryptonPluginContainer
import java.util.stream.Stream

object PluginsCommand : AquaSubCommand {

    private val OPEN_BRACKET = Component.text(" (", NamedTextColor.DARK_GRAY)
    private val CLOSED_BRACKET = Component.text(")", NamedTextColor.DARK_GRAY)

    private val INDENT = Component.text("  - ", NamedTextColor.DARK_GRAY)
    private val PREFIX = Component.text("Plugins", AquaColors.STANDARD_PURPLE, TextDecoration.BOLD)
    private val COLON = Component.text(": ", NamedTextColor.DARK_GRAY)
    private val SEPARATOR = Component.text(", ", AquaColors.DARK_ORCHID)

    private val ID = Component.text("ID: ", AquaColors.DARK_ORCHID)
    private val NAME = Component.text("Name: ", AquaColors.DARK_ORCHID)
    private val VERSION = Component.text("Version: ", AquaColors.DARK_ORCHID)
    private val DESCRIPTION = Component.text("Description: ", AquaColors.DARK_ORCHID)
    private val AUTHORS = Component.text("Authors: ", AquaColors.DARK_ORCHID)
    private val DEPENDENCIES = Component.text("Dependencies: ", AquaColors.DARK_ORCHID)
    private val OPTIONAL = Component.text("optional: ", AquaColors.DARK_ORCHID)

    override fun aliases(): Stream<String> = Stream.of("pl")

    override fun register(): LiteralArgumentBuilder<CommandSourceStack> = literalCommand("plugins") {
        runs { context ->
            val plugins = context.source.server.pluginManager.plugins
            if (plugins.isEmpty()) {
                context.source.sendSystemMessage(Component.text("No plugins are currently running on this server.", NamedTextColor.RED))
                return@runs
            }

            val message = foldToMessage(plugins) { pluginIndex, builder, plugin ->
                // Don't include modules in the plugin list.
                if (plugin is KryptonPluginContainer && plugin.isModule) return@foldToMessage

                val pluginMessage = Component.text()
                    .append(ID)
                    .append(Component.text(plugin.description.id, AquaColors.VIVID_SKY_BLUE))
                    .appendNewline()
                    .append(NAME)
                    .append(Component.text(plugin.description.name, AquaColors.TURQUOISE))
                    .appendNewline()
                    .append(VERSION)
                    .append(Component.text(plugin.description.version, NamedTextColor.GREEN))
                    .appendNewline()
                    .append(DESCRIPTION)
                    .append(Component.text(plugin.description.description, NamedTextColor.GOLD))
                    .appendNewline()
                    .append(AUTHORS)
                    .append(Component.text(plugin.description.authors.joinToString(", "), NamedTextColor.YELLOW))
                    .appendNewline()
                    .append(DEPENDENCIES)

                val dependencies = plugin.description.dependencies
                if (dependencies.isNotEmpty()) {
                    pluginMessage.appendNewline().append(foldToMessage(dependencies) { index, dependencyMessage, dependency ->
                        dependencyMessage.append(INDENT)
                            .append(Component.text(dependency.id, NamedTextColor.GREEN))
                            .append(OPEN_BRACKET)
                            .append(OPTIONAL)
                            .append(Component.text(dependency.isOptional, NamedTextColor.GREEN))
                            .append(CLOSED_BRACKET)
                        if (index < dependencies.size - 1) dependencyMessage.appendNewline()
                    })
                } else {
                    pluginMessage.append(Component.text("None", NamedTextColor.RED))
                }

                builder.append(Component.text()
                    .append(Component.text(plugin.description.id, AquaColors.LIGHTER_PURPLE))
                    .append(OPEN_BRACKET)
                    .append(Component.text(plugin.description.version, NamedTextColor.GREEN))
                    .append(CLOSED_BRACKET)
                    .hoverEvent(HoverEvent.showText(pluginMessage)))
                if (pluginIndex < plugins.size - 1) builder.append(SEPARATOR)
            }

            context.source.sendSystemMessage(Component.text()
                .append(PREFIX)
                .append(OPEN_BRACKET)
                .append(Component.text(plugins.size, NamedTextColor.GREEN))
                .append(CLOSED_BRACKET)
                .append(COLON)
                .append(message)
                .build())
        }
    }

    @JvmStatic
    private inline fun <T> foldToMessage(iterable: Iterable<T>, operation: (Int, ComponentBuilder<*, *>, T) -> Unit): Component =
        iterable.foldIndexed(Component.text()) { index, builder, value ->
            operation(index, builder, value)
            builder
        }.build()
}

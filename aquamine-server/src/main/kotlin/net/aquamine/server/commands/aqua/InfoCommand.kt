package net.aquamine.server.commands.aqua

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.aquamine.api.command.literalCommand
import net.aquamine.server.KryptonPlatform
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.commands.runs
import java.util.stream.Stream

object InfoCommand : AquaSubCommand {

    private val MESSAGE = Component.text()
        .append(Component.text("This server is running ", AquaColors.LIGHTER_PURPLE))
        .append(Component.text()
            .content("Krypton ")
            .color(AquaColors.STANDARD_PURPLE)
            .decorate(TextDecoration.BOLD)
            .clickEvent(ClickEvent.openUrl("https://kryptonmc.org")))
        .append(Component.text(KryptonPlatform.version, NamedTextColor.GREEN))
        .append(Component.text(" for Minecraft ", AquaColors.LIGHTER_PURPLE))
        .append(Component.text(KryptonPlatform.minecraftVersion, NamedTextColor.GREEN))
        .build()

    override fun aliases(): Stream<String> = Stream.of("about", "version")

    override fun register(): LiteralArgumentBuilder<CommandSourceStack> = literalCommand("info") {
        runs { it.source.sendSystemMessage(MESSAGE) }
    }
}

package net.aquamine.server.commands.aqua

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.aquamine.api.command.literalCommand
import net.aquamine.server.AquaPlatform
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.commands.runs
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import java.util.stream.Stream

object InfoCommand : AquaSubCommand {

    private val MESSAGE = Component.text()
        .append(Component.text("This server is running ",NamedTextColor.WHITE))
        .append(Component.text()
            .content("AquaMine ")
            .color(AquaColors.LIGHTER_PURPLE)
            .decorate(TextDecoration.BOLD))
        .append(Component.text(AquaPlatform.version, AquaColors.VIVID_SKY_BLUE))
        .append(Component.text(" for ",NamedTextColor.WHITE))
        .append(Component.text("Minecraft ", AquaColors.LIGHTER_PURPLE))
        .append(Component.text(AquaPlatform.minecraftVersion, AquaColors.VIVID_SKY_BLUE))
        .build()

    override fun aliases(): Stream<String> = Stream.of("about", "version")

    override fun register(): LiteralArgumentBuilder<CommandSourceStack> = literalCommand("info") {
        runs { it.source.sendSystemMessage(MESSAGE) }
    }
}

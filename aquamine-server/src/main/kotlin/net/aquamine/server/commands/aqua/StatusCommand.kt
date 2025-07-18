package net.aquamine.server.commands.aqua

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.aquamine.api.command.literalCommand
import net.aquamine.api.entity.player.Player
import net.aquamine.api.scoreboard.DisplaySlot
import net.aquamine.api.scoreboard.ObjectiveRenderType
import net.aquamine.api.scoreboard.Scoreboard
import net.aquamine.api.scoreboard.criteria.Criteria
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.commands.runs
import net.kyori.adventure.text.Component

object StatusCommand : AquaSubCommand {

    val serverInfo = Component.text("Server status", AquaColors.LIGHTER_PURPLE)

    val board = Scoreboard.create()

    val objective = board.createObjectiveBuilder()
        .name("info")
        .criterion(Criteria.AIR.get())
        .displayName(serverInfo)
        .renderType(ObjectiveRenderType.INTEGER)
        .buildAndRegister().apply {
            this.getOrCreateScore(Component.text("CPU: 1%"))
            this.getOrCreateScore(Component.text("Mem: 100/1024 MB"))
            this.getOrCreateScore(Component.text("TPS: 2000"))
            this.getOrCreateScore(Component.text("MSPT: 1.3"))

            board.updateSlot(this, DisplaySlot.SIDEBAR)
        }

    override fun register(): LiteralArgumentBuilder<CommandSourceStack> = literalCommand("status") {
        runs {
            val source = it.source
            val entity = source.entity

            if(entity !is Player) return@runs

            entity.showScoreboard(
                board
            )
        }
    }
}

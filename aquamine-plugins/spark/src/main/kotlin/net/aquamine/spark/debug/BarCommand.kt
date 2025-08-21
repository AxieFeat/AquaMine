package net.aquamine.spark.debug

import net.aquamine.api.command.Sender
import net.aquamine.api.command.SimpleCommand
import net.aquamine.api.entity.player.Player
import net.aquamine.spark.task.BossBarTask

class BarCommand(
    private val bar: BossBarTask,
) : SimpleCommand {

    override fun execute(sender: Sender, args: Array<String>) {
        if(sender !is Player) return

        // TODO Add permission check and message sending.

        bar.togglePlayer(sender)
    }
}

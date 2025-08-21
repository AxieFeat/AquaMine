package net.aquamine.spark

import me.lucko.spark.common.SparkPlatform
import net.aquamine.api.command.Sender
import net.aquamine.api.command.SimpleCommand

class SparkCommand(
    val platform: SparkPlatform,
) : SimpleCommand {

    override fun execute(sender: Sender, args: Array<String>) {
        this.platform.executeCommand(AquaCommandSender(sender), args)
    }

    override fun suggest(sender: Sender, args: Array<String>): List<String> {
        return this.platform.tabCompleteCommand(AquaCommandSender(sender), args)
    }
}

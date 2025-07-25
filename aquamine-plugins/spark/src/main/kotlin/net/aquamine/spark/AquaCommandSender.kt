package net.aquamine.spark

import me.lucko.spark.common.command.sender.AbstractCommandSender
import net.aquamine.api.command.Sender
import net.aquamine.api.entity.player.Player
import net.kyori.adventure.text.Component
import java.util.UUID

class AquaCommandSender(
    val sender: Sender,
) : AbstractCommandSender<Sender>(sender) {

    override fun getName(): String = sender.name

    override fun getUniqueId(): UUID? = if(sender is Player) sender.uuid else null

    override fun sendMessage(msg: Component) = sender.sendMessage(msg)

    override fun hasPermission(permission: String): Boolean = true

}
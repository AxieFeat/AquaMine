package net.aquamine.server.event.command

import net.aquamine.api.command.Sender
import net.aquamine.api.event.command.CommandExecuteEvent
import net.aquamine.api.event.type.AbstractDeniableEventWithResult

class KryptonCommandExecuteEvent(
    override val sender: Sender,
    override val command: String
) : AbstractDeniableEventWithResult<CommandExecuteEvent.Result>(), CommandExecuteEvent

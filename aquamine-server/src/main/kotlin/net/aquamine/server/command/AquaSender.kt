package net.aquamine.server.command

import net.kyori.adventure.text.Component
import net.aquamine.api.command.Sender

interface AquaSender : Sender {

    fun acceptsSuccess(): Boolean

    fun acceptsFailure(): Boolean

    fun shouldInformAdmins(): Boolean

    fun alwaysAccepts(): Boolean = false

    fun sendSystemMessage(message: Component)

    override fun sendMessage(message: Component) {
        sendSystemMessage(message)
    }

    fun createCommandSourceStack(): CommandSourceStack
}

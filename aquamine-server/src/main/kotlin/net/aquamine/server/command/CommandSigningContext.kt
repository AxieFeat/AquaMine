package net.aquamine.server.command

import net.aquamine.server.network.chat.PlayerChatMessage

fun interface CommandSigningContext {

    fun getArgument(name: String): PlayerChatMessage?

    @JvmRecord
    data class SignedArguments(val arguments: Map<String, PlayerChatMessage>) : CommandSigningContext {

        override fun getArgument(name: String): PlayerChatMessage? = arguments.get(name)
    }

    companion object {

        @JvmField
        val ANONYMOUS: CommandSigningContext = CommandSigningContext { null }
    }
}

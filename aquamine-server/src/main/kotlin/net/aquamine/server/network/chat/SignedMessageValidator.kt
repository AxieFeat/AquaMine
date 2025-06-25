package net.aquamine.server.network.chat

import net.aquamine.server.util.crypto.SignatureValidator

fun interface SignedMessageValidator {

    fun updateAndValidate(message: PlayerChatMessage): Boolean

    class KeyBased(private val validator: SignatureValidator) : SignedMessageValidator {

        private var lastMessage: PlayerChatMessage? = null
        private var isChainValid = true

        private fun validateChain(message: PlayerChatMessage): Boolean =
            message == lastMessage || lastMessage == null || message.link.isDescendantOf(lastMessage!!.link)

        override fun updateAndValidate(message: PlayerChatMessage): Boolean {
            isChainValid = isChainValid && message.verify(validator) && validateChain(message)
            if (!isChainValid) return false
            lastMessage = message
            return true
        }
    }
}

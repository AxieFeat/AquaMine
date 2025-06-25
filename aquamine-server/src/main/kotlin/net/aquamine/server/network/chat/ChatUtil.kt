package net.aquamine.server.network.chat

object ChatUtil {

    private const val SECTION_SIGN = '§'
    private const val DELETE_CHAR = '\u007F'

    @JvmStatic
    fun isValidMessage(message: String): Boolean {
        for (i in message.indices) {
            if (!isValidCharacter(message[i])) return false
        }
        return true
    }

    @JvmStatic
    fun isValidCharacter(char: Char): Boolean = char != SECTION_SIGN && char >= ' ' && char != DELETE_CHAR
}

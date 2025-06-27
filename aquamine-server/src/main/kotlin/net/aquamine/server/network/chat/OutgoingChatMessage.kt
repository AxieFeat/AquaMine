package net.aquamine.server.network.chat

import net.kyori.adventure.text.Component
import net.aquamine.server.entity.player.AquaPlayer

sealed interface OutgoingChatMessage {

    fun content(): Component

    fun sendToPlayer(player: AquaPlayer, filter: Boolean, type: RichChatType.Bound)

    @JvmRecord
    data class Disguised(private val content: Component) : OutgoingChatMessage {

        override fun content(): Component = content

        override fun sendToPlayer(player: AquaPlayer, filter: Boolean, type: RichChatType.Bound) {
            player.chatTracker.sendDisguisedChatMessage(content, type)
        }
    }

    @JvmRecord
    data class Player(val message: PlayerChatMessage) : OutgoingChatMessage {

        override fun content(): Component = message.decoratedContent()

        override fun sendToPlayer(player: AquaPlayer, filter: Boolean, type: RichChatType.Bound) {
            val filtered = message.filter(filter)
            if (!filtered.isFullyFiltered()) player.chatTracker.sendPlayerChatMessage(filtered, type)
        }
    }

    companion object {

        @JvmStatic
        fun create(message: PlayerChatMessage): OutgoingChatMessage =
            if (message.isSystem()) Disguised(message.decoratedContent()) else Player(message)
    }
}

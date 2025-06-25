package net.aquamine.server

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.chat.ChatType
import net.kyori.adventure.chat.SignedMessage
import net.kyori.adventure.text.Component
import net.aquamine.api.Server
import net.aquamine.server.adventure.PacketGroupingAudience
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.server.PlayerManager
import java.util.Collections

interface ServerAudience : Server, PacketGroupingAudience {

    val playerManager: PlayerManager

    override val players: Collection<KryptonPlayer>
        get() = players()

    override fun players(): Collection<KryptonPlayer> = Collections.unmodifiableCollection(playerManager.players())

    override fun audiences(): Iterable<Audience> = players().asSequence().plus(console).asIterable()

    override fun sendMessage(message: Component) {
        super<PacketGroupingAudience>.sendMessage(message)
        console.sendMessage(message)
    }

    override fun sendMessage(message: Component, boundChatType: ChatType.Bound) {
        super<PacketGroupingAudience>.sendMessage(message, boundChatType)
        console.sendMessage(message, boundChatType)
    }

    override fun sendMessage(signedMessage: SignedMessage, boundChatType: ChatType.Bound) {
        super<PacketGroupingAudience>.sendMessage(signedMessage, boundChatType)
        console.sendMessage(signedMessage, boundChatType)
    }

    override fun deleteMessage(signature: SignedMessage.Signature) {
        super<PacketGroupingAudience>.deleteMessage(signature)
        console.deleteMessage(signature)
    }
}

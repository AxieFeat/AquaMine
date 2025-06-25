package net.aquamine.server.network.chat

import net.kyori.adventure.key.Key
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.registry.KryptonRegistry
import net.aquamine.server.resource.KryptonResourceKey
import net.aquamine.server.resource.KryptonResourceKeys

object ChatTypes {

    @JvmField
    val CHAT: ResourceKey<RichChatType> = create("chat")
    @JvmField
    val SAY_COMMAND: ResourceKey<RichChatType> = create("say_command")
    @JvmField
    val MESSAGE_COMMAND_INCOMING: ResourceKey<RichChatType> = create("msg_command_incoming")
    @JvmField
    val MESSAGE_COMMAND_OUTGOING: ResourceKey<RichChatType> = create("msg_command_outgoing")
    @JvmField
    val TEAM_MESSAGE_COMMAND_INCOMING: ResourceKey<RichChatType> = create("team_msg_command_incoming")
    @JvmField
    val TEAM_MESSAGE_COMMAND_OUTGOING: ResourceKey<RichChatType> = create("team_msg_command_outgoing")
    @JvmField
    val EMOTE_COMMAND: ResourceKey<RichChatType> = create("emote_command")

    @JvmStatic
    private fun create(key: String): ResourceKey<RichChatType> = KryptonResourceKey.of(KryptonResourceKeys.CHAT_TYPE, Key.key(key))

    @JvmStatic
    fun bootstrap(registry: KryptonRegistry<RichChatType>) {
        val defaultDecoration = ChatTypeDecoration.withSender("chat.type.text")
        val defaultNarration = ChatTypeDecoration.withSender("chat.type.text.narrate")
        KryptonRegistries.register(registry, CHAT, RichChatType(defaultDecoration, defaultNarration))
        KryptonRegistries.register(registry, SAY_COMMAND, RichChatType(ChatTypeDecoration.withSender("chat.type.announcement"), defaultNarration))
        KryptonRegistries.register(registry, MESSAGE_COMMAND_INCOMING,
            RichChatType(ChatTypeDecoration.incomingDirectMessage("commands.message.display.incoming"), defaultNarration))
        KryptonRegistries.register(registry, MESSAGE_COMMAND_OUTGOING,
            RichChatType(ChatTypeDecoration.outgoingDirectMessage("commands.message.display.outgoing"), defaultNarration))
        KryptonRegistries.register(registry, TEAM_MESSAGE_COMMAND_INCOMING,
            RichChatType(ChatTypeDecoration.teamMessage("chat.type.team.text"), defaultNarration))
        KryptonRegistries.register(registry, TEAM_MESSAGE_COMMAND_OUTGOING,
            RichChatType(ChatTypeDecoration.teamMessage("chat.type.team.sent"), defaultNarration))
        KryptonRegistries.register(registry, EMOTE_COMMAND,
            RichChatType(ChatTypeDecoration.withSender("chat.type.emote"), ChatTypeDecoration.withSender("chat.type.emote")))
    }
}

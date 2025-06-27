package net.aquamine.server.network.chat

import net.kyori.adventure.key.Key
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.registry.AquaRegistry
import net.aquamine.server.resource.AquaResourceKey
import net.aquamine.server.resource.AquaResourceKeys

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
    private fun create(key: String): ResourceKey<RichChatType> = AquaResourceKey.of(AquaResourceKeys.CHAT_TYPE, Key.key(key))

    @JvmStatic
    fun bootstrap(registry: AquaRegistry<RichChatType>) {
        val defaultDecoration = ChatTypeDecoration.withSender("chat.type.text")
        val defaultNarration = ChatTypeDecoration.withSender("chat.type.text.narrate")
        AquaRegistries.register(registry, CHAT, RichChatType(defaultDecoration, defaultNarration))
        AquaRegistries.register(registry, SAY_COMMAND, RichChatType(ChatTypeDecoration.withSender("chat.type.announcement"), defaultNarration))
        AquaRegistries.register(registry, MESSAGE_COMMAND_INCOMING,
            RichChatType(ChatTypeDecoration.incomingDirectMessage("commands.message.display.incoming"), defaultNarration))
        AquaRegistries.register(registry, MESSAGE_COMMAND_OUTGOING,
            RichChatType(ChatTypeDecoration.outgoingDirectMessage("commands.message.display.outgoing"), defaultNarration))
        AquaRegistries.register(registry, TEAM_MESSAGE_COMMAND_INCOMING,
            RichChatType(ChatTypeDecoration.teamMessage("chat.type.team.text"), defaultNarration))
        AquaRegistries.register(registry, TEAM_MESSAGE_COMMAND_OUTGOING,
            RichChatType(ChatTypeDecoration.teamMessage("chat.type.team.sent"), defaultNarration))
        AquaRegistries.register(registry, EMOTE_COMMAND,
            RichChatType(ChatTypeDecoration.withSender("chat.type.emote"), ChatTypeDecoration.withSender("chat.type.emote")))
    }
}

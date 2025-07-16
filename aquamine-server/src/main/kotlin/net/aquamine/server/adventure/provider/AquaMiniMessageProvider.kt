package net.aquamine.server.adventure.provider

import net.kyori.adventure.text.minimessage.MiniMessage
import java.util.function.Consumer

@Suppress("UnstableApiUsage")
class AquaMiniMessageProvider : MiniMessage.Provider {

    override fun miniMessage(): MiniMessage = MiniMessage.builder().build()

    override fun builder(): Consumer<MiniMessage.Builder> = Consumer {}
}

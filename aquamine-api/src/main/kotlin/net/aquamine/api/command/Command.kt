package net.aquamine.api.command

/**
 * A command that can be sent by a [Sender], such as a
 * [player][net.aquamine.api.entity.player.Player] or the [ConsoleSender].
 *
 * There are three built-in types that inherit from this base interface:
 *
 * * [BrigadierCommand] - the more modern type of command, backed by
 * Brigadier's [com.mojang.brigadier.tree.LiteralCommandNode]
 * * [SimpleCommand] - the older style of command, mainly popularized by Bukkit
 * and BungeeCord.
 * * [RawCommand] - provides everything as-is, so it can be processed by
 * external frameworks.
 */
sealed interface Command

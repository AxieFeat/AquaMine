package net.aquamine.server.command.arguments

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType
import net.kyori.adventure.text.Component
import net.aquamine.server.adventure.AquaAdventure

/**
 * Used as an easy way to create command exception types that return a translatable message from their arguments.
 */
object CommandExceptions {

    @JvmStatic
    fun simple(key: String): SimpleCommandExceptionType = SimpleCommandExceptionType(AquaAdventure.asMessage(Component.translatable(key)))

    @JvmStatic
    fun dynamic(key: String): DynamicCommandExceptionType =
        DynamicCommandExceptionType { AquaAdventure.asMessage(Component.translatable(key, Component.text(it.toString()))) }

    @JvmStatic
    fun dynamic2(key: String): Dynamic2CommandExceptionType = Dynamic2CommandExceptionType { a, b ->
        AquaAdventure.asMessage(Component.translatable(key, Component.text(a.toString()), Component.text(b.toString())))
    }

    @JvmStatic
    fun resetAndThrow(reader: StringReader, position: Int, exception: CommandSyntaxException): Nothing {
        reader.cursor = position
        throw exception
    }
}

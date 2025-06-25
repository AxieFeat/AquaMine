package net.aquamine.server.command.registrar

import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.ParsedArgument
import net.aquamine.api.command.RawCommand
import java.util.concurrent.locks.Lock

/**
 * Registers raw commands to a root node. This is relatively simple, as raw
 * commands do not do any extra processing.
 */
class RawCommandRegistrar(lock: Lock) : InvocableCommandRegistrar<RawCommand, String>(lock, StringArgumentType.greedyString()) {

    override fun getArgs(arguments: Map<String, ParsedArgument<*, *>>): String = readArguments(arguments, "")
}

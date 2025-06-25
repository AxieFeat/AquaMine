package net.aquamine.server.command.registrar

import com.mojang.brigadier.context.ParsedArgument
import net.aquamine.api.command.SimpleCommand
import net.aquamine.server.command.brigadier.StringArrayArgumentType
import java.util.concurrent.locks.Lock

/**
 * Registers simple commands to a root node. This performs permission checks on
 * execute and suggest.
 */
class SimpleCommandRegistrar(lock: Lock) : InvocableCommandRegistrar<SimpleCommand, Array<String>>(lock, StringArrayArgumentType) {

    override fun getArgs(arguments: Map<String, ParsedArgument<*, *>>): Array<String> = readArguments(arguments, StringArrayArgumentType.EMPTY)
}

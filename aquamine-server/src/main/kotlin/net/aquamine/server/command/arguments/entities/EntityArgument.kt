package net.aquamine.server.command.arguments.entities

@JvmRecord
data class EntityArgument(val name: String, val value: Any, val exclude: Boolean)

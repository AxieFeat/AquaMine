package net.aquamine.api.command

/**
 * The sender for the [net.aquamine.api.Server]. This is the sender
 * used by the console.
 *
 * This purely exists so it can be `is` checked if necessary, to determine
 * whether a given [Sender] is the console or not.
 */
interface ConsoleSender : Sender

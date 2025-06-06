package net.aquamine.api.command

/**
 * A command that passes its arguments as a single string without processing
 * the input.
 *
 * This is useful for attaching external command frameworks to AquaMine and
 * allowing them to do their own processing.
 */
fun interface RawCommand : InvocableCommand<String>

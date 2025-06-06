package net.aquamine.api.command

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.kyori.adventure.builder.AbstractBuilder
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine

/**
 * Holds metadata for a [Command].
 */
@ImmutableType
interface CommandMeta {

    /**
     * The name of the command.
     */
    val name: String

    /**
     * All the aliases of the command.
     */
    val aliases: Set<String>

    /**
     * A builder for [CommandMeta].
     */
    interface Builder : AbstractBuilder<CommandMeta> {

        /**
         * Sets the name of the command to the given [name].
         *
         * @param name The name.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun name(name: String): Builder

        /**
         * Adds the given [alias] to the list of aliases.
         *
         * @param alias The alias.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun alias(alias: String): Builder

        /**
         * Adds the given [aliases] to the list of aliases.
         *
         * @param aliases The aliases.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun aliases(aliases: Collection<String>): Builder
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun builder(name: String): Builder
    }

    companion object {

        /**
         * Creates a new builder for constructing command metadata.
         *
         * @param name The name of the command.
         *
         * @return A new builder.
         */
        @JvmStatic
        @Contract("_ -> new", pure = true)
        fun builder(name: String): Builder = AquaMine.factory<Factory>().builder(name)
    }
}

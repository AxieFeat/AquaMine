package net.aquamine.server.command

import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf
import net.aquamine.api.command.CommandMeta

@JvmRecord
data class AquaCommandMeta(override val name: String, override val aliases: ImmutableSet<String>) : CommandMeta {

    class Builder(private var name: String) : CommandMeta.Builder {

        private val aliases = persistentSetOf<String>().builder()

        override fun name(name: String): Builder = apply { this.name = name }

        override fun alias(alias: String): Builder = apply { aliases.add(alias) }

        override fun aliases(aliases: Collection<String>): Builder = apply { this.aliases.addAll(aliases) }

        override fun build(): CommandMeta = AquaCommandMeta(name, aliases.build())
    }

    object Factory : CommandMeta.Factory {

        override fun builder(name: String): CommandMeta.Builder = Builder(name)
    }
}

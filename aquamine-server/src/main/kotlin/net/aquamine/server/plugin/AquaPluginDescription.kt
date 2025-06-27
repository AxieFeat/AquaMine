package net.aquamine.server.plugin

import net.aquamine.api.plugin.PluginDependency
import net.aquamine.api.plugin.PluginDescription
import net.aquamine.server.AquaPlatform
import java.nio.file.Path

open class AquaPluginDescription(
    final override val id: String,
    final override val name: String,
    final override val version: String,
    final override val description: String,
    final override val authors: Collection<String>,
    final override val dependencies: Collection<PluginDependency>,
    final override val source: Path?
) : PluginDescription {

    final override fun getDependency(id: String): PluginDependency? = dependencies.firstOrNull { it.id == id }

    companion object {

        private const val DESCRIPTION = "A plugin representing the server. Used for internal things, such as service implementations."
        @JvmField
        val SERVER: AquaPluginDescription =
            AquaPluginDescription("aquamine", "AquaMine", AquaPlatform.version, DESCRIPTION, setOf("AquaMine"), emptySet(), Path.of(""))
    }
}

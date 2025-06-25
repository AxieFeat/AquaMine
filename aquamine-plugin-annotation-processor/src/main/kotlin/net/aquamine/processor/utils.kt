package net.aquamine.processor

import net.aquamine.api.plugin.annotation.Plugin

fun Plugin.toDescription(qualifiedName: String): SerializedPluginDescription {
    val dependencies = dependencies.map { SerializedDependency(it.id, it.optional) }
    return SerializedPluginDescription(id, name, version, description, authors.toList(), dependencies, qualifiedName)
}

package net.aquamine.server.resource

import net.kyori.adventure.key.Key
import net.aquamine.server.pack.resources.Resource
import net.aquamine.server.pack.resources.ResourceManager

class FileToIdConverter(private val prefix: String, private val extension: String) {

    fun idToFile(key: Key): Key = Key.key(key.namespace(), "$prefix/${key.value()}$extension")

    fun fileToId(key: Key): Key {
        val value = key.value()
        return Key.key(value.substring(prefix.length + 1, value.length - extension.length))
    }

    fun listMatchingResources(resourceManager: ResourceManager): Map<Key, Resource> =
        resourceManager.listResources(prefix) { it.value().endsWith(extension) }

    fun listMatchingResourceStacks(resourceManager: ResourceManager): Map<Key, List<Resource>> =
        resourceManager.listResourceStacks(prefix) { it.value().endsWith(extension) }

    companion object {

        @JvmStatic
        fun json(prefix: String): FileToIdConverter = FileToIdConverter(prefix, ".json")
    }
}

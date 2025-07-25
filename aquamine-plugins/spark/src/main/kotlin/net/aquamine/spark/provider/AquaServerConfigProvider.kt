package net.aquamine.spark.provider

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import me.lucko.spark.common.platform.serverconfig.ConfigParser
import me.lucko.spark.common.platform.serverconfig.ExcludedConfigFilter
import me.lucko.spark.common.platform.serverconfig.ServerConfigProvider
import org.spongepowered.configurate.ConfigurationNode
import org.spongepowered.configurate.hocon.HoconConfigurationLoader
import java.io.BufferedReader
import java.nio.file.Paths

class AquaServerConfigProvider : ServerConfigProvider(FILES, HIDDEN_PATHS) {

    object HoconConfigParser : ConfigParser {

        @JvmField
        val HOCON: HoconConfigurationLoader.Builder = HoconConfigurationLoader.builder()

        override fun load(
            file: String,
            filter: ExcludedConfigFilter,
        ): JsonElement {
            val element = JsonObject()

            val node = HOCON.path(Paths.get(file)).build().load()

            processNode(element, "", node)

            return filter.apply(element)
        }

        private fun processNode(
            parent: JsonObject,
            currentPath: String,
            node: ConfigurationNode
        ) {
            when {
                node.isMap -> {
                    node.childrenMap().forEach { (key, childNode) ->
                        val newPath = if (currentPath.isEmpty()) key.toString() else "$currentPath.$key"

                        when {
                            childNode.isMap -> {
                                val newObject = JsonObject()
                                processNode(newObject, newPath, childNode)
                                if (newObject.size() > 0) {
                                    parent.add(key.toString(), newObject)
                                }
                            }
                            childNode.isList -> {
                                val array = childNode.childrenList().mapNotNull { it.toJsonPrimitive() }
                                if (array.isNotEmpty()) {
                                    parent.add(key.toString(), array.toJsonArray())
                                }
                            }
                            else -> {
                                childNode.toJsonPrimitive()?.let {
                                    parent.add(key.toString(), it)
                                }
                            }
                        }
                    }
                }
                node.isList -> {
                    val array = node.childrenList().mapNotNull { it.toJsonPrimitive() }
                    if (array.isNotEmpty()) {
                        parent.add("value", array.toJsonArray())
                    }
                }
                else -> {
                    node.toJsonPrimitive()?.let {
                        parent.add("value", it)
                    }
                }
            }
        }

        private fun ConfigurationNode.toJsonPrimitive(): JsonPrimitive? {
            val value = this.raw() ?: return null
            return when (value) {
                is String -> JsonPrimitive(value)
                is Number -> JsonPrimitive(value)
                is Boolean -> JsonPrimitive(value)
                else -> JsonPrimitive(value.toString())
            }
        }

        private fun List<JsonPrimitive>.toJsonArray() = this.fold(com.google.gson.JsonArray()) { arr, el ->
            arr.apply { add(el) }
        }

        override fun parse(reader: BufferedReader): Map<String, Any> {
            val node = HOCON.source { reader }.build().load()
            return flattenNode(node)
        }

        private fun flattenNode(node: ConfigurationNode, path: String = ""): Map<String, Any> {
            val result = mutableMapOf<String, Any>()

            if (node.isMap) {
                for ((key, childNode) in node.childrenMap()) {
                    val newPath = if (path.isEmpty()) key.toString() else "$path.$key"
                    result.putAll(flattenNode(childNode, newPath))
                }
            } else if (node.isList) {
                result[path] = node.childrenList().map { it.raw() ?: "" }
            } else {
                node.raw()?.let {
                    result[path] = it
                }
            }

            return result
        }
    }

    companion object {
        @JvmField
        val FILES = mapOf<String, ConfigParser>(
            "config.conf" to HoconConfigParser
        )

        @JvmField
        val HIDDEN_PATHS = setOf<String>(
            "proxy.secret",
            "server.ip",
            "server.port",
            "server.resource-pack.hash",
            "server.resource-pack.uri",
            "status.motd",
            "world.generator",
            "world.name",
            "world.spawn-protection-message"
        )
    }

}
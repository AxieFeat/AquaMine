package net.aquamine.spark

import me.lucko.spark.common.sampler.source.ClassSourceLookup.ByClassLoader
import net.aquamine.api.Server
import net.aquamine.api.plugin.PluginClassLoader

class AquaClassSourceLookup(
    server: Server,
) : ByClassLoader() {

    private val classLoaderToPlugins: MutableMap<ClassLoader, String> = mutableMapOf()

    init {
        server.pluginManager.plugins.forEach {
            it.instance?.javaClass?.classLoader?.let { classLoader ->
                classLoaderToPlugins[classLoader] = it.description.id
            }
        }
    }

    override fun identify(loader: ClassLoader): String? {
        if (loader is PluginClassLoader) {
            return this.classLoaderToPlugins[loader]
        }
        return null
    }
}

package net.aquamine.server.plugin.server

import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import java.net.URI

class ClasspathModuleDiscoverer(private val reflections: Reflections, private val resourceLoader: ClassLoader) : ModuleDiscoverer {

    override fun discover(): Collection<URI> {
        val files = reflections.getResources("(.*).json")
        val result = ArrayList<URI>()
        files.forEach {
            val url = resourceLoader.getResource(it) ?: error("Failed to resolve resource $it found by reflections! This is a bug!")
            result.add(url.toURI())
        }
        return result
    }

    companion object {

        @JvmStatic
        fun createDefault(): ClasspathModuleDiscoverer {
            val config = ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath())
                .setScanners(Scanners.Resources)
                .filterInputsBy { it.contains("aquamine-modules") }
            val reflections = Reflections(config)
            val resourceLoader = Thread.currentThread().contextClassLoader
            return ClasspathModuleDiscoverer(reflections, resourceLoader)
        }
    }
}

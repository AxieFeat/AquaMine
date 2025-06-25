package net.aquamine.server.plugin

import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Path
import java.util.concurrent.CopyOnWriteArraySet

/**
 * The class loader used to load plugins. This is to allow shared resources across
 * multiple loaded plugins.
 */
class PluginClassLoader(vararg urls: URL) : URLClassLoader(urls) {

    constructor(path: Path) : this(path.toUri().toURL())

    fun addToLoaders(): PluginClassLoader = apply { loaders.add(this) }

    fun addPath(path: Path) {
        addURL(path.toUri().toURL())
    }

    override fun close() {
        loaders.remove(this)
        super.close()
    }

    override fun loadClass(name: String, resolve: Boolean): Class<*> = loadClass0(name, resolve, true)

    private fun loadClass0(name: String, resolve: Boolean, checkOther: Boolean): Class<*> {
        try {
            return super.loadClass(name, resolve)
        } catch (_: ClassNotFoundException) {
            // ignored - we'll try others
        }

        if (!checkOther) throw ClassNotFoundException(name)
        loaders.forEach {
            if (it == this) return@forEach
            try {
                return it.loadClass0(name, resolve, false)
            } catch (_: ClassNotFoundException) {
                // oh well, we'll try everyone else first
            }
        }
        throw ClassNotFoundException(name)
    }

    companion object {

        private val loaders = CopyOnWriteArraySet<PluginClassLoader>()

        init {
            registerAsParallelCapable()
        }
    }
}

package net.aquamine.server.util

import com.google.gson.Gson
import com.google.gson.JsonObject
import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import net.aquamine.server.KryptonPlatform
import net.aquamine.server.registry.KryptonRegistry
import net.aquamine.server.registry.KryptonRegistries

abstract class KryptonDataLoader<T>(fileSuffix: String, protected val registry: KryptonRegistry<T>) {

    private val fileName = "${KryptonPlatform.dataVersionPrefix}_$fileSuffix.json"
    @Volatile
    private var isLoaded = false

    protected abstract fun create(key: Key, value: JsonObject): T

    protected open fun register(key: Key, value: T) {
        KryptonRegistries.register(registry, key, value)
    }

    protected open fun preload() {
        // empty by default
    }

    fun init() {
        if (isLoaded) {
            LOGGER.warn("Attempted to load data twice!")
            return
        }

        val inputStream = checkNotNull(ClassLoader.getSystemResourceAsStream(fileName)) {
            "Could not find $fileName bundled in JAR! Please report to Krypton!"
        }
        preload()
        GSON.fromJson(inputStream.reader(), JsonObject::class.java).entrySet().forEach { (entryKey, value) ->
            val key = Key.key(entryKey)
            if (!registry.containsKey(key)) register(key, create(key, value as JsonObject))
        }
        isLoaded = true
    }

    companion object {

        protected val GSON = Gson()
        private val LOGGER = LogManager.getLogger()
    }
}

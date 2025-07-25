package net.aquamine.server.registry

import com.google.common.collect.Collections2
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.DefaultedRegistry
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.api.world.biome.Biome
import net.aquamine.api.world.dimension.DimensionType
import net.aquamine.server.network.chat.ChatTypes
import net.aquamine.server.network.chat.RichChatType
import net.aquamine.server.resource.AquaResourceKeys
import net.aquamine.server.world.biome.AquaBiomeRegistrar
import net.aquamine.server.world.dimension.AquaDimensionTypes

object AquaDynamicRegistries {

    private val LOADERS = LinkedHashMap<Key, Runnable>()
    private val WRITABLE_PARENT: WritableRegistry<WritableRegistry<*>> = AquaSimpleRegistry.standard(AquaResourceKeys.PARENT)
    @JvmField
    val PARENT: AquaRegistry<out AquaRegistry<*>> = WRITABLE_PARENT

    @JvmField
    val DIMENSION_TYPE: AquaRegistry<DimensionType> = register(ResourceKeys.DIMENSION_TYPE) { AquaDimensionTypes }
    @JvmField
    val BIOME: AquaRegistry<Biome> = register(ResourceKeys.BIOME) { AquaBiomeRegistrar.bootstrap() }
    @JvmField
    val CHAT_TYPE: AquaRegistry<RichChatType> = register(AquaResourceKeys.CHAT_TYPE, ChatTypes::bootstrap)

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    private fun <T> register(key: ResourceKey<out Registry<T>>, loader: Bootstrap<T>): AquaRegistry<T> {
        val registry = AquaSimpleRegistry.standard(key)
        LOADERS.put(key.location) { loader.run(registry) }
        WRITABLE_PARENT.register(key as ResourceKey<WritableRegistry<*>>, registry)
        return registry
    }

    @JvmStatic
    fun bootstrap() {
        WRITABLE_PARENT.freeze()
        runLoaders()
        WRITABLE_PARENT.forEach { it.freeze() }
        AquaRegistries.validateAll(WRITABLE_PARENT)
    }

    @JvmStatic
    private fun runLoaders() {
        LOADERS.forEach { (key, action) ->
            try {
                action.run()
            } catch (exception: Exception) {
                throw RegistryInitializationException("Failed to bootstrap registry $key!", exception)
            }
            requireNotNull(WRITABLE_PARENT.get(key)) { "Cannot find registry for key $key in loading!" }.freeze()
        }
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun <T> getRegistry(key: ResourceKey<out Registry<T>>): Registry<T>? {
        return WRITABLE_PARENT.get(key as ResourceKey<WritableRegistry<*>>) as? Registry<T>
    }

    private fun interface Bootstrap<T> {

        fun run(registry: AquaRegistry<T>)
    }

    private class RegistryInitializationException(message: String, cause: Throwable) : RuntimeException(message, cause)

    object DynamicHolder : RegistryHolder {

        override val registries: Collection<Registry<*>>
            get() = Collections2.transform(PARENT.holders()) { it.value() }

        override fun <E> getRegistry(key: ResourceKey<out Registry<E>>): Registry<E>? = AquaDynamicRegistries.getRegistry(key)

        @Suppress("UNCHECKED_CAST")
        override fun <E> getDefaultedRegistry(key: ResourceKey<out Registry<E>>): DefaultedRegistry<E>? {
            return WRITABLE_PARENT.get(key as ResourceKey<WritableRegistry<*>>) as? DefaultedRegistry<E>
        }
    }
}

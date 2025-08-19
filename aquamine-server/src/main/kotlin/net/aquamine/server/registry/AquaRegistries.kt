package net.aquamine.server.registry

import com.google.common.collect.Collections2
import net.aquamine.api.block.entity.BlockEntityType
import net.aquamine.api.block.entity.banner.BannerPatternType
import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.entity.EntityCategory
import net.aquamine.api.entity.attribute.AttributeType
import net.aquamine.api.entity.hanging.PaintingVariant
import net.aquamine.api.inventory.InventoryType
import net.aquamine.api.potion.PotionType
import net.aquamine.api.registry.DefaultedRegistry
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.api.scoreboard.criteria.KeyedCriterion
import net.aquamine.api.statistic.StatisticType
import net.aquamine.api.world.damage.type.DamageType
import net.aquamine.api.world.gameevent.GameEvent
import net.aquamine.server.effect.sound.SoundLoader
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.ai.memory.MemoryKey
import net.aquamine.server.entity.ai.memory.MemoryKeys
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.item.AquaItemType
import net.aquamine.server.item.ItemLoader
import net.aquamine.server.item.data.Instrument
import net.aquamine.server.item.data.Instruments
import net.aquamine.server.potion.AquaPotionType
import net.aquamine.server.registry.loader.RegistryLoader
import net.aquamine.server.registry.loader.RegistryLoaders
import net.aquamine.server.resource.AquaResourceKey
import net.aquamine.server.resource.AquaResourceKeys
import net.aquamine.server.util.AquaDataLoader
import net.aquamine.server.util.provider.IntProviderType
import net.aquamine.server.util.provider.IntProviderTypes
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.BlockLoader
import net.aquamine.server.world.fluid.AquaFluid
import net.aquamine.server.world.fluid.AquaFluids
import net.aquamine.server.world.gameevent.GameEventLoader
import net.kyori.adventure.key.Key
import org.apache.logging.log4j.LogManager
import java.util.function.Supplier

/**
 * This class contains all of the built-in registries for AquaMine. These are required by the API to exist, and they exist in this class
 * because it is easier to not have to downcast the registries available in the API everywhere.
 */
object AquaRegistries {

    private val LOGGER = LogManager.getLogger()
    private val LOADERS = LinkedHashMap<Key, Runnable>()
    private val WRITABLE_PARENT: WritableRegistry<WritableRegistry<*>> = AquaSimpleRegistry.standard(AquaResourceKeys.PARENT)
    @JvmField
    val PARENT: AquaRegistry<out AquaRegistry<*>> = WRITABLE_PARENT

    /*
     * Built-in vanilla-derived registries
     */

    @JvmField
    val GAME_EVENT: AquaRegistry<GameEvent> = simple(ResourceKeys.GAME_EVENT, dataLoader(::GameEventLoader))
    @JvmField
    val SOUND_EVENT: AquaRegistry<SoundEvent> = simple(ResourceKeys.SOUND_EVENT, dataLoader(::SoundLoader))
    @JvmField
    val FLUID: AquaDefaultedRegistry<AquaFluid> = defaultedIntrusive(AquaResourceKeys.FLUID, "empty") { AquaFluids }
    @JvmField
    val BLOCK: AquaDefaultedRegistry<AquaBlock> = defaultedIntrusive(AquaResourceKeys.BLOCK, "air", dataLoader(::BlockLoader))
    @JvmField
    val ENTITY_CATEGORIES: AquaRegistry<EntityCategory> = simple(ResourceKeys.ENTITY_CATEGORIES, loader(RegistryLoaders.entityCategory()))
    @JvmField
    val ENTITY_TYPE: AquaDefaultedRegistry<AquaEntityType<*>> =
        defaultedIntrusive(AquaResourceKeys.ENTITY_TYPE, "pig") { AquaEntityTypes }
    @JvmField
    val ITEM: AquaDefaultedRegistry<AquaItemType> = defaultedIntrusive(AquaResourceKeys.ITEM, "air", dataLoader(::ItemLoader))
    @JvmField
    val PARTICLE_TYPE: AquaRegistry<ParticleType> = simple(ResourceKeys.PARTICLE_TYPE, loader(RegistryLoaders.particleType()))
    @JvmField
    val BLOCK_ENTITY_TYPE: AquaRegistry<BlockEntityType<*>> = simple(ResourceKeys.BLOCK_ENTITY_TYPE, loader(RegistryLoaders.blockEntityType()))
    @JvmField
    val PAINTING_VARIANT: AquaDefaultedRegistry<PaintingVariant> =
        defaulted(ResourceKeys.PAINTING_VARIANT, "kebab", loader(RegistryLoaders.paintingVariant()))
    @JvmField
    val STATISTIC_TYPE: AquaRegistry<StatisticType<*>> = simple(ResourceKeys.STATISTIC_TYPE, loader(RegistryLoaders.statisticType()))
    @JvmField
    val CUSTOM_STATISTIC: AquaRegistry<Key> = simple(ResourceKeys.CUSTOM_STATISTIC, loader(RegistryLoaders.customStatistic()))
    @JvmField
    val INVENTORY_TYPE: AquaRegistry<InventoryType> =
        simple(ResourceKeys.INVENTORY_TYPE, loader(RegistryLoaders.inventoryType()))
    @JvmField
    val ATTRIBUTE: AquaRegistry<AttributeType> = simple(ResourceKeys.ATTRIBUTE) { AquaAttributeTypes }
    @JvmField
    val MEMORY_KEY: AquaRegistry<MemoryKey<*>> = simple(AquaResourceKeys.MEMORIES) { MemoryKeys }
    @JvmField
    val INT_PROVIDER_TYPE: AquaRegistry<IntProviderType<*>> = simple(AquaResourceKeys.INT_PROVIDER_TYPES) { IntProviderTypes }
    @JvmField
    val BANNER_PATTERN: AquaRegistry<BannerPatternType> = simple(ResourceKeys.BANNER_PATTERN, loader(RegistryLoaders.bannerPatternType()))
    @JvmField
    val INSTRUMENT: AquaRegistry<Instrument> = simple(AquaResourceKeys.INSTRUMENTS) { Instruments }
    @JvmField
    val POTION_TYPE: AquaRegistry<AquaPotionType> = simple(AquaResourceKeys.POTION_TYPE, loader(RegistryLoaders.potionType()))

    /*
     * Custom built-in registries
     */

    @JvmField
    val CRITERIA: AquaRegistry<KeyedCriterion> = simple(ResourceKeys.CRITERIA, loader(RegistryLoaders.criterion()))
    @JvmField
    val DAMAGE_TYPES: AquaRegistry<DamageType> = simple(ResourceKeys.DAMAGE_TYPES, loader(RegistryLoaders.damageType()))

    /*
     * Registry constructor functions
     */

    @JvmStatic
    private fun <T> simple(key: ResourceKey<out Registry<T>>, bootstrap: Bootstrap<T>): AquaRegistry<T> =
        internalRegister(key, AquaSimpleRegistry.standard(key), bootstrap)

    @JvmStatic
    private fun <T> defaulted(key: ResourceKey<out Registry<T>>, defaultName: String, bootstrap: Bootstrap<T>): AquaDefaultedRegistry<T> =
        internalRegister(key, AquaDefaultedSimpleRegistry.standard(key, Key.key(defaultName)), bootstrap)

    @JvmStatic
    private fun <T> defaultedIntrusive(key: ResourceKey<out Registry<T>>, defaultName: String,
                                       bootstrap: Bootstrap<T>): AquaDefaultedRegistry<T> {
        return internalRegister(key, AquaDefaultedSimpleRegistry.intrusive(key, Key.key(defaultName)), bootstrap)
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    private fun <T, R : WritableRegistry<T>> internalRegister(key: ResourceKey<out Registry<T>>, registry: R, loader: Bootstrap<T>): R {
        LOADERS.put(key.location) { loader.run(registry) }
        WRITABLE_PARENT.register(key as ResourceKey<WritableRegistry<*>>, registry)
        return registry
    }

    /*
     * Registry registration functions. Designed to stop having to leak the WritableRegistry API in to other parts of the system,
     * as well as controlling how registry values are registered.
     */

    @JvmStatic
    fun <T, V : T> register(registry: AquaRegistry<T>, key: Key, value: V): V =
        register(registry, AquaResourceKey.of(registry.key, key), value)

    @JvmStatic
    fun <T, V : T> register(registry: AquaRegistry<T>, key: ResourceKey<T>, value: V): V {
        (registry as WritableRegistry<T>).register(key, value)
        return value
    }

    /*
     * Bootstrap constructors. Used to create Bootstrap functions from registry loaders and data loaders.
     */

    @JvmStatic
    private fun <T> loader(loader: Supplier<RegistryLoader<T>>): Bootstrap<T> =
        Bootstrap { loader.get().forEach { key, value -> register(it, key, value) } }

    @JvmStatic
    private inline fun <T> dataLoader(crossinline loader: (AquaRegistry<T>) -> AquaDataLoader<T>): Bootstrap<T> =
        Bootstrap { loader(it).init() }

    /*
     * Bootstrapping methods. Used to initialize all the registries at the right time from the Bootstrap class.
     */

    @JvmStatic
    fun bootstrap() {
        WRITABLE_PARENT.freeze()
        runLoaders()
        WRITABLE_PARENT.forEach { it.freeze() }
        validateAll(WRITABLE_PARENT)
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
    fun <T : Registry<*>> validateAll(parent: Registry<T>) {
        parent.forEach { registry ->
            if (registry.keys().isEmpty()) LOGGER.error("Registry ${registry.key} was empty after loading!")
            if (registry is AquaDefaultedRegistry<*>) {
                val defaultKey = registry.defaultKey
                checkNotNull(registry.get(defaultKey)) { "Default value for key $defaultKey in registry ${registry.key} was not loaded!" }
            }
        }
    }

    /**
     * A function used to bootstrap registries by preloading all the values in to them, available for use very early in the server
     * lifecycle, to avoid any failures in attempting to retrieve them.
     */
    private fun interface Bootstrap<T> {

        fun run(registry: AquaRegistry<T>)
    }

    private class RegistryInitializationException(message: String, cause: Throwable) : RuntimeException(message, cause)

    /**
     * The backend registry manager implementation. Moved inside AquaRegistries as it needs access to internals that would rather
     * not be otherwise published and available to other components.
     */
    object StaticHolder : RegistryHolder {

        override val registries: Collection<Registry<*>>
            get() = Collections2.transform(PARENT.holders()) { it.value() }

        @Suppress("UNCHECKED_CAST")
        override fun <E> getRegistry(key: ResourceKey<out Registry<E>>): Registry<E>? {
            return WRITABLE_PARENT.get(key as ResourceKey<WritableRegistry<*>>) as? Registry<E>
        }

        @Suppress("UNCHECKED_CAST")
        override fun <E> getDefaultedRegistry(key: ResourceKey<out Registry<E>>): DefaultedRegistry<E>? {
            return WRITABLE_PARENT.get(key as ResourceKey<WritableRegistry<*>>) as? DefaultedRegistry<E>
        }
    }
}

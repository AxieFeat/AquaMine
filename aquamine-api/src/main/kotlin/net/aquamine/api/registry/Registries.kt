package net.aquamine.api.registry

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import net.aquamine.api.AquaMine
import net.aquamine.api.block.Block
import net.aquamine.api.block.entity.BlockEntityType
import net.aquamine.api.block.entity.banner.BannerPatternType
import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.entity.EntityCategory
import net.aquamine.api.entity.EntityType
import net.aquamine.api.entity.attribute.AttributeType
import net.aquamine.api.entity.hanging.PaintingVariant
import net.aquamine.api.fluid.Fluid
import net.aquamine.api.inventory.InventoryType
import net.aquamine.api.item.ItemType
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.resource.ResourceKeys
import net.aquamine.api.scoreboard.criteria.KeyedCriterion
import net.aquamine.api.statistic.StatisticType
import net.aquamine.api.world.damage.type.DamageType

/**
 * Holder of all the built-in registries.
 */
@Catalogue(Registry::class)
object Registries {

    /**
     * All built-in vanilla registries.
     */
    @JvmField
    val SOUND_EVENT: Registry<SoundEvent> = builtin(ResourceKeys.SOUND_EVENT)
    @JvmField
    val FLUID: DefaultedRegistry<Fluid> = builtinDefaulted(ResourceKeys.FLUID)
    @JvmField
    val BLOCK: DefaultedRegistry<Block> = builtinDefaulted(ResourceKeys.BLOCK)
    @JvmField
    val ENTITY_TYPE: DefaultedRegistry<EntityType<*>> = builtinDefaulted(ResourceKeys.ENTITY_TYPE)
    @JvmField
    val ITEM: DefaultedRegistry<ItemType> = builtinDefaulted(ResourceKeys.ITEM)
    @JvmField
    val PARTICLE_TYPE: Registry<ParticleType> = builtin(ResourceKeys.PARTICLE_TYPE)
    @JvmField
    val BLOCK_ENTITY_TYPE: Registry<BlockEntityType<*>> = builtin(ResourceKeys.BLOCK_ENTITY_TYPE)
    @JvmField
    val PAINTING_VARIANT: DefaultedRegistry<PaintingVariant> = builtinDefaulted(ResourceKeys.PAINTING_VARIANT)
    @JvmField
    val CUSTOM_STATISTIC: Registry<Key> = builtin(ResourceKeys.CUSTOM_STATISTIC)
    @JvmField
    val INVENTORY_TYPE: Registry<InventoryType> = builtin(ResourceKeys.INVENTORY_TYPE)
    @JvmField
    val ATTRIBUTE: Registry<AttributeType> = builtin(ResourceKeys.ATTRIBUTE)
    @JvmField
    val STATISTIC_TYPE: Registry<StatisticType<*>> = builtin(ResourceKeys.STATISTIC_TYPE)
    @JvmField
    val BANNER_PATTERN: Registry<BannerPatternType> = builtin(ResourceKeys.BANNER_PATTERN)

    /**
     * Custom built-in registries.
     */
    @JvmField
    val CRITERIA: Registry<KeyedCriterion> = builtin(ResourceKeys.CRITERIA)
    @JvmField
    val ENTITY_CATEGORIES: Registry<EntityCategory> = builtin(ResourceKeys.ENTITY_CATEGORIES)
    @JvmField
    val DAMAGE_TYPES: Registry<DamageType> = builtin(ResourceKeys.DAMAGE_TYPES)

    @JvmStatic
    private fun <T> builtin(key: ResourceKey<out Registry<T>>): Registry<T> {
        return requireNotNull(getRegistry(key)) { "Cannot find built-in registry $key!" }
    }

    @JvmStatic
    private fun <T> builtinDefaulted(key: ResourceKey<out Registry<T>>): DefaultedRegistry<T> {
        return requireNotNull(getDefaultedRegistry(key)) { "Cannot find built-in defaulted registry $key!" }
    }

    /**
     * Gets the existing registry with the given resource [key], or returns null
     * if there is no existing registry with the given resource [key].
     *
     * @param T The registry element type.
     * @param key The key.
     *
     * @return The existing registry, or null if not present.
     */
    @JvmStatic
    fun <T> getRegistry(key: ResourceKey<out Registry<T>>): Registry<T>? = AquaMine.staticRegistryHolder().getRegistry(key)

    /**
     * Gets the existing defaulted registry with the given resource [key], or
     * returns null if there is no existing-defaulted registry with the given
     * resource [key].
     *
     * @param T The registry element type.
     * @param key The key.
     *
     * @return The existing defaulted registry, or null if not present.
     */
    @JvmStatic
    fun <T> getDefaultedRegistry(key: ResourceKey<out Registry<T>>): DefaultedRegistry<T>? {
        return AquaMine.staticRegistryHolder().getDefaultedRegistry(key)
    }
}

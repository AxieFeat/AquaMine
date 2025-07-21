package net.aquamine.api.resource

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import org.jetbrains.annotations.Contract
import net.aquamine.api.block.Block
import net.aquamine.api.block.entity.BlockEntityType
import net.aquamine.api.block.entity.banner.BannerPatternType
import net.aquamine.api.effect.particle.ParticleType
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.entity.EntityType
import net.aquamine.api.entity.EntityCategory
import net.aquamine.api.entity.attribute.AttributeType
import net.aquamine.api.entity.hanging.PaintingVariant
import net.aquamine.api.fluid.Fluid
import net.aquamine.api.inventory.InventoryType
import net.aquamine.api.item.ItemType
import net.aquamine.api.potion.PotionType
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryRoots
import net.aquamine.api.scoreboard.criteria.KeyedCriterion
import net.aquamine.api.statistic.StatisticType
import net.aquamine.api.world.World
import net.aquamine.api.world.biome.Biome
import net.aquamine.api.world.dimension.DimensionType
import net.aquamine.api.world.damage.type.DamageType
import net.aquamine.api.world.gameevent.GameEvent

/**
 * All the built-in registry keys for various registries.
 */
@Catalogue(ResourceKey::class)
object ResourceKeys {

    /**
     * Built-in vanilla registries.
     */
    @JvmField
    val GAME_EVENT: ResourceKey<out Registry<GameEvent>> = minecraft("game_event")
    @JvmField
    val SOUND_EVENT: ResourceKey<out Registry<SoundEvent>> = minecraft("sound_event")
    @JvmField
    val ENTITY_TYPE: ResourceKey<out Registry<EntityType<*>>> = minecraft("entity_type")
    @JvmField
    val PARTICLE_TYPE: ResourceKey<out Registry<ParticleType>> = minecraft("particle_type")
    @JvmField
    val BLOCK: ResourceKey<out Registry<Block>> = minecraft("block")
    @JvmField
    val ITEM: ResourceKey<out Registry<ItemType>> = minecraft("item")
    @JvmField
    val DIMENSION: ResourceKey<out Registry<World>> = minecraft("dimension")
    @JvmField
    val ATTRIBUTE: ResourceKey<out Registry<AttributeType>> = minecraft("attribute")
    @JvmField
    val BIOME: ResourceKey<out Registry<Biome>> = minecraft("worldgen/biome")
    @JvmField
    val INVENTORY_TYPE: ResourceKey<out Registry<InventoryType>> = minecraft("menu")
    @JvmField
    val STATISTIC_TYPE: ResourceKey<out Registry<StatisticType<*>>> = minecraft("stat_type")
    @JvmField
    val CUSTOM_STATISTIC: ResourceKey<out Registry<Key>> = minecraft("custom_stat")
    @JvmField
    val FLUID: ResourceKey<out Registry<Fluid>> = minecraft("fluid")
    @JvmField
    val DIMENSION_TYPE: ResourceKey<out Registry<DimensionType>> = minecraft("dimension_type")
    @JvmField
    val BLOCK_ENTITY_TYPE: ResourceKey<out Registry<BlockEntityType<*>>> = minecraft("block_entity_type")
    @JvmField
    val BANNER_PATTERN: ResourceKey<out Registry<BannerPatternType>> = minecraft("banner_pattern")
    @JvmField
    val PAINTING_VARIANT: ResourceKey<out Registry<PaintingVariant>> = minecraft("painting_variant")
    @JvmField
    val POTION_TYPE: ResourceKey<out Registry<PotionType>> = minecraft("potion_type")

    /**
     * Custom built-in registries.
     */
    @JvmField
    val CRITERIA: ResourceKey<out Registry<KeyedCriterion>> = aquamine("criteria")
    @JvmField
    val ENTITY_CATEGORIES: ResourceKey<out Registry<EntityCategory>> = aquamine("entity_categories")
    @JvmField
    val DAMAGE_TYPES: ResourceKey<out Registry<DamageType>> = aquamine("damage_types")

    /**
     * Creates a new registry key with the given [key] as its base key.
     *
     * This will use [RegistryRoots.MINECRAFT] as its root.
     *
     * @param T The resource key type.
     * @param key The key.
     *
     * @return A new registry key.
     */
    @JvmStatic
    @Contract("_ -> new", pure = true)
    fun <T> minecraft(key: String): ResourceKey<out Registry<T>> = ResourceKey.of(RegistryRoots.MINECRAFT, Key.key(key))

    /**
     * Creates a new registry key with the given [key] as its base key.
     *
     * This will use [RegistryRoots.AQUAMINE] as its root.
     *
     * @param T The resource key type.
     * @param key The key.
     *
     * @return A new registry key.
     */
    @JvmStatic
    @Contract("_ -> new", pure = true)
    fun <T> aquamine(key: String): ResourceKey<out Registry<T>> =
        ResourceKey.of(RegistryRoots.AQUAMINE, Key.key("aquamine", key))
}

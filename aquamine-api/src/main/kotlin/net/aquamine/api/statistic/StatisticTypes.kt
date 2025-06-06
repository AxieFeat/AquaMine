package net.aquamine.api.statistic

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import net.aquamine.api.block.Block
import net.aquamine.api.entity.EntityType
import net.aquamine.api.item.ItemType
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference

/**
 * All of the built-in statistic types.
 */
@Catalogue(StatisticType::class)
object StatisticTypes {

    // @formatter:off
    @JvmField val BLOCK_MINED: RegistryReference<StatisticType<Block>> = get("mined")
    @JvmField val ITEM_CRAFTED: RegistryReference<StatisticType<ItemType>> = get("crafted")
    @JvmField val ITEM_USED: RegistryReference<StatisticType<ItemType>> = get("used")
    @JvmField val ITEM_BROKEN: RegistryReference<StatisticType<ItemType>> = get("broken")
    @JvmField val ITEM_PICKED_UP: RegistryReference<StatisticType<ItemType>> = get("picked_up")
    @JvmField val ITEM_DROPPED: RegistryReference<StatisticType<ItemType>> = get("dropped")
    @JvmField val ENTITY_KILLED: RegistryReference<StatisticType<EntityType<*>>> = get("killed")
    @JvmField val ENTITY_KILLED_BY: RegistryReference<StatisticType<EntityType<*>>> = get("killed_by")
    @JvmField val CUSTOM: RegistryReference<StatisticType<Key>> = get("custom")

    // @formatter:on
    @JvmStatic
    private fun <T : Any> get(name: String): RegistryReference<StatisticType<T>> = RegistryReference.of(Registries.STATISTIC_TYPE, Key.key(name))
}

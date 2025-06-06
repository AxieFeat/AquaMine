package net.aquamine.api.inventory

import net.aquamine.annotations.Catalogue
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registries
import net.aquamine.api.registry.RegistryReference

/**
 * All the built-in inventory types.
 *
 * Note: The player inventory is not included in this list as it is not a valid
 * menu type and cannot be created or opened client-side.
 */
@Catalogue(InventoryType::class)
object InventoryTypes {

    // @formatter:off
    @JvmField val CHEST_ONE_ROW: RegistryReference<InventoryType> = of("chest_one_row")
    @JvmField val CHEST_TWO_ROWS: RegistryReference<InventoryType> = of("chest_two_rows")
    @JvmField val CHEST_THREE_ROWS: RegistryReference<InventoryType> = of("chest_three_rows")
    @JvmField val CHEST_FOUR_ROWS: RegistryReference<InventoryType> = of("chest_four_rows")
    @JvmField val CHEST_FIVE_ROWS: RegistryReference<InventoryType> = of("chest_five_rows")
    @JvmField val CHEST_SIX_ROWS: RegistryReference<InventoryType> = of("chest_six_rows")
    @JvmField val GENERIC_3x3: RegistryReference<InventoryType> = of("generic_3x3")
    @JvmField val ANVIL: RegistryReference<InventoryType> = of("anvil")
    @JvmField val BEACON: RegistryReference<InventoryType> = of("beacon")
    @JvmField val BLAST_FURNACE: RegistryReference<InventoryType> = of("blast_furnace")
    @JvmField val BREWING_STAND: RegistryReference<InventoryType> = of("brewing_stand")
    @JvmField val CRAFTING_TABLE: RegistryReference<InventoryType> = of("crafting")
    @JvmField val ENCHANTING_TABLE: RegistryReference<InventoryType> = of("enchantment")
    @JvmField val FURNACE: RegistryReference<InventoryType> = of("furnace")
    @JvmField val GRINDSTONE: RegistryReference<InventoryType> = of("grindstone")
    @JvmField val HOPPER: RegistryReference<InventoryType> = of("hopper")
    @JvmField val LECTERN: RegistryReference<InventoryType> = of("lectern")
    @JvmField val LOOM: RegistryReference<InventoryType> = of("loom")
    @JvmField val MERCHANT: RegistryReference<InventoryType> = of("merchant")
    @JvmField val SHULKER_BOX: RegistryReference<InventoryType> = of("shulker_box")
    @JvmField val SMITHING_TABLE: RegistryReference<InventoryType> = of("smithing")
    @JvmField val SMOKER: RegistryReference<InventoryType> = of("smoker")
    @JvmField val CARTOGRAPHY_TABLE: RegistryReference<InventoryType> = of("cartography_table")
    @JvmField val STONECUTTER: RegistryReference<InventoryType> = of("stonecutter")

    // @formatter:on
    @JvmStatic
    private fun of(name: String): RegistryReference<InventoryType> = RegistryReference.of(Registries.INVENTORY_TYPE, Key.key(name))
}

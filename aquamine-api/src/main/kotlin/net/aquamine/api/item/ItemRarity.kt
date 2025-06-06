package net.aquamine.api.item

import net.kyori.adventure.text.format.NamedTextColor

/**
 * The rarity of an item. This determines what color the lore text appears as
 * when the tooltip is read.
 *
 * @property color The colour the lore text will appear.
 */
enum class ItemRarity(val color: NamedTextColor) {

    COMMON(NamedTextColor.WHITE),
    UNCOMMON(NamedTextColor.YELLOW),
    RARE(NamedTextColor.AQUA),
    EPIC(NamedTextColor.LIGHT_PURPLE)
}

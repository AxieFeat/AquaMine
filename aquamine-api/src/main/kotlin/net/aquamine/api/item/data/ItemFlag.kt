package net.aquamine.api.item.data

/**
 * A flag that may be applied to an item to hide something that would usually
 * display on it, such as its enchantments.
 */
enum class ItemFlag {

    HIDE_ENCHANTMENTS,
    HIDE_ATTRIBUTES,
    HIDE_UNBREAKABLE,
    HIDE_CAN_DESTROY,
    HIDE_CAN_PLACE,
    HIDE_MISCELLANEOUS,
    HIDE_DYE
}

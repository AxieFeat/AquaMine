package net.aquamine.api.entity

/**
 * A slot that a piece of equipment may be in.
 *
 * @property type The type of equipment that this slot is for.
 */
enum class EquipmentSlot(val type: Type) {

    MAIN_HAND(Type.HAND),
    OFF_HAND(Type.HAND),
    FEET(Type.ARMOR),
    LEGS(Type.ARMOR),
    CHEST(Type.ARMOR),
    HEAD(Type.ARMOR);

    /**
     * A type of equipment slot.
     */
    enum class Type {

        HAND,
        ARMOR
    }
}

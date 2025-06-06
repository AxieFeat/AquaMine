package net.aquamine.api.block.meta

/**
 * Indicates which part of the two-block bed a block this property is applied
 * to represents.
 *
 * The head of the bed is the part with the pillow, and where the player's
 * head is when the player lays down in the bed. The foot is the other end,
 * where the player's feet are when laying down.
 */
enum class BedPart {

    HEAD,
    FOOT
}

package net.aquamine.api.block.entity

import net.kyori.adventure.text.Component

/**
 * A block entity that can be named.
 */
interface NameableBlockEntity : BlockEntity {

    /**
     * The display name of the block entity.
     */
    var displayName: Component
}

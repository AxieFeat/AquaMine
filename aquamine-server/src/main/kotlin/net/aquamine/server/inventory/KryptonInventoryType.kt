package net.aquamine.server.inventory

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.aquamine.api.inventory.InventoryType

class KryptonInventoryType(private val key: Key, override val size: Int, override val defaultTitle: Component) : InventoryType {

    override fun key(): Key = key

    override fun toString(): String = "KryptonInventoryType(size=$size, defaultTitle=$defaultTitle)"
}

package net.aquamine.server.item

import net.aquamine.server.util.InteractionResult

/**
 * The result of using an item.
 */
@JvmRecord
data class UseItemResult(val result: InteractionResult, val item: KryptonItemStack)

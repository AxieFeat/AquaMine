package net.aquamine.server.entity.hanging

import net.kyori.adventure.key.Key
import net.aquamine.api.entity.hanging.PaintingVariant

@JvmRecord
data class KryptonPaintingVariant(private val key: Key, override val width: Int, override val height: Int) : PaintingVariant {

    override fun key(): Key = key
}

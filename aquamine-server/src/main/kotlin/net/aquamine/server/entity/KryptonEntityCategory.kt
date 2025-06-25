package net.aquamine.server.entity

import net.kyori.adventure.key.Key
import net.aquamine.api.entity.EntityCategory

@JvmRecord
data class KryptonEntityCategory(
    private val key: Key,
    val max: Int,
    override val isFriendly: Boolean,
    val isPersistent: Boolean,
    override val despawnDistance: Int,
    val noDespawnDistance: Int
) : EntityCategory {

    override fun key(): Key = key
}

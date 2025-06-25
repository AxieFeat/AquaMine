package net.aquamine.server.world.gameevent

import net.kyori.adventure.key.Key
import net.kyori.adventure.key.Keyed
import net.aquamine.api.tags.TagKey
import net.aquamine.server.registry.KryptonRegistries

class GameEvent(private val key: Key) : Keyed {

    private val builtInRegistryHolder = KryptonRegistries.GAME_EVENT.createIntrusiveHolder(this)

    override fun key(): Key = key

    fun eq(tag: TagKey<GameEvent>): Boolean = builtInRegistryHolder.eq(tag)
}

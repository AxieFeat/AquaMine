package net.aquamine.server.world.gameevent

import net.aquamine.api.world.gameevent.GameEvent
import net.kyori.adventure.key.Key

@JvmRecord
data class AquaGameEvent(
    private val key: Key,
    override val radius: Int
) : GameEvent {

    override fun key(): Key = key

}
package net.aquamine.server.world.gameevent

import com.google.gson.JsonObject
import net.aquamine.api.world.gameevent.GameEvent
import net.aquamine.server.registry.AquaRegistry
import net.aquamine.server.util.AquaDataLoader
import net.kyori.adventure.key.Key

class GameEventLoader(registry: AquaRegistry<GameEvent>) : AquaDataLoader<GameEvent>("game_events", registry) {

    override fun create(key: Key, value: JsonObject): GameEvent {
        return AquaGameEvent(key, value.get("notificationRadius").asInt)
    }
}

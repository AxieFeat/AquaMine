package net.aquamine.server.effect.sound

import com.google.gson.JsonObject
import net.kyori.adventure.key.Key
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.server.registry.AquaRegistry
import net.aquamine.server.util.AquaDataLoader

class SoundLoader(registry: AquaRegistry<SoundEvent>) : AquaDataLoader<SoundEvent>("sounds", registry) {

    override fun create(key: Key, value: JsonObject): SoundEvent {
        val range = value.get("range")
        val newSystem = range != null
        return AquaSoundEvent(key, if (newSystem) range.asFloat else AquaSoundEvent.DEFAULT_RANGE, newSystem)
    }
}

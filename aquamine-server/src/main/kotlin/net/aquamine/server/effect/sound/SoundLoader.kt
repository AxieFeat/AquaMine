package net.aquamine.server.effect.sound

import com.google.gson.JsonObject
import net.kyori.adventure.key.Key
import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.server.registry.KryptonRegistry
import net.aquamine.server.util.KryptonDataLoader

class SoundLoader(registry: KryptonRegistry<SoundEvent>) : KryptonDataLoader<SoundEvent>("sounds", registry) {

    override fun create(key: Key, value: JsonObject): SoundEvent {
        val range = value.get("range")
        val newSystem = range != null
        return KryptonSoundEvent(key, if (newSystem) range.asFloat else KryptonSoundEvent.DEFAULT_RANGE, newSystem)
    }
}

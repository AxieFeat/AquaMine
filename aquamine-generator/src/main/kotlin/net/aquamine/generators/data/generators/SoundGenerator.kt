package net.aquamine.generators.data.generators

import com.google.gson.JsonObject
import net.aquamine.generators.data.Generator
import net.aquamine.generators.data.ReflectionHelper.getHiddenField
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents

// TODO Fixme
object SoundGenerator : Generator<SoundEvent>() {

    override val names: Map<SoundEvent, String> = buildMap {
        SoundEvents::class.java.getDeclaredFields().filter {
            SoundEvent::class.java.isAssignableFrom(it.type)
        }.forEach {
            val soundEvent = it.get(null) as SoundEvent
            put(soundEvent, it.name)
        }

        SoundEvents.GOAT_HORN_SOUND_VARIANTS.forEachIndexed { i, it ->
            put(it.value(), "GOAT_HORN_$i")
        }
    }

    override fun generate(): JsonObject {
        val sounds = JsonObject()

        val soundRegistry = BuiltInRegistries.SOUND_EVENT

        soundRegistry.keySet().sortedBy {
            soundRegistry.getId(soundRegistry.get(it).get().value())
        }.forEach { resource ->
            val minecraftSound = soundRegistry.get(resource).get().value()

            val sound = JsonObject()

            sound.addProperty("range", 16f)

            sounds.add(resource.toString(), sound)
        }

        return sounds
    }

}

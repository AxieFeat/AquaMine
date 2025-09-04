package net.aquamine.generators.data.generators

import com.google.gson.JsonObject
import net.aquamine.generators.data.Generator
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.gameevent.GameEvent

object GameEventGenerator : Generator<GameEvent>() {

    override val names: Map<GameEvent, String> = Blocks::class.java.getDeclaredFields().filter {
        GameEvent::class.java.isAssignableFrom(it.type)
    }.associate {
        val block = it.get(null) as GameEvent
        block to it.name
    }

    override fun generate(): JsonObject {
        val gameEvents = JsonObject()

        val gameEventRegistry = BuiltInRegistries.GAME_EVENT

        gameEventRegistry.keySet().forEach { resource ->
            val minecraftGameEvent = gameEventRegistry.get(resource).get().value()

            val gameEvent = JsonObject()

            gameEvent.addProperty("notificationRadius", minecraftGameEvent.notificationRadius)

            gameEvents.add(resource.toString(), gameEvent)
        }

        return gameEvents
    }

}

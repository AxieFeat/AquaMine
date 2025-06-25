package net.aquamine.server.world.block

import com.google.gson.JsonObject
import net.kyori.adventure.key.Key
import net.aquamine.api.block.BlockSoundGroup
import net.aquamine.server.registry.KryptonRegistry
import net.aquamine.server.state.property.KryptonProperty
import net.aquamine.server.state.property.KryptonPropertyFactory
import net.aquamine.server.util.KryptonDataLoader
import net.aquamine.server.world.block.data.BlockSoundGroups
import net.aquamine.server.world.block.handlers.DefaultBlockHandler
import net.aquamine.server.world.material.Material
import net.aquamine.server.world.material.Materials
import java.lang.reflect.Modifier

class BlockLoader(registry: KryptonRegistry<KryptonBlock>) : KryptonDataLoader<KryptonBlock>("blocks", registry) {

    private val materialsByName: Map<String, Material> = Materials::class.java.declaredFields.asSequence()
        .filter { Modifier.isPublic(it.modifiers) && Modifier.isStatic(it.modifiers) }
        .filter { Material::class.java.isAssignableFrom(it.type) }
        .associate { it.name to it.get(null) as Material }

    private val soundGroupsByName: Map<String, BlockSoundGroup> = BlockSoundGroups::class.java.declaredFields.asSequence()
        .filter { Modifier.isPublic(it.modifiers) && Modifier.isStatic(it.modifiers) }
        .filter { BlockSoundGroup::class.java.isAssignableFrom(it.type) }
        .associate { it.name to it.get(null) as BlockSoundGroup }

    override fun create(key: Key, value: JsonObject): KryptonBlock {
        val materialName = "STONE"
        val soundGroupName = "STONE"
        val states = value.getAsJsonArray("states").first().asJsonObject

        val properties = BlockProperties(
            requireNotNull(materialsByName[materialName]) { "Could not find material for name $materialName!" },
            true,
            requireNotNull(soundGroupsByName[soundGroupName]) { "Could not find sound group for name $soundGroupName!" },
            value.get("explosionResistance").asFloat,
            value.get("defaultHardness").asFloat,
            states.get("toolRequired").asBoolean,
            value.get("friction").asFloat,
            value.get("speedFactor").asFloat,
            value.get("jumpFactor").asFloat,
            if (value.has("drops")) Key.key(value.get("drops").asString) else null,
            states.get("occludes").asBoolean,
            states.get("air").asBoolean,
            value.get("dynamicShape").asBoolean
        )

        val blockProperties =  value.get("properties").asJsonArray
        val stateProperties = mutableListOf<KryptonProperty<*>>()

        blockProperties.forEach {
            try {
                stateProperties.add(KryptonPropertyFactory.findByName(it.asString))
            } catch (ignore: Exception) {}
        }

        // TODO: Update this to get the handlers from somewhere
        return KryptonBlock(properties, DefaultBlockHandler, DefaultBlockHandler, DefaultBlockHandler, DefaultBlockHandler, stateProperties)
    }
}

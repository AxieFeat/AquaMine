package net.aquamine.server.world.block

import com.google.gson.JsonObject
import net.aquamine.api.block.BlockSoundGroup
import net.aquamine.server.registry.AquaRegistry
import net.aquamine.server.state.property.AquaPropertyFactory
import net.aquamine.server.util.AquaDataLoader
import net.aquamine.server.world.block.data.BlockSoundGroups
import net.aquamine.server.world.block.handlers.DefaultBlockHandler
import net.aquamine.server.world.material.Material
import net.aquamine.server.world.material.Materials
import net.kyori.adventure.key.Key
import java.lang.reflect.Modifier

class BlockLoader(registry: AquaRegistry<AquaBlock>) : AquaDataLoader<AquaBlock>("blocks", registry) {

    private val materialsByName: Map<String, Material> = Materials::class.java.declaredFields.asSequence()
        .filter { Modifier.isPublic(it.modifiers) && Modifier.isStatic(it.modifiers) }
        .filter { Material::class.java.isAssignableFrom(it.type) }
        .associate { it.name to it.get(null) as Material }

    private val soundGroupsByName: Map<String, BlockSoundGroup> = BlockSoundGroups::class.java.declaredFields.asSequence()
        .filter { Modifier.isPublic(it.modifiers) && Modifier.isStatic(it.modifiers) }
        .filter { BlockSoundGroup::class.java.isAssignableFrom(it.type) }
        .associate { it.name to it.get(null) as BlockSoundGroup }

    override fun create(key: Key, value: JsonObject): AquaBlock {
        val materialName = value.get("material").asString
        val soundGroupName = value.get("soundGroup").asString
        val soundGroup =  soundGroupsByName[soundGroupName] ?: BlockSoundGroups.STONE

        val properties = BlockProperties(
            requireNotNull(materialsByName[materialName]) { "Could not find material for name $materialName!" },
            true, // TODO Fixme
            soundGroup,
            value.get("explosionResistance").asFloat,
            value.get("defaultHardness").asFloat,
            value.get("toolRequired").asBoolean,
            value.get("friction").asFloat,
            value.get("speedFactor").asFloat,
            value.get("jumpFactor").asFloat,
            if (value.has("drops")) Key.key(value.get("drops").asString) else null,
            value.get("occludes").asBoolean,
            value.get("air").asBoolean,
            value.get("dynamicShape").asBoolean
        )

        val stateProperties = try {
            value.get("properties").asJsonArray.map { AquaPropertyFactory.findByName(it.asString) }
        } catch (e: Exception) {
            println("Cant find ${value.get("properties").asJsonArray.joinToString(", ") { it.asString }}")
            throw e
        }

        // TODO: Update this to get the handlers from somewhere
        return AquaBlock(properties, DefaultBlockHandler, DefaultBlockHandler, DefaultBlockHandler, DefaultBlockHandler, stateProperties)
    }
}

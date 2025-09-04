package net.aquamine.generators.data.generators

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.aquamine.generators.data.Generator
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import java.lang.reflect.Modifier

object BlockGenerator : Generator<Block>() {

    override val names: Map<Block, String> = Blocks::class.java.getDeclaredFields().filter {
        Block::class.java.isAssignableFrom(it.type)
    }.associate {
            val block = it.get(null) as Block
            block to it.name
    }

    private val soundTypes: Map<SoundType, String> = SoundType::class.java.declaredFields.asSequence()
        .filter { Modifier.isPublic(it.modifiers) && Modifier.isStatic(it.modifiers) }
        .filter { SoundType::class.java.isAssignableFrom(it.type) }
        .associate { it.get(null) as SoundType to it.name }

    // TODO Fixme
//    private val materials: Map<Material, String> = Material::class.java.declaredFields.asSequence()
//        .filter { Modifier.isPublic(it.modifiers) && Modifier.isStatic(it.modifiers) }
//        .filter { Material::class.java.isAssignableFrom(it.type) }
//        .associate { it.get(null) as Material to it.name }

    override fun generate(): JsonObject {
        val blocks = JsonObject()

        val blockRegistry = BuiltInRegistries.BLOCK

        val propertyNames = BlockPropertyGenerator.names

        blockRegistry.keySet().sortedBy {
            blockRegistry.getId(blockRegistry.get(it).get().value())
        }.forEach { resource ->
            val minecraftBlock = blockRegistry.get(resource).get().value()
            val blockState = minecraftBlock.defaultBlockState()

            val block = JsonObject()

            block.addProperty("material", "STONE")
            block.addProperty("soundGroup", soundTypes[blockState.soundType])
            block.addProperty("explosionResistance", minecraftBlock.explosionResistance)
            block.addProperty("friction", minecraftBlock.friction)
            block.addProperty("speedFactor", minecraftBlock.speedFactor)
            block.addProperty("jumpFactor", minecraftBlock.jumpFactor)
            block.addProperty("dynamicShape", minecraftBlock.hasDynamicShape())
            block.addProperty("defaultHardness", minecraftBlock.defaultDestroyTime())

            block.addProperty("toolRequired", blockState.requiresCorrectToolForDrops())
            block.addProperty("occludes", blockState.canOcclude())
            block.addProperty("air", blockState.isAir)

            val properties = JsonArray().apply {
                minecraftBlock.getStateDefinition().properties.forEach { this.add(propertyNames[it]) }
            }

            block.add("properties", properties)

            blocks.add(resource.toString(), block)
        }

        return blocks
    }

}

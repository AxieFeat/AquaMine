package net.aquamine.generators

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.STAR
import net.minecraft.SharedConstants
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.server.Bootstrap
import net.minecraft.tags.BannerPatternTags
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.BlockTags
import net.minecraft.tags.EntityTypeTags
import net.minecraft.tags.FluidTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.Fluids
import java.nio.file.Path

fun main() {
    SharedConstants.tryDetectVersion()
    Bootstrap.bootStrap()
    val output = Path.of("aquamine-api/src/generated/kotlin")
    val generator = StandardGenerator(output)
    generator.run<Blocks, Block>(BuiltInRegistries.BLOCK, "block.Blocks", "block.Block", "BLOCK")
    SoundEventGenerator(output).run()
    generator.run<Fluids, Fluid>(BuiltInRegistries.FLUID, "fluid.Fluids", "fluid.Fluid", "FLUID")
    generator.run<Items, Item>(BuiltInRegistries.ITEM, "item.ItemTypes", "item.ItemType", "ITEM")
    generator.run<Attributes, Attribute>(BuiltInRegistries.ATTRIBUTE, "entity.attribute.AttributeTypes",
        "entity.attribute.AttributeType", "ATTRIBUTE")
    val patternType = ClassName("${PACKAGE}.block.entity.banner", "BannerPatternType")
    val entityType = ClassName("${PACKAGE}.entity", "EntityType").parameterizedBy(STAR)
    val tagGenerator = TagGenerator(output)
    tagGenerator.run<BannerPatternTags>("BannerPatternTags", "BANNER_PATTERN", patternType, "BannerPatternType")
    tagGenerator.run<BiomeTags>("BiomeTags", "BIOME", ClassName("${PACKAGE}.world.biome", "Biome"), "Biome")
    tagGenerator.run<BlockTags>("BlockTags", "BLOCK", ClassName("${PACKAGE}.block", "Block"), "Block")
    tagGenerator.run<EntityTypeTags>("EntityTypeTags", "ENTITY_TYPE", entityType, "EntityType<\\*>")
    tagGenerator.run<FluidTags>("FluidTags", "FLUID", ClassName("${PACKAGE}.fluid", "Fluid"), "Fluid")
    tagGenerator.run<ItemTags>("ItemTags", "ITEM", ClassName("${PACKAGE}.item", "ItemType"), "ItemType")
}

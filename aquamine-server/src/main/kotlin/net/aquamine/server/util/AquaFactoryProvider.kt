package net.aquamine.server.util

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import net.aquamine.api.adventure.AdventureMessage
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.auth.ProfileProperty
import net.aquamine.api.block.entity.banner.BannerPattern
import net.aquamine.api.state.Property
import net.aquamine.api.command.BrigadierCommand
import net.aquamine.api.command.CommandMeta
import net.aquamine.api.effect.Music
import net.aquamine.api.effect.particle.data.ParticleData
import net.aquamine.api.entity.attribute.AttributeModifier
import net.aquamine.api.event.EventListener
import net.aquamine.api.event.EventNode
import net.aquamine.api.item.ItemAttributeModifier
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.data.FireworkEffect
import net.aquamine.api.item.meta.ItemMeta
import net.aquamine.api.registry.DynamicRegistryReference
import net.aquamine.api.registry.RegistryReference
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.resource.ResourcePack
import net.aquamine.api.scheduling.TaskAction
import net.aquamine.api.scheduling.TaskTime
import net.aquamine.api.scoreboard.Scoreboard
import net.aquamine.api.tags.TagKey
import net.aquamine.api.util.TypeNotFoundException
import net.aquamine.api.util.FactoryProvider
import net.aquamine.api.util.register
import net.aquamine.api.world.biome.AmbientAdditionsSettings
import net.aquamine.api.world.biome.AmbientMoodSettings
import net.aquamine.api.world.biome.AmbientParticleSettings
import net.aquamine.api.world.biome.Biome
import net.aquamine.api.world.biome.BiomeEffects
import net.aquamine.api.world.biome.Climate
import net.aquamine.api.world.chunk.BlockChangeFlags
import net.aquamine.api.world.damage.DamageSource
import net.aquamine.api.world.dimension.DimensionType
import net.aquamine.api.world.rule.GameRule
import net.aquamine.server.adventure.AquaAdventureMessage
import net.aquamine.server.auth.AquaGameProfile
import net.aquamine.server.auth.AquaProfileProperty
import net.aquamine.server.command.AquaBrigadierCommand
import net.aquamine.server.command.AquaCommandMeta
import net.aquamine.server.effect.AquaMusic
import net.aquamine.server.effect.particle.data.AquaParticleDataFactory
import net.aquamine.server.entity.attribute.AquaAttributeModifier
import net.aquamine.server.event.AquaEventListener
import net.aquamine.server.event.AquaEventNode
import net.aquamine.server.item.data.AquaItemAttributeModifier
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.item.data.AquaFireworkEffect
import net.aquamine.server.item.meta.AquaItemMeta
import net.aquamine.server.registry.AquaDynamicRegistryReference
import net.aquamine.server.registry.AquaRegistryReference
import net.aquamine.server.resource.AquaResourceKey
import net.aquamine.server.resource.AquaResourcePack
import net.aquamine.server.scheduling.AquaTaskAction
import net.aquamine.server.scheduling.AquaTaskTime
import net.aquamine.server.world.biome.data.AquaAmbientAdditionsSettings
import net.aquamine.server.world.biome.data.AquaAmbientMoodSettings
import net.aquamine.server.world.biome.data.AquaAmbientParticleSettings
import net.aquamine.server.world.biome.AquaBiome
import net.aquamine.server.world.biome.data.AquaBiomeEffects
import net.aquamine.server.world.biome.data.AquaClimate
import net.aquamine.server.world.block.entity.banner.AquaBannerPattern
import net.aquamine.server.state.property.AquaPropertyFactory
import net.aquamine.server.tags.AquaTagKey
import net.aquamine.server.world.chunk.flag.AquaBlockChangeFlags
import net.aquamine.server.world.damage.AquaDamageSourceFactory
import net.aquamine.server.world.dimension.AquaDimensionType
import net.aquamine.server.world.rule.GameRuleKeys
import net.aquamine.server.world.scoreboard.AquaScoreboard

object AquaFactoryProvider : FactoryProvider {

    private val factories = Object2ObjectOpenHashMap<Class<*>, Any>()

    @Suppress("UNCHECKED_CAST")
    override fun <T> provide(type: Class<T>): T = factories.get(type) as? T ?: throw TypeNotFoundException("Type $type has no factory registered!")

    override fun <T> register(type: Class<T>, factory: T) {
        require(!factories.contains(type)) { "Duplicate registration for type $type!" }
        factories.put(type, factory)
    }

    @JvmStatic
    fun bootstrap() {
        register<AdventureMessage.Factory>(AquaAdventureMessage.Factory)
        register<GameProfile.Factory>(AquaGameProfile.Factory)
        register<ProfileProperty.Factory>(AquaProfileProperty.Factory)
        register<BannerPattern.Factory>(AquaBannerPattern.Factory)
        register<BrigadierCommand.Factory>(AquaBrigadierCommand.Factory)
        register<CommandMeta.Factory>(AquaCommandMeta.Factory)
        register<ParticleData.Factory>(AquaParticleDataFactory)
        register<Music.Factory>(AquaMusic.Factory)
        register<AttributeModifier.Factory>(AquaAttributeModifier.Factory)
        register<ItemAttributeModifier.Factory>(AquaItemAttributeModifier.Factory)
        register<ItemStack.Factory>(AquaItemStack.Factory)
        register<FireworkEffect.Factory>(AquaFireworkEffect.Factory)
        register<ItemMeta.Factory>(AquaItemMeta.Factory)
        register<ResourceKey.Factory>(AquaResourceKey.Factory)
        register<ResourcePack.Factory>(AquaResourcePack.Factory)
        register<Property.Factory>(AquaPropertyFactory)
        register<TagKey.Factory>(AquaTagKey.Factory)
        register<AmbientAdditionsSettings.Factory>(AquaAmbientAdditionsSettings.Factory)
        register<AmbientMoodSettings.Factory>(AquaAmbientMoodSettings.Factory)
        register<AmbientParticleSettings.Factory>(AquaAmbientParticleSettings.Factory)
        register<Biome.Factory>(AquaBiome.Factory)
        register<BiomeEffects.Factory>(AquaBiomeEffects.Factory)
        register<Climate.Factory>(AquaClimate.Factory)
        register<DamageSource.Factory>(AquaDamageSourceFactory)
        register<DimensionType.Factory>(AquaDimensionType.Factory)
        register<GameRule.Factory>(GameRuleKeys.Factory)
        register<RegistryReference.Factory>(AquaRegistryReference.Factory)
        register<DynamicRegistryReference.Factory>(AquaDynamicRegistryReference.Factory)
        register<TaskTime.Factory>(AquaTaskTime.Factory)
        register<TaskAction.Factory>(AquaTaskAction.Factory)
        register<EventNode.Factory>(AquaEventNode.Factory)
        register<EventListener.Factory>(AquaEventListener.Factory)
        register<BlockChangeFlags.Factory>(AquaBlockChangeFlags.Factory)
        register<Scoreboard.Factory>(AquaScoreboard.Factory)
    }
}

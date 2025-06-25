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
import net.aquamine.server.entity.attribute.KryptonAttributeModifier
import net.aquamine.server.event.KryptonEventListener
import net.aquamine.server.event.KryptonEventNode
import net.aquamine.server.item.data.KryptonItemAttributeModifier
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.item.data.KryptonFireworkEffect
import net.aquamine.server.item.meta.KryptonItemMeta
import net.aquamine.server.registry.KryptonDynamicRegistryReference
import net.aquamine.server.registry.KryptonRegistryReference
import net.aquamine.server.resource.KryptonResourceKey
import net.aquamine.server.resource.KryptonResourcePack
import net.aquamine.server.scheduling.KryptonTaskAction
import net.aquamine.server.scheduling.KryptonTaskTime
import net.aquamine.server.world.biome.data.KryptonAmbientAdditionsSettings
import net.aquamine.server.world.biome.data.KryptonAmbientMoodSettings
import net.aquamine.server.world.biome.data.KryptonAmbientParticleSettings
import net.aquamine.server.world.biome.KryptonBiome
import net.aquamine.server.world.biome.data.KryptonBiomeEffects
import net.aquamine.server.world.biome.data.KryptonClimate
import net.aquamine.server.world.block.entity.banner.KryptonBannerPattern
import net.aquamine.server.state.property.KryptonPropertyFactory
import net.aquamine.server.tags.KryptonTagKey
import net.aquamine.server.world.chunk.flag.KryptonBlockChangeFlags
import net.aquamine.server.world.damage.KryptonDamageSourceFactory
import net.aquamine.server.world.dimension.KryptonDimensionType
import net.aquamine.server.world.rule.GameRuleKeys
import net.aquamine.server.world.scoreboard.KryptonScoreboard

object KryptonFactoryProvider : FactoryProvider {

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
        register<BannerPattern.Factory>(KryptonBannerPattern.Factory)
        register<BrigadierCommand.Factory>(AquaBrigadierCommand.Factory)
        register<CommandMeta.Factory>(AquaCommandMeta.Factory)
        register<ParticleData.Factory>(AquaParticleDataFactory)
        register<Music.Factory>(AquaMusic.Factory)
        register<AttributeModifier.Factory>(KryptonAttributeModifier.Factory)
        register<ItemAttributeModifier.Factory>(KryptonItemAttributeModifier.Factory)
        register<ItemStack.Factory>(KryptonItemStack.Factory)
        register<FireworkEffect.Factory>(KryptonFireworkEffect.Factory)
        register<ItemMeta.Factory>(KryptonItemMeta.Factory)
        register<ResourceKey.Factory>(KryptonResourceKey.Factory)
        register<ResourcePack.Factory>(KryptonResourcePack.Factory)
        register<Property.Factory>(KryptonPropertyFactory)
        register<TagKey.Factory>(KryptonTagKey.Factory)
        register<AmbientAdditionsSettings.Factory>(KryptonAmbientAdditionsSettings.Factory)
        register<AmbientMoodSettings.Factory>(KryptonAmbientMoodSettings.Factory)
        register<AmbientParticleSettings.Factory>(KryptonAmbientParticleSettings.Factory)
        register<Biome.Factory>(KryptonBiome.Factory)
        register<BiomeEffects.Factory>(KryptonBiomeEffects.Factory)
        register<Climate.Factory>(KryptonClimate.Factory)
        register<DamageSource.Factory>(KryptonDamageSourceFactory)
        register<DimensionType.Factory>(KryptonDimensionType.Factory)
        register<GameRule.Factory>(GameRuleKeys.Factory)
        register<RegistryReference.Factory>(KryptonRegistryReference.Factory)
        register<DynamicRegistryReference.Factory>(KryptonDynamicRegistryReference.Factory)
        register<TaskTime.Factory>(KryptonTaskTime.Factory)
        register<TaskAction.Factory>(KryptonTaskAction.Factory)
        register<EventNode.Factory>(KryptonEventNode.Factory)
        register<EventListener.Factory>(KryptonEventListener.Factory)
        register<BlockChangeFlags.Factory>(KryptonBlockChangeFlags.Factory)
        register<Scoreboard.Factory>(KryptonScoreboard.Factory)
    }
}

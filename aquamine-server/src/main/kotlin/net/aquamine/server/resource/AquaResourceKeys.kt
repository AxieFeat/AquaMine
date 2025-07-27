package net.aquamine.server.resource

import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryRoots
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.world.World
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.ai.memory.MemoryKey
import net.aquamine.server.item.AquaItemType
import net.aquamine.server.item.data.Instrument
import net.aquamine.server.network.chat.RichChatType
import net.aquamine.server.potion.AquaPotionType
import net.aquamine.server.registry.WritableRegistry
import net.aquamine.server.util.provider.IntProviderType
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.dimension.AquaDimensionType
import net.aquamine.server.world.fluid.AquaFluid
import net.kyori.adventure.key.Key

object AquaResourceKeys {

    @JvmField
    val PARENT: ResourceKey<out Registry<WritableRegistry<*>>> = minecraft("root")
    @JvmField
    val FLUID: ResourceKey<out Registry<AquaFluid>> = minecraft("fluid")
    @JvmField
    val BLOCK: ResourceKey<out Registry<AquaBlock>> = minecraft("block")
    @JvmField
    val ENTITY_TYPE: ResourceKey<out Registry<AquaEntityType<*>>> = minecraft("entity_type")
    @JvmField
    val ITEM: ResourceKey<out Registry<AquaItemType>> = minecraft("item")
    @JvmField
    val MEMORIES: ResourceKey<out Registry<MemoryKey<*>>> = minecraft("memory_module_type")
    @JvmField
    val INT_PROVIDER_TYPES: ResourceKey<out Registry<IntProviderType<*>>> = minecraft("int_provider_type")
    @JvmField
    val INSTRUMENTS: ResourceKey<out Registry<Instrument>> = minecraft("instrument")
    @JvmField
    val CHAT_TYPE: ResourceKey<out Registry<RichChatType>> = minecraft("chat_type")
    @JvmField
    val WORLD: ResourceKey<out Registry<World>> = minecraft("dimension")
    @JvmField
    val DIMENSION_TYPE: ResourceKey<out Registry<AquaDimensionType>> = minecraft("dimension_type")
    @JvmField
    val POTION_TYPE: ResourceKey<out Registry<AquaPotionType>> = minecraft("potion_type")

    @JvmStatic
    private fun <T> minecraft(key: String): ResourceKey<out Registry<T>> = AquaResourceKey.of(RegistryRoots.MINECRAFT, Key.key(key))
}

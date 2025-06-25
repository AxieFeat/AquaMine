package net.aquamine.server.resource

import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryRoots
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.world.World
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.ai.memory.MemoryKey
import net.aquamine.server.item.data.Instrument
import net.aquamine.server.item.KryptonItemType
import net.aquamine.server.network.chat.RichChatType
import net.aquamine.server.registry.WritableRegistry
import net.aquamine.server.util.provider.IntProviderType
import net.aquamine.server.world.block.KryptonBlock
import net.aquamine.server.world.dimension.KryptonDimensionType
import net.aquamine.server.world.gameevent.GameEvent
import net.aquamine.server.world.fluid.KryptonFluid

object KryptonResourceKeys {

    @JvmField
    val PARENT: ResourceKey<out Registry<WritableRegistry<*>>> = minecraft("root")
    @JvmField
    val GAME_EVENT: ResourceKey<out Registry<GameEvent>> = minecraft("game_event")
    @JvmField
    val FLUID: ResourceKey<out Registry<KryptonFluid>> = minecraft("fluid")
    @JvmField
    val BLOCK: ResourceKey<out Registry<KryptonBlock>> = minecraft("block")
    @JvmField
    val ENTITY_TYPE: ResourceKey<out Registry<KryptonEntityType<*>>> = minecraft("entity_type")
    @JvmField
    val ITEM: ResourceKey<out Registry<KryptonItemType>> = minecraft("item")
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
    val DIMENSION_TYPE: ResourceKey<out Registry<KryptonDimensionType>> = minecraft("dimension_type")

    @JvmStatic
    private fun <T> minecraft(key: String): ResourceKey<out Registry<T>> = KryptonResourceKey.of(RegistryRoots.MINECRAFT, Key.key(key))
}

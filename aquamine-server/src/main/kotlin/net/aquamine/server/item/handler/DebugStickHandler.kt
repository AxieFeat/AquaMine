package net.aquamine.server.item.handler

import net.kyori.adventure.text.Component
import net.aquamine.api.entity.Hand
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.state.property.AquaProperty
import net.aquamine.server.util.collection.Iterables
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.chunk.flag.SetBlockFlag
import java.util.function.Consumer

object DebugStickHandler : ItemHandler {

    private val translation by lazy { ItemTypes.DEBUG_STICK.get().translationKey() }

    override fun canAttackBlock(player: AquaPlayer, world: AquaWorld, block: AquaBlockState, pos: Vec3i): Boolean {
        handleInteraction(player, world, block, pos, false, player.getHeldItem(Hand.MAIN)) { player.setHeldItem(Hand.MAIN, it) }
        return false
    }

    /* FIXME fix this when we sort out interactions
    override fun interact(context: InteractionContext): InteractionResult {
        val player = context.player
        val item = context.heldItem
        val world = context.world
        val position = context.position
        if (!handleInteraction(player, world, world.getBlock(position), position, true, item)) {
            return InteractionResult.FAIL
        }
        return InteractionResult.CONSUME
    }
     */

    // TODO: We need to get information about where the item was so we can replace it with a copy that has the modified metadata,
    //  as all item stacks are immutable, so we can't just simply modify the data on the item like vanilla does.
    @JvmStatic
    private fun handleInteraction(player: AquaPlayer, world: AquaWorld, state: AquaBlockState, pos: Vec3i, isUse: Boolean,
                                  item: AquaItemStack, setter: Consumer<AquaItemStack>): Boolean {
        if (!player.canUseGameMasterBlocks()) return false
        val block = state.block
        val definition = block.stateDefinition
        val properties = definition.properties()
        val key = AquaRegistries.BLOCK.getKey(block).asString()
        if (properties.isEmpty()) {
            player.sendActionBar(Component.translatable("$translation.empty", Component.text(key)))
            return false
        }

        var debugProperty = item.meta.data.getCompound("DebugProperty")
        val propertyName = debugProperty.getString(key)
        var property = definition.getProperty(propertyName)

        if (isUse) {
            if (property == null) property = properties.first()
            val cycled = cycleState(state, property, player.isSneaking)
            world.setBlock(pos, cycled, SetBlockFlag.UPDATE_NEIGHBOUR_SHAPES or SetBlockFlag.NOTIFY_CLIENTS)
            player.sendActionBar(Component.translatable("$translation.update", Component.text(property.name), toString(state, property)))
        } else {
            property = Iterables.findRelative(properties, property, player.isSneaking)!!
            debugProperty = debugProperty.putString(key, property.name)
            player.sendActionBar(Component.translatable("$translation.select", Component.text(property.name), toString(state, property)))
        }
        setter.accept(item.withMeta(item.meta.copy(item.meta.data.put("DebugProperty", debugProperty))))
        return true
    }

    @JvmStatic
    private fun <T : Comparable<T>> cycleState(state: AquaBlockState, property: AquaProperty<T>, reversed: Boolean): AquaBlockState =
        state.setProperty(property, Iterables.findRelative(property.values, state.requireProperty(property), reversed)!!)

    @JvmStatic
    private fun <T : Comparable<T>> toString(state: AquaBlockState, property: AquaProperty<T>): Component =
        Component.text(property.toString(state.requireProperty(property)))
}

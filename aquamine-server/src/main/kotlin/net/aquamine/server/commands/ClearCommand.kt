package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.entities.EntityArgumentType
import net.aquamine.server.command.arguments.item.ItemStackPredicate
import net.aquamine.server.command.arguments.item.ItemStackPredicateArgument
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.locale.CommandMessages
import net.aquamine.server.packet.out.play.PacketOutSetContainerContent
import java.util.function.Consumer

object ClearCommand {

    private const val TARGETS = "targets"
    private const val ITEM = "item"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("clear") {
            requiresPermission(KryptonPermission.CLEAR)
            runs { clear(it.source, listOf(it.source.getPlayerOrError()), { true }, -1) }
            argument(TARGETS, EntityArgumentType.players()) {
                runs { clear(it.source, EntityArgumentType.getPlayers(it, TARGETS), { true }, -1) }
                argument(ITEM, ItemStackPredicateArgument) {
                    runs { clear(it.source, EntityArgumentType.getPlayers(it, TARGETS), it.getArgument(ITEM), -1) }
                }
            }
        })
    }

    //-1 means that everything should be cleared (there is no limit)
    @JvmStatic
    private fun clear(source: CommandSourceStack, targets: List<KryptonPlayer>, predicate: ItemStackPredicate, maxCount: Int) {
        val amount = if (maxCount == -1) "all" else maxCount.toString()
        if (targets.size == 1) {
            val target = targets.get(0)
            clear(target, predicate, maxCount)
            CommandMessages.CLEAR_SINGLE.sendSuccess(source, amount, target.displayName, true)
            target.connection.send(PacketOutSetContainerContent.fromPlayerInventory(target.inventory))
        } else {
            targets.forEach { target ->
                target.inventory.items.forEachIndexed { index, item ->
                    if (predicate.test(item)) target.inventory.setItem(index, KryptonItemStack.EMPTY)
                }
                target.connection.send(PacketOutSetContainerContent.fromPlayerInventory(target.inventory))
            }
            CommandMessages.CLEAR_MULTIPLE.sendSuccess(source, amount, targets.size, true)
        }
    }

    @JvmStatic
    private fun clear(target: KryptonPlayer, predicate: ItemStackPredicate, maxCount: Int) {
        val inventory = target.inventory
        var remaining = maxCount
        // Clear inventory items
        remaining = clearList(predicate, remaining, inventory.items)
        if (remaining == 0) return
        // Clear armor items
        remaining = clearList(predicate, remaining, inventory.armor)
        if (remaining == 0) return
        // Clear crafting items
        remaining = clearList(predicate, remaining, inventory.crafting)
        if (remaining == 0) return
        // Clear offhand
        clearItem(predicate, remaining, inventory.offHand) { inventory.offHand = it }
    }

    @JvmStatic
    private fun clearList(predicate: ItemStackPredicate, originalRemaining: Int, items: MutableList<KryptonItemStack>): Int {
        var remaining = originalRemaining
        items.forEachIndexed { index, item ->
            val newRemaining = clearItem(predicate, remaining, item) { items.set(index, it) }
            if (newRemaining == -1) return@forEachIndexed
            if (newRemaining == 0) return 0
            remaining = newRemaining
        }
        return remaining
    }

    @JvmStatic
    private fun clearItem(predicate: ItemStackPredicate, remaining: Int, item: KryptonItemStack, setItem: Consumer<KryptonItemStack>): Int {
        if (!predicate.test(item)) return -1
        return when {
            remaining == -1 -> {
                setItem.accept(KryptonItemStack.EMPTY)
                remaining
            }
            remaining > item.amount -> {
                setItem.accept(KryptonItemStack.EMPTY)
                remaining - item.amount
            }
            else -> {
                setItem.accept(item.shrink(remaining))
                0
            }
        }
    }
}

package net.aquamine.server.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.IntegerArgumentType
import net.kyori.adventure.sound.Sound
import net.aquamine.api.command.argument
import net.aquamine.api.command.literalCommand
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.command.arguments.entities.EntityArgumentType
import net.aquamine.server.command.arguments.item.ItemStackArgument
import net.aquamine.server.command.arguments.item.ItemStackArgumentType
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.packet.out.play.PacketOutSetContainerContent

object GiveCommand {

    // These constants come from vanilla
    private const val PICKUP_VOLUME = 0.2F
    private const val PICKUP_PITCH = 2F

    private const val TARGETS = "targets"
    private const val ITEM = "item"
    private const val COUNT = "count"

    @JvmStatic
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(literalCommand("give") {
            requiresPermission(KryptonPermission.GIVE)
            argument(TARGETS, EntityArgumentType.players()) {
                argument(ITEM, ItemStackArgumentType) {
                    runs { give(EntityArgumentType.getPlayers(it, TARGETS), ItemStackArgumentType.get(it, ITEM), 1) }
                    argument(COUNT, IntegerArgumentType.integer(1)) {
                        runs {
                            give(EntityArgumentType.getPlayers(it, TARGETS), ItemStackArgumentType.get(it, ITEM), it.getArgument(COUNT))
                        }
                    }
                }
            }
        })
    }

    @JvmStatic
    private fun give(targets: List<KryptonPlayer>, item: ItemStackArgument, count: Int) {
        targets.forEach { target ->
            item.createItemStacks(count).forEach(target.inventory::addItem)
            target.playSound(Sound.sound(SoundEvents.ITEM_PICKUP, Sound.Source.PLAYER, PICKUP_VOLUME, PICKUP_PITCH))
            target.connection.send(PacketOutSetContainerContent.fromPlayerInventory(target.inventory))
        }
    }
}

package net.aquamine.server.entity.player

import net.aquamine.api.entity.player.CooldownTracker
import net.aquamine.api.item.ItemType
import net.aquamine.server.event.player.AquaPlayerItemCooldownEvent
import net.aquamine.server.item.downcast
import net.aquamine.server.packet.out.play.PacketOutSetCooldown
import net.aquamine.server.util.math.Maths
import java.util.concurrent.ConcurrentHashMap

class AquaCooldownTracker(private val player: AquaPlayer) : CooldownTracker {

    private val cooldowns = ConcurrentHashMap<ItemType, Cooldown>()
    private var tickCount = 0

    fun tick() {
        tickCount++
        if (cooldowns.isEmpty()) return

        val iterator = cooldowns.entries.iterator() // so we can use remove
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.value.endTime > tickCount) continue

            iterator.remove()
            onCooldownEnded(entry.key)
        }
    }

    override fun hasCooldown(item: ItemType): Boolean = getCooldownPercentage(item) > 0F

    override fun getCooldown(item: ItemType): Int {
        val instance = cooldowns.get(item) ?: return -1
        return instance.endTime - tickCount
    }

    override fun getCooldownPercentage(item: ItemType): Float {
        val instance = cooldowns.get(item) ?: return 0F
        val totalCooldownTime = (instance.endTime - instance.startTime).toFloat()
        val remainingCooldownTime = (instance.endTime - tickCount).toFloat()
        return Maths.clamp(remainingCooldownTime / totalCooldownTime, 0F, 1F)
    }

    override fun setCooldown(item: ItemType, ticks: Int) {
        if (ticks < 0) return

        val event = player.server.eventNode.fire(AquaPlayerItemCooldownEvent(player, item, ticks))
        if (!event.isAllowed()) return

        val result = event.result
        cooldowns.put(item, Cooldown(tickCount, result?.cooldown ?: ticks))
        onCooldownStarted(item, ticks)
    }

    override fun resetCooldown(item: ItemType) {
        setCooldown(item, 0)
    }

    private fun onCooldownStarted(type: ItemType, ticks: Int) {
        player.connection.send(PacketOutSetCooldown(type.downcast(), ticks))
    }

    private fun onCooldownEnded(type: ItemType) {
        player.connection.send(PacketOutSetCooldown(type.downcast(), 0))
    }

    @JvmRecord
    data class Cooldown(val startTime: Int, val endTime: Int)
}

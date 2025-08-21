package net.aquamine.spark.task

import net.aquamine.api.Server
import net.aquamine.api.entity.player.Player
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import java.lang.management.ManagementFactory

// Modified ram bar from PurpurMC
class RamBarTask(
    server: Server,
    private val title: String = "<gray>Ram<yellow>:</yellow> <used>/<xmx> <gray>(<percent>)",
    private val overlay: BossBar.Overlay = BossBar.Overlay.NOTCHED_20,
    private val barGoodColor: BossBar.Color = BossBar.Color.GREEN,
    private val barMediumColor: BossBar.Color = BossBar.Color.YELLOW,
    private val barLowColor: BossBar.Color = BossBar.Color.RED,
    private val textGoodColor: String = "<gradient:#55ff55:#00aa00><text></gradient>",
    private val textMediumColor: String = "<gradient:#ffff55:#ffaa00><text></gradient>",
    private val textLowColor: String = "<gradient:#ff5555:#aa0000><text></gradient>"
) : BossBarTask(server) {

    private var allocated = 0L
    private var used = 0L
    private var xmx = 0L
    private var xms = 0L
    private var bossBarProgress = 0f
    private var tick = 0

    private val bossBarColor: BossBar.Color
        get() = when (bossBarProgress) {
            in 0f..0.5f -> barGoodColor
            in 0.5f..0.75f -> barMediumColor
            else -> barLowColor
        }

    override fun createBossBar(): BossBar =
        BossBar.bossBar(Component.empty(), 0.0f, barGoodColor, overlay)

    override fun updateBossBar(bossBar: BossBar, player: Player) {
        bossBar.progress(bossBarProgress)
        bossBar.color(bossBarColor)
        bossBar.name(
            miniMessage.deserialize(
                title,
                Placeholder.component("allocated", formatSize(allocated)),
                Placeholder.component("used", formatSize(used)),
                Placeholder.component("xmx", formatSize(xmx)),
                Placeholder.component("xms", formatSize(xms)),
                Placeholder.unparsed("percent", "${(bossBarProgress * 100).toInt()}%")
            )
        )
    }

    override fun run() {
        if (tick++ < UPDATE_INTERVAL) return
        tick = 0

        ManagementFactory.getMemoryMXBean().heapMemoryUsage.let { heap ->
            allocated = heap.committed
            used = heap.used
            xmx = heap.max
            xms = heap.init
        }

        bossBarProgress = (used.toFloat() / xmx).coerceIn(0.0f, 1.0f)
        super.run()
    }

    private fun formatSize(bytes: Long): Component {
        val color = when (bossBarProgress) {
            in 0f..0.60f -> textGoodColor
            in 0.60f..0.85f -> textMediumColor
            else -> textLowColor
        }

        val value = if (bytes < 1024) {
            "${bytes}B"
        } else {
            val unitIndex = (63 - bytes.countLeadingZeroBits()) / 10
            String.format("%.1f%s", bytes.toDouble() / (1L shl (unitIndex * 10)), "BKMGTPE"[unitIndex])
        }

        return miniMessage.deserialize(color, Placeholder.unparsed("text", value))
    }

    companion object {
        private val miniMessage = MiniMessage.miniMessage()

        private const val UPDATE_INTERVAL = 20
    }
}

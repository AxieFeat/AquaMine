package net.aquamine.spark.task

import me.lucko.spark.api.Spark
import me.lucko.spark.api.statistic.StatisticWindow
import net.aquamine.api.Server
import net.aquamine.api.entity.player.Player
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder

class CpuBarTask(
    server: Server,
    private val spark: Spark,
    private val title: String = "<gray>CPU<yellow>:</yellow> <process>% (System <system>%)",
    private val overlay: BossBar.Overlay = BossBar.Overlay.NOTCHED_20,
    private val progressFillMode: FillMode = FillMode.PROCESS,
    private val barGoodColor: BossBar.Color = BossBar.Color.GREEN,
    private val barMediumColor: BossBar.Color = BossBar.Color.YELLOW,
    private val barLowColor: BossBar.Color = BossBar.Color.RED,
    private val textGoodColor: String = "<gradient:#55ff55:#00aa00><text></gradient>",
    private val textMediumColor: String = "<gradient:#ffff55:#ffaa00><text></gradient>",
    private val textLowColor: String = "<gradient:#ff5555:#aa0000><text></gradient>"
) : BossBarTask(server) {

    private var process = 0.0
    private var system = 0.0
    private var tick = 0

    private val bossBarProgress: Float
        get() = when (progressFillMode) {
            FillMode.PROCESS -> process / 100f
            FillMode.SYSTEM -> system / 100f
        }.toFloat().coerceIn(0f, 1f)

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
                Placeholder.component("process", color(FillMode.PROCESS, process)),
                Placeholder.component("system",  color(FillMode.SYSTEM, system)),
            )
        )
    }

    override fun run() {
        if (tick++ < UPDATE_INTERVAL) return
        tick = 0

        process = spark.cpuProcess().poll(StatisticWindow.CpuUsage.SECONDS_10) * 100
        system = spark.cpuSystem().poll(StatisticWindow.CpuUsage.SECONDS_10) * 100

        super.run()
    }

    private fun color(mode: FillMode, value: Double): Component {
        val color = when {
            isGood(mode) -> textGoodColor
            isMedium(mode) -> textMediumColor
            else -> textLowColor
        }
        return miniMessage.deserialize(color, Placeholder.parsed("text", String.format("%.2f", value)))
    }

    private fun isGood(mode: FillMode): Boolean = when (mode) {
        FillMode.PROCESS -> process < 25
        FillMode.SYSTEM -> system < 50
    }

    private fun isMedium(mode: FillMode): Boolean = when (mode) {
        FillMode.PROCESS -> process < 50
        FillMode.SYSTEM -> system < 75
    }

    enum class FillMode { PROCESS, SYSTEM }

    companion object {
        private val miniMessage = MiniMessage.miniMessage()

        private const val UPDATE_INTERVAL = 20
    }
}

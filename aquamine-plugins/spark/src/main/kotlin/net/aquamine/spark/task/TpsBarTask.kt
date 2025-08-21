package net.aquamine.spark.task

import me.lucko.spark.api.Spark
import me.lucko.spark.api.statistic.StatisticWindow
import net.aquamine.api.Server
import net.aquamine.api.entity.player.Player
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder

// Modified tps bar from PurpurMC
class TpsBarTask(
    server: Server,
    private val spark: Spark,
    private val title: String = "<gray>TPS<yellow>:</yellow> <tps> <gray>MSPT<yellow>:</yellow> <mspt> <gray>Ping<yellow>:</yellow> <ping>ms",
    private val overlay: BossBar.Overlay = BossBar.Overlay.NOTCHED_20,
    private val progressFillMode: FillMode = FillMode.MSPT,
    private val barGoodColor: BossBar.Color = BossBar.Color.GREEN,
    private val barMediumColor: BossBar.Color = BossBar.Color.YELLOW,
    private val barLowColor: BossBar.Color = BossBar.Color.RED,
    private val textGoodColor: String = "<gradient:#55ff55:#00aa00><text></gradient>",
    private val textMediumColor: String = "<gradient:#ffff55:#ffaa00><text></gradient>",
    private val textLowColor: String = "<gradient:#ff5555:#aa0000><text></gradient>"
) : BossBarTask(server) {

    private var tps = 20.0
    private var mspt = 0.0
    private var tick = 0

    private val bossBarProgress: Float
        get() = when (progressFillMode) {
            FillMode.MSPT -> mspt.toFloat() / 50f
            FillMode.TPS -> tps.toFloat() / 20f
            else -> 0f
        }.coerceIn(0f, 1f)

    private val bossBarColor: BossBar.Color
        get() = when {
            isGood(progressFillMode) -> barGoodColor
            isMedium(progressFillMode) -> barMediumColor
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
                Placeholder.component("tps", metricColor(FillMode.TPS, tps, "%.2f")),
                Placeholder.component("mspt", metricColor(FillMode.MSPT, mspt, "%.2f")),
                Placeholder.component("ping", metricColor(FillMode.PING, player.ping.toDouble(), "%s"))
            )
        )
    }

    override fun run() {
        if (tick++ < UPDATE_INTERVAL) return
        tick = 0

        tps = spark.tps()?.poll(StatisticWindow.TicksPerSecond.SECONDS_5) ?: 0.0
        mspt = spark.mspt()?.poll(StatisticWindow.MillisPerTick.SECONDS_10)?.mean() ?: 0.0

        super.run()
    }

    private fun metricColor(mode: FillMode, value: Double, format: String): Component {
        val color = when {
            isGood(mode, value) -> textGoodColor
            isMedium(mode, value) -> textMediumColor
            else -> textLowColor
        }
        return miniMessage.deserialize(color, Placeholder.parsed("text", String.format(format, value)))
    }

    private fun isGood(mode: FillMode, v: Double = 0.0): Boolean = when (mode) {
        FillMode.MSPT -> mspt < 40
        FillMode.TPS -> tps >= 19
        FillMode.PING -> v < 100
    }

    private fun isMedium(mode: FillMode, v: Double = 0.0): Boolean = when (mode) {
        FillMode.MSPT -> mspt < 50
        FillMode.TPS -> tps >= 15
        FillMode.PING -> v < 200
    }

    enum class FillMode { TPS, MSPT, PING }

    companion object {
        private val miniMessage = MiniMessage.miniMessage()

        private const val UPDATE_INTERVAL = 20
    }
}

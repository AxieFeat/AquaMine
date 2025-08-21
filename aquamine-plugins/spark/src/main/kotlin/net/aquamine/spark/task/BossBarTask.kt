package net.aquamine.spark.task

import net.aquamine.api.Server
import net.aquamine.api.entity.player.Player
import net.aquamine.api.scheduling.Task
import net.aquamine.api.scheduling.TaskTime
import net.kyori.adventure.bossbar.BossBar
import java.util.UUID

abstract class BossBarTask(
    private val server: Server
) : Runnable {

    private val bossBars = mutableMapOf<UUID, BossBar>()
    private var task: Task? = null

    abstract fun createBossBar(): BossBar

    abstract fun updateBossBar(bossBar: BossBar, player: Player)

    override fun run() {
        val iter = bossBars.entries.iterator()
        while (iter.hasNext()) {
            val entry = iter.next()
            val player = server.getPlayer(entry.key)

            if (player == null) {
                iter.remove()
                continue
            }

            updateBossBar(entry.value, player)
        }
    }

    fun removePlayer(player: Player): Boolean =
        bossBars.remove(player.uuid)?.let { bossBar ->
            player.hideBossBar(bossBar)
            true
        } ?: false

    fun addPlayer(player: Player) {
        removePlayer(player)
        createBossBar().also { bossBar ->
            bossBars[player.uuid] = bossBar
            updateBossBar(bossBar, player)
            player.showBossBar(bossBar)
        }
    }

    fun hasPlayer(uuid: UUID) = uuid in bossBars

    fun togglePlayer(player: Player): Boolean =
        if (removePlayer(player)) false else {
            addPlayer(player)
            true
        }

    fun start() {
        stop()
        task = server.scheduler.buildTask(this)
            .delay(TaskTime.ticks(1))
            .period(TaskTime.ticks(1))
            .async()
            .schedule()
    }

    fun stop() {
        task?.cancel()
        bossBars.clear()
    }
}

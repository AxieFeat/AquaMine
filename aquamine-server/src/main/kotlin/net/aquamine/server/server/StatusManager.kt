package net.aquamine.server.server

import net.kyori.adventure.text.Component
import net.aquamine.server.packet.out.status.ServerStatus
import net.aquamine.server.util.math.Maths
import net.aquamine.server.util.random.RandomSource
import kotlin.math.min

class StatusManager(private val playerManager: PlayerManager, motd: Component, maxPlayers: Int) {

    private val random = RandomSource.create()
    private val status = ServerStatus(motd, ServerStatus.Players(maxPlayers, playerManager.players().size), null)
    private var statusInvalidated = false
    private var statusInvalidatedTime = 0L
    private var lastStatus = 0L

    fun status(): ServerStatus = status

    fun tick(time: Long) {
        if (statusInvalidated && time - statusInvalidatedTime > WAIT_AFTER_INVALID_STATUS_TIME || time - lastStatus >= UPDATE_STATUS_INTERVAL) {
            updateStatus(time)
        }
    }

    private fun updateStatus(time: Long) {
        lastStatus = time
        statusInvalidated = false
        statusInvalidatedTime = 0L
        val playersOnline = playerManager.players().size
        status.players.online = playersOnline
        val sampleSize = min(playersOnline, MAXIMUM_SAMPLED_PLAYERS)
        val playerOffset = Maths.nextInt(random, 0, playersOnline - sampleSize)
        val sample = Array(sampleSize) { playerManager.players()[it + playerOffset].profile }.apply { shuffle() }
        status.players.sample = sample
    }

    fun invalidateStatus() {
        if (statusInvalidated) return
        statusInvalidated = true
        statusInvalidatedTime = System.currentTimeMillis()
        lastStatus = 0L
    }

    companion object {

        private const val UPDATE_STATUS_INTERVAL = 5000L
        private const val WAIT_AFTER_INVALID_STATUS_TIME = 1000L
        private const val MAXIMUM_SAMPLED_PLAYERS = 12
    }
}

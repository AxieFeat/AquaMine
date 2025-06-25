package net.aquamine.server.entity.components

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.PlayerChangeGameModeEvent
import net.aquamine.api.world.GameMode
import net.aquamine.server.entity.system.PlayerGameModeSystem

interface GameModePlayer : Player {

    val gameModeSystem: PlayerGameModeSystem
    override var gameMode: GameMode
        get() = gameModeSystem.gameMode()
        set(value) {
            updateGameMode(value)
        }

    fun updateGameMode(mode: GameMode): PlayerChangeGameModeEvent?
}

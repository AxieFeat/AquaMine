package net.aquamine.server.event.player

import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.player.PlayerChangeGameModeEvent
import net.aquamine.api.event.type.AbstractDeniableEventWithResult
import net.aquamine.api.world.GameMode

class KryptonPlayerChangeGameModeEvent(
    override val player: Player,
    override val oldGameMode: GameMode,
    override val newGameMode: GameMode
) : AbstractDeniableEventWithResult<PlayerChangeGameModeEvent.Result>(), PlayerChangeGameModeEvent

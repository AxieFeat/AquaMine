package net.aquamine.api.event.player

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.event.type.DeniableEventWithResult
import net.aquamine.api.event.type.PlayerEvent
import net.aquamine.api.world.GameMode

/**
 * Called when the given [player] changes game mode.
 */
interface PlayerChangeGameModeEvent : PlayerEvent, DeniableEventWithResult<PlayerChangeGameModeEvent.Result> {

    /**
     * The game mode that the player was in before the change.
     */
    val oldGameMode: GameMode

    /**
     * The game mode that the player will be in after the change.
     */
    val newGameMode: GameMode

    /**
     * The result of an attempt to change a player's game mode.
     *
     * This allows plugins to completely overwrite the game mode that the
     * player is switching to. For example, this could be used to force players
     * that attempt to switch to creative mode in to survival.
     *
     * @property newGameMode The game mode to change the player to.
     */
    @JvmRecord
    @ImmutableType
    data class Result(val newGameMode: GameMode)
}

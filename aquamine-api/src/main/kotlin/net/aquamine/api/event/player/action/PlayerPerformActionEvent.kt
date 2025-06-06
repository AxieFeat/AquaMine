package net.aquamine.api.event.player.action

import net.aquamine.api.event.type.DeniableEvent
import net.aquamine.api.event.type.PlayerEvent

/**
 * A type of event that is called when a player performs an action.
 */
interface PlayerPerformActionEvent : PlayerEvent, DeniableEvent

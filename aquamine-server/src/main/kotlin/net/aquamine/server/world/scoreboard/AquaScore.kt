package net.aquamine.server.world.scoreboard

import net.kyori.adventure.text.Component
import net.aquamine.api.scoreboard.Objective
import net.aquamine.api.scoreboard.Score

class AquaScore(override val scoreboard: AquaScoreboard, override val objective: Objective, override val member: Component) : Score {

    override var score: Int = 0
        set(value) {
            val old = field
            field = value
            if (old != value || forceUpdate) {
                forceUpdate = false
                scoreboard.onScoreUpdated(this)
            }
        }
    override var isLocked: Boolean = true
    private var forceUpdate = true
}

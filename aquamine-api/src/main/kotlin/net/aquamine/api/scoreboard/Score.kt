package net.aquamine.api.scoreboard

import net.kyori.adventure.text.Component

/**
 * The score of a member of a team for a specific objective.
 */
interface Score : ScoreboardBound {

    /**
     * The name of the member whose score this is.
     */
    val member: Component

    /**
     * The objective this score is registered to.
     */
    val objective: Objective

    /**
     * The underlying value of this score.
     */
    var score: Int

    /**
     * If this score is locked, meaning it cannot be changed.
     */
    var isLocked: Boolean
}

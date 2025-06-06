package net.aquamine.api.scoreboard

import net.kyori.adventure.text.Component

/**
 * Something that may be a member of a team, having a meaningful representation
 * on a team.
 */
interface TeamMember {

    /**
     * If the member is currently part of a team, the team the member is part
     * of.
     */
    val team: Team?

    /**
     * How this member will be represented in a team.
     *
     * For entities, this is generally a [text component][Component.text]
     * containing their UUID, except the player, for which it is
     * their username.
     */
    val teamRepresentation: Component
}

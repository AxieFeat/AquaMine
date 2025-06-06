package net.aquamine.api.scoreboard

/**
 * A rule for collision between members of a team.
 */
enum class CollisionRule {

    ALWAYS,
    NEVER,
    PUSH_OTHER_TEAMS,
    PUSH_OWN_TEAM
}

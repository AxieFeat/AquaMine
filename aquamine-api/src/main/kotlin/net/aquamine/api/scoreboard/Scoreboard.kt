package net.aquamine.api.scoreboard

import net.aquamine.annotations.TypeFactory
import net.kyori.adventure.text.Component
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import net.aquamine.api.scoreboard.criteria.Criterion

/**
 * A [Scoreboard] is a method of keeping track of scores.
 * These are primarily for use in minigames.
 */
interface Scoreboard {

    /**
     * All objectives registered on this scoreboard.
     */
    val objectives: Collection<Objective>

    /**
     * All teams tracked by this scoreboard.
     */
    val teams: Collection<Team>

    /**
     * Gets the objective with the given [name], or returns null if there are
     * no objectives registered with this scoreboard with the given [name].
     *
     * @param name the name of the registered objective
     * @return the registered objective, or null if not present
     */
    fun getObjective(name: String): Objective?

    /**
     * Gets the objective in the given [slot], or returns null if there are no
     * objectives registered with this scoreboard in the given [slot].
     *
     * @param slot the slot the objective may be in
     * @return the registered objective, or null if not present
     */
    fun getObjective(slot: DisplaySlot): Objective?

    /**
     * Creates a new builder for building an objective that will be registered
     * to this scoreboard.
     *
     * @return a new objective builder
     */
    @Contract("_ -> new", pure = true)
    fun createObjectiveBuilder(): Objective.Builder

    /**
     * Creates a new objective with the given [name], [criterion],
     * [displayName], and [renderType], and adds it to this scoreboard.
     *
     * @param name the name
     * @param criterion the criterion
     * @param displayName the display name
     * @param renderType the render type
     * @return the created objective
     * @throws IllegalArgumentException if an objective with the given name is
     * already registered with this scoreboard
     */
    fun addObjective(name: String, criterion: Criterion, displayName: Component, renderType: ObjectiveRenderType): Objective

    /**
     * Removes the given [objective] from this scoreboard's list of registered
     * objectives.
     *
     * @param objective the objective
     */
    fun removeObjective(objective: Objective)

    /**
     * Updates the objective in the given [slot] to be the given [objective],
     * clearing the objective at the given [slot] if the given [objective] is
     * null.
     *
     * @param objective the objective, null to clear
     * @param slot the slot
     */
    fun updateSlot(objective: Objective?, slot: DisplaySlot)

    /**
     * Clears any objective in the given [slot].
     *
     * @param slot the slot to clear
     */
    fun clearSlot(slot: DisplaySlot)

    /**
     * Gets the team with the given [name] if there is one registered with this
     * scoreboard, or returns null if there is not.
     *
     * @param name the name of the team
     * @return the team with the name, or null if not present
     */
    fun getTeam(name: String): Team?

    /**
     * Gets the team that the given [member] is in on this scoreboard, or
     * returns null if there is no team with the given [member] in it.
     *
     * @param member the member
     * @return the team of the member, or null if not present
     */
    fun getMemberTeam(member: Component): Team?

    /**
     * Creates a new builder for building a team with the given [name] that
     * will be registered to this scoreboard.
     *
     * @param name the name of the team
     * @return a new team builder
     */
    fun createTeamBuilder(name: String): Team.Builder

    /**
     * Creates a new team with the given [name] and adds it to the list of
     * registered teams for this scoreboard.
     *
     * @param name the name of the team
     * @throws IllegalArgumentException if there is a registered team with the
     * same name as the given name
     */
    fun addTeam(name: String): Team

    /**
     * Gets the existing team with the given [name], or creates a new team with
     * the given [name] and adds it to the list of registered teams for this
     * scoreboard.
     *
     * @param name the name of the team
     * @return the existing team, or the new team if there was no existing one
     */
    fun getOrAddTeam(name: String): Team

    /**
     * Removes the given [team] from this scoreboard's list of registered
     * teams.
     *
     * @param team the team
     */
    fun removeTeam(team: Team)

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun create(): Scoreboard
    }

    companion object {

        /**
         * Creates a new blank scoreboard, with no objectives or teams.
         *
         * @return a new blank scoreboard
         */
        @JvmStatic
        fun create(): Scoreboard = AquaMine.factory<Factory>().create()
    }
}

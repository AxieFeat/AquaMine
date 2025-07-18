package net.aquamine.api.scoreboard

import net.kyori.adventure.text.Component
import org.jetbrains.annotations.Contract
import net.aquamine.api.scoreboard.criteria.Criterion

/**
 * An objective for a scoreboard that has optional criteria that must be met,
 * and information about what it's called and how it should be rendered.
 */
interface Objective : ScoreboardBound {

    /**
     * The name of this objective.
     */
    val name: String

    /**
     * The name that is displayed on the scoreboard to clients.
     */
    var displayName: Component

    /**
     * The criterion that must be met for this objective.
     */
    val criterion: Criterion

    /**
     * The setting for how this objective should be displayed on the
     * scoreboard.
     */
    var renderType: ObjectiveRenderType

    /**
     * All the scores on this objective.
     */
    val scores: Collection<Score>

    /**
     * Gets the score for the given [member], if the member has a score.
     *
     * @param member The member.
     *
     * @return The score, or null if not present.
     */
    fun getScore(member: Component): Score?

    /**
     * Gets the score for the given [member], or creates a new score if the
     * member does not have a score.
     *
     * @param member The member.
     *
     * @return The score.
     */
    fun getOrCreateScore(member: Component): Score

    /**
     * Removes the score for the given [member], if the member has a score.
     *
     * @param member The member.
     *
     * @return Whether the score was removed.
     */
    fun removeScore(member: Component): Boolean

    /**
     * Removes the given [score], if it exists on this objective.
     *
     * @param score The score to remove.
     *
     * @return Whether the score was removed.
     */
    fun removeScore(score: Score): Boolean

    /**
     * A builder for objectives.
     */
    interface Builder {

        /**
         * Sets the name of the objective to the given [name].
         *
         * @param name The name.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun name(name: String): Builder

        /**
         * Sets the criterion for the objective to the given [criterion].
         *
         * @param criterion The criterion.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun criterion(criterion: Criterion): Builder

        /**
         * Sets the display name of the objective to the given [name].
         *
         * @param name The display name.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun displayName(name: Component): Builder

        /**
         * Sets the render type for the objective to the given [type].
         *
         * @param type The render type.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun renderType(type: ObjectiveRenderType): Builder

        /**
         * Builds the objective and adds it to the scoreboard.
         *
         * @return The built objective.
         */
        @Contract("-> new", pure = true)
        fun buildAndRegister(): Objective

    }
}

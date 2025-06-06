package net.aquamine.api.scoreboard

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.jetbrains.annotations.Contract

/**
 * A team on a [Scoreboard].
 *
 * Teams are groups of entities that have a name, prefix, suffix, color,
 * and a specific set of rules they follow.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface Team : ScoreboardBound {

    /**
     * The name of this team.
     */
    val name: String

    /**
     * The name that is displayed on the scoreboard to clients.
     */
    var displayName: Component

    /**
     * The prefix prepended to the display name of members of this team.
     */
    var prefix: Component

    /**
     * The suffix appended to the display name of members of this team.
     */
    var suffix: Component

    /**
     * The color of the team that is displayed on the scoreboard.
     */
    var color: NamedTextColor

    /**
     * If this team allows members to attack each other.
     *
     * * Well, that's not very nice, is it! They're there to help you...*
     */
    @get:JvmName("allowFriendlyFire")
    var allowFriendlyFire: Boolean

    /**
     * If this team allows members to see members that are invisible.
     */
    @get:JvmName("canSeeInvisibleMembers")
    var canSeeInvisibleMembers: Boolean

    /**
     * The visibility of name tags in the team.
     */
    var nameTagVisibility: Visibility

    /**
     * The visibility of death messages in the team.
     */
    var deathMessageVisibility: Visibility

    /**
     * The collision rule for the team.
     */
    var collisionRule: CollisionRule

    /**
     * All the members in this team.
     */
    val members: List<Component>

    /**
     * Adds a member to the list of members in this team.
     *
     * @param member The member to add.
     *
     * @return Whether the member was added.
     */
    fun addMember(member: Component): Boolean

    /**
     * Removes a member from the list of members in this team.
     *
     * @return Whether the member was removed.
     */
    fun removeMember(member: Component): Boolean

    /**
     * Formats the given [name] according to the options in this team.
     *
     * The format returned by this function should be [prefix] [name] [suffix],
     * excluding the spaces in between the terms, and it will be colored with
     * the [team color][color].
     *
     * For example, if the prefix was "Aqua", the name was "is", and the
     * suffix was "cool", the full name would be "Aquaiscool".
     *
     * @param name The name to format.
     *
     * @return The formatted name.
     */
    fun formatName(name: Component): Component

    /**
     * A builder for building teams.
     */
    interface Builder {

        /**
         * Sets the display name for the team to the given [name].
         *
         * @param name The display name.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun displayName(name: Component): Builder

        /**
         * Sets the prefix for the team to the given [prefix].
         *
         * @param prefix The prefix.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun prefix(prefix: Component): Builder

        /**
         * Sets the suffix for the team to the given [suffix].
         *
         * @param suffix The suffix.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun suffix(suffix: Component): Builder

        /**
         * Sets whether team members are allowed to attack each other.
         *
         * @param value The value for the setting.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun friendlyFire(value: Boolean): Builder

        /**
         * Allows all team members to attack each other.
         *
         * @return This builder.
         */
        @Contract("-> this", mutates = "this")
        fun allowFriendlyFire(): Builder = friendlyFire(true)

        /**
         * Sets whether team members can see invisible team members.
         *
         * @param value The value for the setting.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun canSeeInvisibleMembers(value: Boolean): Builder

        /**
         * Allows all team members to see invisible team members.
         *
         * @return This builder.
         */
        @Contract("-> this", mutates = "this")
        fun seeInvisibleMembers(): Builder = canSeeInvisibleMembers(true)

        /**
         * Sets the name tag visibility for the team to the given [visibility].
         *
         * @param visibility The visibility.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun nameTagVisibility(visibility: Visibility): Builder

        /**
         * Sets the death message visibility for the team to the given
         * [visibility].
         *
         * @param visibility The visibility.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun deathMessageVisibility(visibility: Visibility): Builder

        /**
         * Sets the team color to the given [color].
         *
         * @param color The color.
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun color(color: NamedTextColor): Builder

        /**
         * Sets the collision rule for the team to the given [rule].
         *
         * @param rule The collision rule.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun collisionRule(rule: CollisionRule): Builder

        /**
         * Adds the given [member] to the team's list of members.
         *
         * @param member The member.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun addMember(member: Component): Builder

        /**
         * Builds the team and adds it to the scoreboard.
         *
         * @return The built team.
         */
        @Contract("-> new", pure = true)
        fun buildAndRegister(): Team
    }
}

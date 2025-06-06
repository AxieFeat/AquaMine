package net.aquamine.api.world.rule

/**
 * A holder of game rules. Used to get and set game rule values.
 */
interface GameRuleHolder {

    /**
     * Gets the value of the given [rule] in this holder.
     *
     * @param V The value type.
     * @param rule The rule.
     *
     * @return The value.
     */
    fun <V> getGameRule(rule: GameRule<V>): V

    /**
     * Sets the value of the given [rule] to the given [value] in this holder.
     *
     * @param V The value type.
     * @param rule The rule.
     * @param value The new value.
     */
    fun <V> setGameRule(rule: GameRule<V>, value: V & Any)
}

package net.aquamine.api.entity

/**
 * A mob that has an age, meaning it also has a baby form.
 */
interface Ageable : Mob {

    /**
     * The age of this ageable mob.
     */
    val age: Int

    /**
     * If this ageable mob is a baby.
     */
    var isBaby: Boolean

    /**
     * Whether this ageable mob can naturally breed with others of its kind.
     *
     * @return true if this mob can breed naturally
     */
    fun canBreedNaturally(): Boolean

    /**
     * Increases the age of this mob by the given [amount].
     *
     * @param amount the amount
     */
    fun increaseAge(amount: Int)
}

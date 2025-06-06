package net.aquamine.api.entity.animal

/**
 * A chicken.
 */
interface Chicken : Animal {

    /**
     * If this chicken is a jockey.
     */
    var isJockey: Boolean

    /**
     * The time remaining until the next egg drops from this chicken.
     */
    var eggCooldownTime: Int
}

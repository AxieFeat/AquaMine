package net.aquamine.api.entity.ambient

/**
 * A bat.
 */
interface Bat : AmbientCreature {

    /**
     * If this bat is currently resting (hanging from the ceiling).
     */
    var isResting: Boolean
}

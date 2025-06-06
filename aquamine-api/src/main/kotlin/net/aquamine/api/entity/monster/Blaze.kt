package net.aquamine.api.entity.monster

/**
 * A flying monster that shoots fireballs.
 */
interface Blaze : Monster {

    /**
     * Whether this blaze is currently on fire.
     *
     * This usually happens when the blaze is ready to attack.
     */
    override var isOnFire: Boolean
}

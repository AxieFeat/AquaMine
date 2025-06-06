package net.aquamine.api.entity.animal

/**
 * A goat.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface Goat : Animal {

    /**
     * If this goat is a screaming goat, meaning it can scream.
     */
    @get:JvmName("canScream")
    var canScream: Boolean
}

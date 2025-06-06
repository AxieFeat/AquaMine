package net.aquamine.api.entity.animal

import net.aquamine.api.util.Vec3i

/**
 * A bee.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface Bee : Animal {

    /**
     * If this bee is currently angry at a player.
     */
    var isAngry: Boolean

    /**
     * If this bee has stung a player.
     */
    @get:JvmName("hasStung")
    var hasStung: Boolean

    /**
     * If this bee has nectar to deposit back to the hive.
     */
    @get:JvmName("hasNectar")
    var hasNectar: Boolean

    /**
     * The number of ticks this bee can't enter its hive for.
     */
    var cannotEnterHiveTicks: Int

    /**
     * The location of this bee's hive.
     */
    var hive: Vec3i?

    /**
     * The location of this bee's flower.
     */
    var flower: Vec3i?
}

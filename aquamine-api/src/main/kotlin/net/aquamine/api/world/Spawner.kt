package net.aquamine.api.world

/**
 * An object that can spawn entities.
 */
interface Spawner {

    /**
     * The amount of time, in ticks, until the next entity is spawned.
     */
    var spawnDelay: Int

    /**
     * The minimum amount of time, in ticks, between entities being spawned by
     * the spawner.
     */
    var minimumSpawnDelay: Int

    /**
     * The maximum amount of time, in ticks, between entities being spawned by
     * the spawner.
     */
    var maximumSpawnDelay: Int

    /**
     * The number of entities that have been successfully spawned from this
     * spawner.
     */
    var spawnCount: Int

    /**
     * The maximum range that an entity can be spawned from the spawner.
     */
    var spawnRange: Double

    /**
     * The minimum range a player must be in proximity of the spawner for the
     * spawner to attempt to spawn entities.
     */
    var requiredPlayerRange: Double
}

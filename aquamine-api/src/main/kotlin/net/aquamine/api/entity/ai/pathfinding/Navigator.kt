package net.aquamine.api.entity.ai.pathfinding

import net.aquamine.api.entity.Mob
import net.aquamine.api.util.Position
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i

/**
 * A navigator for pathfinding an entity to a target.
 */
interface Navigator {

    /**
     * The entity that is navigating with this navigator.
     */
    val entity: Mob

    /**
     * The current target position.
     */
    val target: Vec3d?

    /**
     * Whether the pathfinding entity has reached its target.
     *
     * @return `true` if the entity has reached the target.
     */
    fun hasReachedTarget(): Boolean

    /**
     * Requests that the entity be moved towards the given [x], [y],
     * and [z] coordinates.
     *
     * This may happen immediately if the current target is the same as or
     * very close to the coordinates. It may also take a while to reach the
     * target if it is far away.
     *
     * @param x The X coordinate to move to.
     * @param y The Y coordinate to move to.
     * @param z The Z coordinate to move to.
     */
    fun pathTo(x: Double, y: Double, z: Double)

    /**
     * Requests that the entity be moved towards the given [position].
     *
     * This may happen immediately if the current target is the same as or
     * very close to the position. It may also take a while to reach the
     * target if it is far away.
     *
     * @param position The position to move to.
     */
    fun pathTo(position: Vec3d) {
        pathTo(position.x, position.y, position.z)
    }

    /**
     * Requests that the entity be moved towards the given [position].
     *
     * This may happen immediately if the current target is the same as or
     * very close to the position. It may also take a while to reach the
     * target if it is far away.
     *
     * @param position The position to move to.
     */
    fun pathTo(position: Vec3i) {
        pathTo(position.x.toDouble(), position.y.toDouble(), position.z.toDouble())
    }

    /**
     * Requests that the entity be moved towards the given [position].
     *
     * This may happen immediately if the current target is the same as or
     * very close to the position. It may also take a while to reach the
     * target if it is far away.
     *
     * @param position The position to move to.
     */
    fun pathTo(position: Position) {
        pathTo(position.x, position.y, position.z)
    }
}

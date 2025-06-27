package net.aquamine.server.entity.ai.pathfinding

import com.extollit.gaming.ai.path.HydrazinePathFinder
import com.extollit.gaming.ai.path.PathOptions
import com.extollit.gaming.ai.path.model.IPath
import net.aquamine.api.entity.ai.pathfinding.Navigator
import net.aquamine.api.util.Position
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.server.coordinate.Positioning
import net.aquamine.server.entity.AquaMob
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class AquaNavigator(override val entity: AquaMob) : Navigator {

    private val pathingEntity = AquaPathingEntity(this)
    private var pathfinder = HydrazinePathFinder(pathingEntity, AquaInstanceSpace(entity.world))
    private var currentPath: IPath? = null
    override var target: Vec3d? = null

    override fun hasReachedTarget(): Boolean = currentPath == null || currentPath!!.done()

    fun moveTowards(direction: Vec3d, speed: Double) {
        val position = entity.position
        val dx = position.x - direction.x
        val dy = position.y - direction.y
        val dz = position.z - direction.z

        val radians = atan2(dz, dx)
        val speedX = cos(radians) * speed
        val speedY = dy * speed
        val speedZ = sin(radians) * speed

        val yaw = Positioning.calculateLookYaw(direction.x - position.x, direction.z - position.z)
        val pitch = Positioning.calculateLookPitch(direction.x - position.x, direction.y - position.y, direction.z - position.z)

        val newPos = Position(position.x - speedX, position.y - speedY, position.z - speedZ, yaw, pitch)
        if (newPos == position) return // Don't move if we don't need to
        entity.teleport(newPos)
    }

    override fun pathTo(x: Double, y: Double, z: Double) {
        tryPathTo(Vec3d(x, y, z))
    }

    override fun pathTo(position: Vec3d) {
        tryPathTo(position)
    }

    override fun pathTo(position: Vec3i) {
        tryPathTo(position.asVec3d())
    }

    override fun pathTo(position: Position) {
        tryPathTo(position.asVec3d())
    }

    fun tryPathTo(position: Vec3d?): Boolean {
        if (position != null && target != null && position == target) return false
        pathfinder.reset()
        if (position == null) return false

        val pathOptions = PathOptions().targetingStrategy(PathOptions.TargetingStrategy.gravitySnap)
        val path = pathfinder.initiatePathTo(position.x, position.y, position.z, pathOptions) ?: return false

        currentPath = path
        target = position
        return true
    }

    fun tick() {
        if (currentPath == null) return
        if (entity.isDead) return // Dead entities cannot pathfind
        currentPath = pathfinder.updatePathFor(pathingEntity)
        if (hasReachedTarget()) reset()
    }

    private fun reset() {
        currentPath = null
        target = null
        pathfinder.reset()
    }
}

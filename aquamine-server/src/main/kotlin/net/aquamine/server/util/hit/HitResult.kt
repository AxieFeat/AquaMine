package net.aquamine.server.util.hit

import net.aquamine.api.util.Vec3d
import net.aquamine.server.entity.KryptonEntity

sealed class HitResult(val location: Vec3d) {

    abstract val type: Type

    fun distanceTo(entity: KryptonEntity): Double {
        val dx = location.x - entity.position.x
        val dy = location.y - entity.position.y
        val dz = location.z - entity.position.z
        return dx * dx + dy * dy + dz * dz
    }

    enum class Type {

        MISS,
        BLOCK,
        ENTITY
    }
}

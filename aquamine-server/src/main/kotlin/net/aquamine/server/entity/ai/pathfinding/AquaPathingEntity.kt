package net.aquamine.server.entity.ai.pathfinding

import com.extollit.gaming.ai.path.model.Gravitation
import com.extollit.gaming.ai.path.model.IPathingEntity
import com.extollit.gaming.ai.path.model.IPathingEntity.Capabilities
import com.extollit.gaming.ai.path.model.Passibility
import com.extollit.linalg.immutable.Vec3d
import net.aquamine.server.entity.attribute.AquaAttributeType
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.api.util.Vec3d as AquaVec3d

class AquaPathingEntity(private val navigator: AquaNavigator) : IPathingEntity {

    private val entity = navigator.entity

    override fun age(): Int = entity.ticksExisted

    override fun bound(): Boolean = entity.velocity.lengthSquared() > 0.0001

    override fun searchRange(): Float = getAttributeValue(AquaAttributeTypes.FOLLOW_RANGE)

    override fun capabilities(): Capabilities = object : Capabilities {

        override fun speed(): Float = getAttributeValue(AquaAttributeTypes.MOVEMENT_SPEED)

        override fun fireResistant(): Boolean = entity.type.isImmuneToFire

        override fun cautious(): Boolean = false

        override fun climber(): Boolean = false

        override fun swimmer(): Boolean = false

        override fun aquatic(): Boolean = false

        override fun avian(): Boolean = false

        override fun aquaphobic(): Boolean = false

        override fun avoidsDoorways(): Boolean = false

        override fun opensDoors(): Boolean = false
    }

    override fun moveTo(position: Vec3d, passibility: Passibility, gravitation: Gravitation) {
        val target = AquaVec3d(position.x, position.y, position.z)
        navigator.moveTowards(target, entity.attributes.getValue(AquaAttributeTypes.MOVEMENT_SPEED))
        if (entity.position.y < target.y) {
            // Jump up if needed
            entity.velocity = AquaVec3d(0.0, 2.5, 0.0)
        }
    }

    override fun coordinates(): Vec3d = Vec3d(entity.position.x, entity.position.y, entity.position.z)

    override fun width(): Float = entity.type.width

    override fun height(): Float = entity.type.height

    private fun getAttributeValue(type: AquaAttributeType): Float = entity.attributes.getValue(type).toFloat()
}

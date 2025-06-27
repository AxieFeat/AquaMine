package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.projectile.ShulkerBullet
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3d
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.ShulkerBulletSerializer
import net.aquamine.server.world.AquaWorld
import java.util.UUID

class AquaShulkerBullet(world: AquaWorld) : AquaProjectile(world), ShulkerBullet {

    override val type: AquaEntityType<AquaShulkerBullet>
        get() = AquaEntityTypes.SHULKER_BULLET
    override val serializer: EntitySerializer<AquaShulkerBullet>
        get() = ShulkerBulletSerializer

    private var targetId: UUID? = null
    override var steps: Int = 0
    override var target: Entity? = null
    override var targetDelta: Vec3d = Vec3d.ZERO
    override var movingDirection: Direction? = null

    fun targetId(): UUID? = targetId

    fun setTargetId(id: UUID) {
        targetId = id
    }
}

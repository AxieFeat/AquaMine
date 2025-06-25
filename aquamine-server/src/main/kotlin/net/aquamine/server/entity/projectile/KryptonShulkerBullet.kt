package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.projectile.ShulkerBullet
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3d
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.ShulkerBulletSerializer
import net.aquamine.server.world.KryptonWorld
import java.util.UUID

class KryptonShulkerBullet(world: KryptonWorld) : KryptonProjectile(world), ShulkerBullet {

    override val type: KryptonEntityType<KryptonShulkerBullet>
        get() = KryptonEntityTypes.SHULKER_BULLET
    override val serializer: EntitySerializer<KryptonShulkerBullet>
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

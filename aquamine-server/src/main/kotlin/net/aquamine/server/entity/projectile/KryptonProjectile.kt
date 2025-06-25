package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.projectile.Projectile
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.ProjectileSerializer
import net.aquamine.server.world.KryptonWorld
import java.util.UUID

abstract class KryptonProjectile(world: KryptonWorld) : KryptonEntity(world), Projectile {

    override val serializer: EntitySerializer<out KryptonProjectile>
        get() = ProjectileSerializer

    private var ownerId: UUID? = null
    final override var owner: Entity? = null
        get() {
            if (field != null) return field
            if (ownerId != null) {
                field = world.entityManager.getByUUID(ownerId!!)
                return field
            }
            return null
        }
        set(value) {
            if (value == null) return
            ownerId = value.uuid
            field = value
        }
    private var leftOwner = false
    private var beenShot = false

    fun ownerId(): UUID? = ownerId

    fun setOwnerId(id: UUID) {
        ownerId = id
    }

    override fun hasLeftOwner(): Boolean = leftOwner

    fun setLeftOwner(left: Boolean) {
        leftOwner = left
    }

    override fun hasBeenShot(): Boolean = beenShot

    fun setBeenShot(shot: Boolean) {
        beenShot = shot
    }
}

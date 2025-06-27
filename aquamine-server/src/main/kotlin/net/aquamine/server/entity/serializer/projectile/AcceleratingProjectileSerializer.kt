package net.aquamine.server.entity.serializer.projectile

import org.apache.logging.log4j.LogManager
import net.aquamine.api.util.Vec3d
import net.aquamine.server.entity.projectile.AquaAcceleratingProjectile
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.DoubleTag
import xyz.axie.nbt.ListTag
import xyz.axie.nbt.list

object AcceleratingProjectileSerializer : EntitySerializer<AquaAcceleratingProjectile> {

    private val LOGGER = LogManager.getLogger()
    private const val POWER_TAG = "power"
    private const val POWER_SIZE = 3

    override fun load(entity: AquaAcceleratingProjectile, data: CompoundTag) {
        ProjectileSerializer.load(entity, data)
        if (!data.contains(POWER_TAG, ListTag.ID)) return
        val power = data.getList(POWER_TAG, DoubleTag.ID)
        if (power.size != POWER_SIZE) {
            LOGGER.warn("Found invalid accelerating projectile power array of length ${power.size}! Expected length of $POWER_SIZE! Skipping...")
            return
        }
        entity.acceleration = Vec3d(power.getDouble(0), power.getDouble(1), power.getDouble(2))
    }

    override fun save(entity: AquaAcceleratingProjectile): CompoundTag.Builder = ProjectileSerializer.save(entity).apply {
        list(POWER_TAG) {
            addDouble(entity.acceleration.x)
            addDouble(entity.acceleration.y)
            addDouble(entity.acceleration.z)
        }
    }
}

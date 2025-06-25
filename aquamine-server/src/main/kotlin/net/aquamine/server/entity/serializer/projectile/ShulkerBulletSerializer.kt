package net.aquamine.server.entity.serializer.projectile

import net.aquamine.api.util.Vec3d
import net.aquamine.server.entity.projectile.KryptonShulkerBullet
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.enumhelper.Directions
import net.aquamine.server.util.nbt.getUUID
import net.aquamine.server.util.nbt.hasNumber
import net.aquamine.server.util.nbt.hasUUID
import net.aquamine.server.util.nbt.putNullable
import net.aquamine.server.util.nbt.putUUID
import xyz.axie.nbt.CompoundTag

object ShulkerBulletSerializer : EntitySerializer<KryptonShulkerBullet> {

    private const val STEPS_TAG = "Steps"
    private const val DELTA_X_TAG = "TXD"
    private const val DELTA_Y_TAG = "TYD"
    private const val DELTA_Z_TAG = "TZD"
    private const val DIR_TAG = "Dir"
    private const val TARGET_TAG = "Target"

    override fun load(entity: KryptonShulkerBullet, data: CompoundTag) {
        ProjectileSerializer.load(entity, data)
        entity.steps = data.getInt(STEPS_TAG)
        entity.targetDelta = Vec3d(data.getDouble(DELTA_X_TAG), data.getDouble(DELTA_Y_TAG), data.getDouble(DELTA_Z_TAG))
        if (data.hasNumber(DIR_TAG)) entity.movingDirection = Directions.of3D(data.getInt(DIR_TAG))
        if (data.hasUUID(TARGET_TAG)) entity.setTargetId(data.getUUID(TARGET_TAG))
    }

    override fun save(entity: KryptonShulkerBullet): CompoundTag.Builder = ProjectileSerializer.save(entity).apply {
        putInt(STEPS_TAG, entity.steps)
        putDouble(DELTA_X_TAG, entity.targetDelta.x)
        putDouble(DELTA_Y_TAG, entity.targetDelta.y)
        putDouble(DELTA_Z_TAG, entity.targetDelta.z)
        putNullable(DIR_TAG, entity.movingDirection) { name, value -> putInt(name, value.ordinal) }
        putNullable(TARGET_TAG, entity.targetId(), CompoundTag.Builder::putUUID)
    }
}

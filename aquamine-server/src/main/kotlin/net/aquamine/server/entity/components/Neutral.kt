package net.aquamine.server.entity.components

import net.aquamine.api.entity.EntityTypes
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.util.nbt.getUUID
import net.aquamine.server.util.nbt.hasUUID
import net.aquamine.server.util.nbt.putNullable
import net.aquamine.server.util.nbt.putUUID
import xyz.axie.nbt.CompoundTag
import java.util.UUID

interface Neutral {

    var remainingAngerTime: Int
    var angerTarget: UUID?
    var lastHurtByMob: AquaLivingEntity?
    var lastHurtByPlayer: AquaPlayer?

    val isAngry: Boolean
        get() = remainingAngerTime > 0

    fun startAngerTimer()

    fun stopBeingAngry() {
        lastHurtByMob = null
        angerTarget = null
        setTarget(null)
        remainingAngerTime = 0
    }

    fun setTarget(target: AquaLivingEntity?)

    companion object {

        private const val ANGER_TIME_TAG = "AngerTime"
        private const val ANGRY_AT_TAG = "AngryAt"

        @JvmStatic
        fun <E> loadAngerData(entity: E, data: CompoundTag) where E : AquaEntity, E : Neutral {
            entity.remainingAngerTime = data.getInt(ANGER_TIME_TAG)
            if (!data.hasUUID(ANGRY_AT_TAG)) {
                entity.angerTarget = null
                return
            }
            val targetId = data.getUUID(ANGRY_AT_TAG)
            entity.angerTarget = targetId
            val target = entity.world.entityManager.getByUUID(targetId)
            if (target != null) {
                if (target is AquaMob) entity.lastHurtByMob = target
                if (target.type === EntityTypes.PLAYER.get()) entity.lastHurtByPlayer = target as AquaPlayer
            }
        }

        @JvmStatic
        fun saveAngerData(entity: Neutral, data: CompoundTag.Builder): CompoundTag.Builder = data.apply {
            putInt(ANGER_TIME_TAG, entity.remainingAngerTime)
            putNullable(ANGRY_AT_TAG, entity.angerTarget, CompoundTag.Builder::putUUID)
        }
    }
}

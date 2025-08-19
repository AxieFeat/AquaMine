package net.aquamine.server.entity.serializer

import org.apache.logging.log4j.LogManager
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.entity.Pose
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.potion.AquaPotionEffect
import net.aquamine.server.util.nbt.getBlockPos
import net.aquamine.server.util.nbt.hasBlockPos
import net.aquamine.server.util.nbt.hasNumber
import net.aquamine.server.util.nbt.putBlockPosParts
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.ListTag
import xyz.axie.nbt.StringTag

object LivingEntitySerializer : EntitySerializer<AquaLivingEntity> {

    private val LOGGER = LogManager.getLogger()
    private const val ATTRIBUTES_TAG = "Attributes"
    private const val BRAIN_TAG = "Brain"
    private const val HEALTH_TAG = "Health"
    private const val GLIDING_TAG = "FallFlying"
    private const val ABSORPTION_TAG = "AbsorptionAmount"
    private const val DEATH_TIME = "DeathTime"
    private const val HURT_BY_TAG = "HurtByTimestamp"
    private const val HURT_TIME_TAG = "HurtTime"
    private const val TEAM_TAG = "Team"
    private const val SLEEPING_PREFIX = "Sleeping"
    private const val ACTIVE_EFFECTS_TAG = "ActiveEffects"

    override fun load(entity: AquaLivingEntity, data: CompoundTag) {
        BaseEntitySerializer.load(entity, data)
        // AI stuff
        if (data.contains(ATTRIBUTES_TAG, ListTag.ID)) entity.attributes.load(data.getList(ATTRIBUTES_TAG, CompoundTag.ID))
        if (data.contains(BRAIN_TAG, CompoundTag.ID)) entity.brain.load(data.getCompound(BRAIN_TAG))

        // Values
        if (data.hasNumber(HEALTH_TAG)) entity.data.set(MetadataKeys.LivingEntity.HEALTH, data.getFloat(HEALTH_TAG))
        if (data.getBoolean(GLIDING_TAG)) entity.isGliding = true
        entity.absorption = data.getFloat(ABSORPTION_TAG).coerceAtLeast(0F)
        entity.deathTime = data.getShort(DEATH_TIME).toInt()
        entity.lastHurtTimestamp = data.getInt(HURT_BY_TAG)
        entity.hurtTime = data.getShort(HURT_TIME_TAG).toInt()

        // Scoreboard
        if (data.contains(TEAM_TAG, StringTag.ID)) {
            val teamName = data.getString(TEAM_TAG)
            val team = entity.world.scoreboard.getTeam(teamName)
            val wasAdded = team != null && entity.world.scoreboard.addMemberToTeam(entity.teamRepresentation, team)
            if (!wasAdded) {
                LOGGER.warn("Unable to add living entity ${entity.name} to team $teamName. This team may not exist.")
            }
        }

        // Sleeping coordinates
        if (data.hasBlockPos(SLEEPING_PREFIX)) {
            entity.sleepingPosition = data.getBlockPos(SLEEPING_PREFIX)
            entity.pose = Pose.SLEEPING
        }

        // Potion effects
        if(data.contains(ACTIVE_EFFECTS_TAG))
            data.getList(ACTIVE_EFFECTS_TAG, CompoundTag.ID).map { AquaPotionEffect.load(it as CompoundTag) }.forEach(entity::addPotionEffect)
    }

    override fun save(entity: AquaLivingEntity): CompoundTag.Builder = BaseEntitySerializer.save(entity).apply {
        putFloat(ABSORPTION_TAG, entity.absorption)
        put(ATTRIBUTES_TAG, entity.attributes.save())
        put(BRAIN_TAG, entity.brain.save())
        putShort(DEATH_TIME, entity.deathTime.toShort())
        putBoolean(GLIDING_TAG, entity.isGliding)
        putFloat(HEALTH_TAG, entity.health)
        putInt(HURT_BY_TAG, entity.lastHurtTimestamp)
        putShort(HURT_TIME_TAG, entity.hurtTime.toShort())
        entity.sleepingPosition?.let { putBlockPosParts(it, SLEEPING_PREFIX) }
        val activePotionEffects = entity.aquaActivePotionEffects.map { it.potion.withDuration(it.ticksToEnd) }
        if(activePotionEffects.isNotEmpty()) {
            putList(ACTIVE_EFFECTS_TAG) { list ->
                activePotionEffects.map(AquaPotionEffect::save).forEach { list.add(it) }
            }
        }
    }
}

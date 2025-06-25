package net.aquamine.server.entity.player

import org.apache.logging.log4j.Logger
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.World
import net.aquamine.server.util.Keys
import net.aquamine.server.util.nbt.getBlockPos
import net.aquamine.server.util.nbt.hasBlockPos
import net.aquamine.server.util.nbt.putBlockPosParts
import net.aquamine.server.util.serialization.Codecs
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.StringTag
import org.kryptonmc.serialization.nbt.NbtOps

@JvmRecord
data class RespawnData(val position: Vec3i, val dimension: ResourceKey<World>, val angle: Float, val forced: Boolean) {

    fun save(data: CompoundTag.Builder, logger: Logger): CompoundTag.Builder = data.apply {
        putBlockPosParts(position, XYZ_PREFIX)
        putFloat(ANGLE_TAG, angle)
        putBoolean(FORCED_TAG, forced)
        Keys.CODEC.encodeStart(dimension.location, NbtOps.INSTANCE).resultOrPartial { logger.error(it) }.ifPresent { put(DIMENSION_TAG, it) }
    }

    companion object {

        private const val XYZ_PREFIX = "Spawn"
        private const val ANGLE_TAG = "SpawnAngle"
        private const val FORCED_TAG = "SpawnForced"
        private const val DIMENSION_TAG = "SpawnDimension"

        @JvmStatic
        fun load(data: CompoundTag, logger: Logger): RespawnData? {
            if (!data.hasBlockPos(XYZ_PREFIX)) return null
            val dimension = if (data.contains(DIMENSION_TAG, StringTag.ID)) {
                Codecs.DIMENSION.read(data.get(DIMENSION_TAG), NbtOps.INSTANCE).resultOrPartial { logger.error(it) }.orElse(World.OVERWORLD)
            } else {
                World.OVERWORLD
            }
            return RespawnData(data.getBlockPos(XYZ_PREFIX), dimension, data.getFloat(ANGLE_TAG), data.getBoolean(FORCED_TAG))
        }
    }
}

package net.aquamine.spark.provider.world

import me.lucko.spark.common.platform.world.AbstractChunkInfo
import me.lucko.spark.common.platform.world.CountMap
import net.aquamine.api.entity.EntityType
import net.aquamine.api.world.chunk.Chunk
import java.util.concurrent.atomic.AtomicInteger

class AquaChunkInfo(
    val chunk: Chunk,
) : AbstractChunkInfo<EntityType<*>>(chunk.x, chunk.z) {

    private val entityCounts = CountMap.Simple(
        chunk.entities
            .map { it.type }
            .distinct()
            .associateWith { AtomicInteger() }
    ).also {
        chunk.entities.forEach { entity ->
            it.increment(entity.type)
        }
    }

    override fun getEntityCounts(): CountMap<EntityType<*>> {
        return entityCounts
    }

    override fun entityTypeName(type: EntityType<*>): String {
        return type.key().toString()
    }
}
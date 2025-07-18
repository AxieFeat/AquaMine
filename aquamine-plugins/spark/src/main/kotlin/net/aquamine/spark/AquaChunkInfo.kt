package net.aquamine.spark

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
            .associateWith { type ->
                AtomicInteger(chunk.entities.count { it.type == type })
            }
    )

    override fun getEntityCounts(): CountMap<EntityType<*>> {
        return entityCounts
    }

    override fun entityTypeName(type: EntityType<*>): String {
        return type.key().toString()
    }
}
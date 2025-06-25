package net.aquamine.server.entity.ai.pathfinding

import com.extollit.gaming.ai.path.model.IBlockObject
import com.extollit.gaming.ai.path.model.IColumnarSpace
import com.extollit.gaming.ai.path.model.IInstanceSpace
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.chunk.KryptonChunk
import java.util.concurrent.ConcurrentHashMap

class KryptonInstanceSpace(private val world: KryptonWorld) : IInstanceSpace {

    private val chunkSpaceMap = ConcurrentHashMap<KryptonChunk, KryptonColumnarSpace>()

    override fun blockObjectAt(x: Int, y: Int, z: Int): IBlockObject = KryptonHydrazineBlock.get(world.getBlock(x, y, z))

    override fun columnarSpaceAt(cx: Int, cz: Int): IColumnarSpace? {
        val chunk = world.getChunk(cx, cz) ?: return null
        return chunkSpaceMap.computeIfAbsent(chunk) { KryptonColumnarSpace(this, it) }
    }
}

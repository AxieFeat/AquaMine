package net.aquamine.server.entity.ai.pathfinding

import com.extollit.gaming.ai.path.model.IBlockObject
import com.extollit.gaming.ai.path.model.IColumnarSpace
import com.extollit.gaming.ai.path.model.IInstanceSpace
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.chunk.AquaChunk
import java.util.concurrent.ConcurrentHashMap

class AquaInstanceSpace(private val world: AquaWorld) : IInstanceSpace {

    private val chunkSpaceMap = ConcurrentHashMap<AquaChunk, AquaColumnarSpace>()

    override fun blockObjectAt(x: Int, y: Int, z: Int): IBlockObject = AquaHydrazineBlock.get(world.getBlock(x, y, z))

    override fun columnarSpaceAt(cx: Int, cz: Int): IColumnarSpace? {
        val chunk = world.getChunk(cx, cz) ?: return null
        return chunkSpaceMap.computeIfAbsent(chunk) { AquaColumnarSpace(this, it) }
    }
}

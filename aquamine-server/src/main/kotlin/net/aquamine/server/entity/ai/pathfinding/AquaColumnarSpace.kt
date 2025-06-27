package net.aquamine.server.entity.ai.pathfinding

import com.extollit.gaming.ai.path.model.ColumnarOcclusionFieldList
import com.extollit.gaming.ai.path.model.IBlockDescription
import com.extollit.gaming.ai.path.model.IColumnarSpace
import com.extollit.gaming.ai.path.model.IInstanceSpace
import net.aquamine.server.world.chunk.AquaChunk

class AquaColumnarSpace(private val instanceSpace: AquaInstanceSpace, private val chunk: AquaChunk) : IColumnarSpace {

    private val occlusionFieldList = ColumnarOcclusionFieldList(this)

    override fun blockAt(x: Int, y: Int, z: Int): IBlockDescription = AquaHydrazineBlock.get(chunk.getBlock(x, y, z))

    override fun metaDataAt(x: Int, y: Int, z: Int): Int = 0

    override fun occlusionFields(): ColumnarOcclusionFieldList = occlusionFieldList

    override fun instance(): IInstanceSpace = instanceSpace
}

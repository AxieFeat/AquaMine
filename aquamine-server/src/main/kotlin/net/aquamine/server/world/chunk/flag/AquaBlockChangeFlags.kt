package net.aquamine.server.world.chunk.flag

import net.aquamine.api.world.chunk.BlockChangeFlags

@JvmRecord
data class AquaBlockChangeFlags(override val raw: Int) : BlockChangeFlags {

    override val updateNeighbours: Boolean
        get() = raw and SetBlockFlag.UPDATE_NEIGHBOURS != 0
    override val notifyClients: Boolean
        get() = raw and SetBlockFlag.NOTIFY_CLIENTS != 0
    override val updateNeighbourShapes: Boolean
        get() = raw and SetBlockFlag.UPDATE_NEIGHBOUR_SHAPES != 0
    override val neighbourDrops: Boolean
        get() = raw and SetBlockFlag.NEIGHBOUR_DROPS != 0
    override val blockMoving: Boolean
        get() = raw and SetBlockFlag.BLOCK_MOVING != 0
    override val lighting: Boolean
        get() = raw and SetBlockFlag.LIGHTING != 0

    override fun withUpdateNeighbours(updateNeighbours: Boolean): BlockChangeFlags {
        if (this.updateNeighbours == updateNeighbours) return this
        return AquaBlockChangeFlags(raw or SetBlockFlag.UPDATE_NEIGHBOURS)
    }

    override fun withNotifyClients(notifyClients: Boolean): BlockChangeFlags {
        if (this.notifyClients == notifyClients) return this
        return AquaBlockChangeFlags(raw or SetBlockFlag.NOTIFY_CLIENTS)
    }

    override fun withUpdateNeighbourShapes(updateNeighbourShapes: Boolean): BlockChangeFlags {
        if (this.updateNeighbourShapes == updateNeighbourShapes) return this
        return AquaBlockChangeFlags(raw or SetBlockFlag.UPDATE_NEIGHBOUR_SHAPES)
    }

    override fun withNeighbourDrops(neighbourDrops: Boolean): BlockChangeFlags {
        if (this.neighbourDrops == neighbourDrops) return this
        return AquaBlockChangeFlags(raw or SetBlockFlag.NEIGHBOUR_DROPS)
    }

    override fun withBlockMoving(blockMoving: Boolean): BlockChangeFlags {
        if (this.blockMoving == blockMoving) return this
        return AquaBlockChangeFlags(raw or SetBlockFlag.BLOCK_MOVING)
    }

    override fun withLighting(lighting: Boolean): BlockChangeFlags {
        if (this.lighting == lighting) return this
        return AquaBlockChangeFlags(raw or SetBlockFlag.LIGHTING)
    }

    override fun not(): BlockChangeFlags = AquaBlockChangeFlags(raw.inv())

    override fun and(other: BlockChangeFlags): BlockChangeFlags = AquaBlockChangeFlags(raw and other.raw)

    override fun or(other: BlockChangeFlags): BlockChangeFlags = AquaBlockChangeFlags(raw or other.raw)

    object Factory : BlockChangeFlags.Factory {

        override fun none(): BlockChangeFlags = AquaBlockChangeFlags(0)

        override fun all(): BlockChangeFlags = AquaBlockChangeFlags(SetBlockFlag.ALL)
    }
}

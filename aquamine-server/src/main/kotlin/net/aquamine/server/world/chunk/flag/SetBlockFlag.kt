package net.aquamine.server.world.chunk.flag

object SetBlockFlag {

    const val UPDATE_NEIGHBOURS: Int = 1
    const val NOTIFY_CLIENTS: Int = 2
    const val UPDATE_NEIGHBOUR_SHAPES: Int = 16
    const val NEIGHBOUR_DROPS: Int = 32
    const val BLOCK_MOVING: Int = 64
    const val LIGHTING: Int = 128

    const val UPDATE_NOTIFY: Int = UPDATE_NEIGHBOURS or NOTIFY_CLIENTS
    const val NO_NEIGHBOUR_DROPS: Int = NEIGHBOUR_DROPS.inv()
    const val ALL: Int = UPDATE_NEIGHBOURS or NOTIFY_CLIENTS or UPDATE_NEIGHBOUR_SHAPES or NEIGHBOUR_DROPS or BLOCK_MOVING or LIGHTING
}

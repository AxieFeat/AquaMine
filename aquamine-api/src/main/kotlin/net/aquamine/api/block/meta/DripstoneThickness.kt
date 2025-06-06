package net.aquamine.api.block.meta

/**
 * Indicates the thickness of a dripstone block that forms the multi block
 * stalagmites and stalactites.
 *
 * As stalagmites and stalactites are spikes on the ceiling or the ground,
 * and they vary in size, it is important to be able to track which part of it
 * a specific block is. For example, a three-block stalagmite may have a base,
 * the part connected to the ground, a middle, the section between the base
 * and the top, and the tip, the section at the top that ends the stalagmite.
 */
enum class DripstoneThickness {

    TIP_MERGE,
    TIP,
    FRUSTUM,
    MIDDLE,
    BASE
}

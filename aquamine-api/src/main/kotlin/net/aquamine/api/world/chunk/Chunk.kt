package net.aquamine.api.world.chunk

import net.aquamine.api.block.BlockContainer
import net.aquamine.api.entity.Entity
import net.aquamine.api.entity.player.Player
import net.aquamine.api.fluid.FluidContainer
import net.aquamine.api.world.EntityContainer
import net.aquamine.api.world.World
import net.aquamine.api.world.biome.BiomeContainer

/**
 * Represents a chunk or a 16 x 16 x world height area of blocks.
 */
interface Chunk : BlockContainer, FluidContainer, BiomeContainer, EntityContainer {

    /**
     * The world this chunk is in.
     */
    val world: World

    /**
     * The X position of this chunk.
     */
    val x: Int

    /**
     * The Z position of this chunk.
     */
    val z: Int

    /**
     * The cumulative number of ticks players has been in this chunk.
     *
     * Note that this value increases faster when more players are in the
     * chunk.
     *
     * This is used for regional difficulty. It increases the chances of mobs
     * spawning with equipment, the chances of that equipment having
     * enchantments, the chances of spiders having potion effects, the chances
     * of mobs having the ability to pick up dropped items, and the chances of
     * zombies having the ability to spawn other zombies when attacked.
     *
     * Also note that regional difficulty is capped when this value reaches
     * 3,600,000, meaning that none of the above will increase past that point.
     *
     * See [here](https://minecraft.gamepedia.com/Chunk_format#NBT_structure)
     * for more details.
     */
    val inhabitedTime: Long

    /**
     * The time that this chunk was last updated. This is set when the chunk is
     * saved to disk.
     */
    val lastUpdate: Long

    /**
     * All the entities currently in this chunk.
     */
    override val entities: Collection<Entity>

    /**
     * All the players currently in this chunk.
     */
    override val players: Collection<Player>
}

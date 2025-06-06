package net.aquamine.api.entity.vehicle

import net.aquamine.api.block.Block
import net.aquamine.api.block.Blocks
import net.aquamine.api.registry.RegistryReference

/**
 * A variant of a boat.
 *
 * @property planks The type of planks the boat is made out of.
 */
enum class BoatVariant(val planks: RegistryReference<Block>) {

    OAK(Blocks.OAK_PLANKS),
    SPRUCE(Blocks.SPRUCE_PLANKS),
    BIRCH(Blocks.BIRCH_PLANKS),
    JUNGLE(Blocks.JUNGLE_PLANKS),
    ACACIA(Blocks.ACACIA_PLANKS),
    DARK_OAK(Blocks.DARK_OAK_PLANKS);
}

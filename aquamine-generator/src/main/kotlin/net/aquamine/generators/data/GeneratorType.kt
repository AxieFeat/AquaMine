package net.aquamine.generators.data

import net.aquamine.generators.data.generators.BlockGenerator
import net.aquamine.generators.data.generators.BlockPropertyGenerator
import net.aquamine.generators.data.generators.ItemGenerator
import net.aquamine.generators.data.generators.SoundGenerator

enum class GeneratorType(
    val fileName: String,
    val generator: Generator<*>
) {

    BLOCK_PROPERTIES("block_properties", BlockPropertyGenerator),
    BLOCKS("blocks", BlockGenerator),
    ITEMS("items", ItemGenerator),
    SOUNDS("sounds", SoundGenerator)

}
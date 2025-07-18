package net.aquamine.api.world.generator

import java.util.function.Consumer

fun interface Generator {

    /**
     * This method is called when this generator is requesting this unit to be filled with blocks or biomes.
     *
     * @param unit The unit to fill.
     */
    fun generate(unit: GenerationUnit)

    /**
     * Runs [generate] on each unit in the collection.
     *
     * @param units The list of units to fill.
     */
    fun generateAll(units: MutableCollection<GenerationUnit>) {
        units.forEach(Consumer { unit: GenerationUnit -> this.generate(unit) })
    }

}
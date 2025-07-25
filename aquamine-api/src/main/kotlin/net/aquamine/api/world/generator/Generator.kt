package net.aquamine.api.world.generator

fun interface Generator {

    /**
     * This method is called when this generator is requesting this unit to be filled with blocks or biomes.
     *
     * @param unit The unit to fill.
     */
    fun generate(unit: GenerationUnit)

}
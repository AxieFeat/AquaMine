package net.aquamine.server.world.fluid

import net.kyori.adventure.key.Key
import net.aquamine.server.registry.AquaRegistries

object AquaFluids {

    @JvmField
    val EMPTY: AquaFluid = register("empty", EmptyFluid())
    @JvmField
    val FLOWING_WATER: FlowingFluid = register("flowing_water", WaterFluid.Flowing())
    @JvmField
    val WATER: FlowingFluid = register("water", WaterFluid.Source())
    @JvmField
    val FLOWING_LAVA: FlowingFluid = register("flowing_lava", LavaFluid.Flowing())
    @JvmField
    val LAVA: FlowingFluid = register("lava", LavaFluid.Source())

    init {
        AquaRegistries.FLUID.forEach { it.stateDefinition.states.forEach(AquaFluid.STATES::add) }
    }

    @JvmStatic
    private fun <T : AquaFluid> register(key: String, fluid: T): T = AquaRegistries.register(AquaRegistries.FLUID, Key.key(key), fluid)
}

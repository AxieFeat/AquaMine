package net.aquamine.server.world.fluid

import net.kyori.adventure.key.Key
import net.aquamine.server.registry.KryptonRegistries

object KryptonFluids {

    @JvmField
    val EMPTY: KryptonFluid = register("empty", EmptyFluid())
    @JvmField
    val FLOWING_WATER: FlowingFluid = register("flowing_water", WaterFluid.Flowing())
    @JvmField
    val WATER: FlowingFluid = register("water", WaterFluid.Source())
    @JvmField
    val FLOWING_LAVA: FlowingFluid = register("flowing_lava", LavaFluid.Flowing())
    @JvmField
    val LAVA: FlowingFluid = register("lava", LavaFluid.Source())

    init {
        KryptonRegistries.FLUID.forEach { it.stateDefinition.states.forEach(KryptonFluid.STATES::add) }
    }

    @JvmStatic
    private fun <T : KryptonFluid> register(key: String, fluid: T): T = KryptonRegistries.register(KryptonRegistries.FLUID, Key.key(key), fluid)
}

package net.aquamine.server.world.dimension

import net.kyori.adventure.key.Key
import net.aquamine.api.tags.BlockTags
import net.aquamine.server.registry.AquaDynamicRegistries
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.util.provider.ConstantInt
import net.aquamine.server.util.provider.UniformInt
import net.aquamine.server.world.dimension.AquaDimensionType.MonsterSettings
import java.util.OptionalLong

object AquaDimensionTypes {

    @JvmField
    val OVERWORLD_EFFECTS: Key = Key.key("overworld")
    @JvmField
    val NETHER_EFFECTS: Key = Key.key("the_nether")
    @JvmField
    val END_EFFECTS: Key = Key.key("the_end")
    @JvmField
    val OVERWORLD: AquaDimensionType = register("overworld", AquaDimensionType(OptionalLong.empty(), true, false, false, true, 1.0, true,
        false, -64, 384, 384, BlockTags.INFINIBURN_OVERWORLD, OVERWORLD_EFFECTS, 0F, MonsterSettings(false, true, UniformInt(0, 7), 0)))
    @JvmField
    val THE_NETHER: AquaDimensionType = register("the_nether", AquaDimensionType(OptionalLong.of(18000L), false, true, true, false, 8.0, false,
        true, 0, 256, 128, BlockTags.INFINIBURN_NETHER, NETHER_EFFECTS, 0.1F, MonsterSettings(true, false, ConstantInt.of(11), 15)))
    @JvmField
    val THE_END: AquaDimensionType = register("the_end", AquaDimensionType(OptionalLong.of(6000L), false, false, false, false, 1.0, false,
        false, 0, 256, 256, BlockTags.INFINIBURN_END, END_EFFECTS, 0F, MonsterSettings(false, true, UniformInt(0, 7), 0)))
    @JvmField
    val OVERWORLD_CAVES: AquaDimensionType = register("overworld_caves", AquaDimensionType(OptionalLong.empty(), true, true, false, true, 1.0,
        true, false, -64, 384, 384, BlockTags.INFINIBURN_OVERWORLD, OVERWORLD_EFFECTS, 0F, MonsterSettings(false, true, UniformInt(0, 7), 0)))

    @JvmStatic
    private fun register(key: String, type: AquaDimensionType): AquaDimensionType =
        AquaRegistries.register(AquaDynamicRegistries.DIMENSION_TYPE, Key.key(key), type)
}

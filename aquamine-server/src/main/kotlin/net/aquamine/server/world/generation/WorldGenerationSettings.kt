package net.aquamine.server.world.generation

import net.aquamine.server.util.random.RandomSource
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.compound

@JvmRecord
data class WorldGenerationSettings(val seed: Long, val generateFeatures: Boolean, val bonusChest: Boolean, val dimensions: CompoundTag) {

    fun save(): CompoundTag = compound {
        putLong("seed", seed)
        putBoolean("generate_features", generateFeatures)
        putBoolean("bonus_chest", bonusChest)
        put("dimensions", dimensions)
    }

    companion object {

        private val RANDOM = RandomSource.createThreadLocal()

        @JvmStatic
        fun parse(data: CompoundTag): WorldGenerationSettings = WorldGenerationSettings(
            data.getLong("seed", RANDOM.nextLong()),
            data.getBoolean("generate_features"),
            data.getBoolean("bonus_chest"),
            data.getCompound("dimensions")
        )
    }
}

package net.aquamine.server.entity.serializer.aquatic

import net.aquamine.server.entity.aquatic.KryptonDolphin
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.MobSerializer
import net.aquamine.server.util.nbt.getBlockPos
import net.aquamine.server.util.nbt.putBlockPosParts
import xyz.axie.nbt.CompoundTag

object DolphinSerializer : EntitySerializer<KryptonDolphin> {

    private const val TREASURE_PREFIX = "TreasurePos"
    private const val GOT_FISH_TAG = "GotFish"
    private const val MOISTNESS_TAG = "Moistness"

    override fun load(entity: KryptonDolphin, data: CompoundTag) {
        MobSerializer.load(entity, data)
        entity.treasurePosition = data.getBlockPos(TREASURE_PREFIX)
        entity.hasGotFish = data.getBoolean(GOT_FISH_TAG)
        entity.skinMoisture = data.getInt(MOISTNESS_TAG)
    }

    override fun save(entity: KryptonDolphin): CompoundTag.Builder = MobSerializer.save(entity).apply {
        putBlockPosParts(entity.treasurePosition, TREASURE_PREFIX)
        putBoolean(GOT_FISH_TAG, entity.hasGotFish)
        putInt(MOISTNESS_TAG, entity.skinMoisture)
    }
}

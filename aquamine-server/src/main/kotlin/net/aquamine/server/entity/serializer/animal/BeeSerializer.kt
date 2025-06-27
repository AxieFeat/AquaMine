package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.components.Neutral
import net.aquamine.server.entity.animal.AquaBee
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.getBlockPos
import net.aquamine.server.util.nbt.getNullableCompound
import net.aquamine.server.util.nbt.putNullable
import net.aquamine.server.util.nbt.putBlockPos
import xyz.axie.nbt.CompoundTag

object BeeSerializer : EntitySerializer<AquaBee> {

    private const val HIVE_TAG = "HivePos"
    private const val FLOWER_TAG = "FlowerPos"
    private const val NECTAR_TAG = "HasNectar"
    private const val STUNG_TAG = "HasStung"
    private const val TICKS_SINCE_POLLINATION_TAG = "TicksSincePollination"
    private const val CANNOT_ENTER_HIVE_TICKS_TAG = "CannotEnterHiveTicks"
    private const val CROPS_GROWN_SINCE_POLLINATION_TAG = "CropsGrownSincePollination"

    override fun load(entity: AquaBee, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        Neutral.loadAngerData(entity, data)
        entity.hive = data.getNullableCompound(HIVE_TAG)?.getBlockPos()
        entity.flower = data.getNullableCompound(FLOWER_TAG)?.getBlockPos()
        entity.hasNectar = data.getBoolean(NECTAR_TAG)
        entity.hasStung = data.getBoolean(STUNG_TAG)
        entity.timeSincePollination = data.getInt(TICKS_SINCE_POLLINATION_TAG)
        entity.cannotEnterHiveTicks = data.getInt(CANNOT_ENTER_HIVE_TICKS_TAG)
        entity.cropsGrownSincePollination = data.getInt(CROPS_GROWN_SINCE_POLLINATION_TAG)
    }

    override fun save(entity: AquaBee): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        Neutral.saveAngerData(entity, this)
        putNullable(HIVE_TAG, entity.hive, CompoundTag.Builder::putBlockPos)
        putNullable(FLOWER_TAG, entity.flower, CompoundTag.Builder::putBlockPos)
        putBoolean(NECTAR_TAG, entity.hasNectar)
        putBoolean(STUNG_TAG, entity.hasStung)
        putInt(TICKS_SINCE_POLLINATION_TAG, entity.timeSincePollination)
        putInt(CANNOT_ENTER_HIVE_TICKS_TAG, entity.cannotEnterHiveTicks)
        putInt(CROPS_GROWN_SINCE_POLLINATION_TAG, entity.cropsGrownSincePollination)
    }
}

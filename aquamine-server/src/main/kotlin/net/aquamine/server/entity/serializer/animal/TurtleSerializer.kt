package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.KryptonTurtle
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.getBlockPos
import net.aquamine.server.util.nbt.putBlockPosParts
import xyz.axie.nbt.CompoundTag

object TurtleSerializer : EntitySerializer<KryptonTurtle> {

    private const val HOME_PREFIX = "HomePos"
    private const val DESTINATION_PREFIX = "TravelPos"
    private const val HAS_EGG_TAG = "HasEgg"

    override fun load(entity: KryptonTurtle, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        entity.home = data.getBlockPos(HOME_PREFIX)
        entity.destination = data.getBlockPos(DESTINATION_PREFIX)
        entity.hasEgg = data.getBoolean(HAS_EGG_TAG)
    }

    override fun save(entity: KryptonTurtle): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putBlockPosParts(entity.home, HOME_PREFIX)
        putBlockPosParts(entity.destination, DESTINATION_PREFIX)
        putBoolean(HAS_EGG_TAG, entity.hasEgg)
    }
}

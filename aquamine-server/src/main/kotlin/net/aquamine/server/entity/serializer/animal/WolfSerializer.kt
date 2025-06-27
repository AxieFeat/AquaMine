package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.components.Neutral
import net.aquamine.server.entity.animal.AquaWolf
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.hasNumber
import xyz.axie.nbt.CompoundTag

object WolfSerializer : EntitySerializer<AquaWolf> {

    private const val COLLAR_COLOR_TAG = "CollarColor"

    override fun load(entity: AquaWolf, data: CompoundTag) {
        TamableSerializer.load(entity, data)
        if (data.hasNumber(COLLAR_COLOR_TAG)) entity.data.set(MetadataKeys.Wolf.COLLAR_COLOR, data.getInt(COLLAR_COLOR_TAG))
        Neutral.loadAngerData(entity, data)
    }

    override fun save(entity: AquaWolf): CompoundTag.Builder = TamableSerializer.save(entity).apply {
        putInt(COLLAR_COLOR_TAG, entity.data.get(MetadataKeys.Wolf.COLLAR_COLOR))
        Neutral.saveAngerData(entity, this)
    }
}

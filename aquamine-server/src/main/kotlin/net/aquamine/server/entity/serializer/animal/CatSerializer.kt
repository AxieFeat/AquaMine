package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.KryptonCat
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.hasNumber
import xyz.axie.nbt.CompoundTag

object CatSerializer : EntitySerializer<KryptonCat> {

    private const val TYPE_TAG = "CatType"
    private const val COLLAR_COLOR_TAG = "CollarColor"

    override fun load(entity: KryptonCat, data: CompoundTag) {
        TamableSerializer.load(entity, data)
        entity.data.set(MetadataKeys.Cat.VARIANT, data.getInt(TYPE_TAG))
        if (data.hasNumber(COLLAR_COLOR_TAG)) entity.data.set(MetadataKeys.Cat.COLLAR_COLOR, data.getInt(COLLAR_COLOR_TAG))
    }

    override fun save(entity: KryptonCat): CompoundTag.Builder = TamableSerializer.save(entity).apply {
        putInt(TYPE_TAG, entity.data.get(MetadataKeys.Cat.VARIANT))
        putByte(COLLAR_COLOR_TAG, entity.data.get(MetadataKeys.Cat.COLLAR_COLOR).toByte())
    }
}

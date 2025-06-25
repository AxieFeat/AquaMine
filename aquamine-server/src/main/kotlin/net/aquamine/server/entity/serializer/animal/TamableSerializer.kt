package net.aquamine.server.entity.serializer.animal

import net.aquamine.server.entity.animal.KryptonTamable
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.util.nbt.getUUID
import net.aquamine.server.util.nbt.hasUUID
import net.aquamine.server.util.nbt.putNullable
import net.aquamine.server.util.nbt.putUUID
import xyz.axie.nbt.CompoundTag

object TamableSerializer : EntitySerializer<KryptonTamable> {

    private const val OWNER_TAG = "Owner"
    private const val SITTING_TAG = "Sitting"

    override fun load(entity: KryptonTamable, data: CompoundTag) {
        AgeableSerializer.load(entity, data)
        // TODO: Fix this. The vanilla implementation is weird, and this doesn't make any sense. It also needs old
        //  user conversion stuff that we don't have yet.
        val ownerId = if (data.hasUUID(OWNER_TAG)) {
            data.getUUID(OWNER_TAG)
        } else {
            // Here's where we need to get the owner tag as a string and convert it if needed
            null
        }

        if (ownerId != null) {
            try {
                entity.data.set(MetadataKeys.Tamable.OWNER, ownerId)
                entity.isTamed = true
            } catch (_: Throwable) {
                entity.isTamed = false
            }
        }
        entity.isOrderedToSit = data.getBoolean(SITTING_TAG)
        entity.isSitting = entity.isOrderedToSit
    }

    override fun save(entity: KryptonTamable): CompoundTag.Builder = AgeableSerializer.save(entity).apply {
        putNullable(OWNER_TAG, entity.data.get(MetadataKeys.Tamable.OWNER), CompoundTag.Builder::putUUID)
        putBoolean(SITTING_TAG, entity.isOrderedToSit)
    }
}

package net.aquamine.server.entity.serializer.aquatic

import net.aquamine.server.entity.aquatic.KryptonGlowSquid
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.MobSerializer
import xyz.axie.nbt.CompoundTag

object GlowSquidSerializer : EntitySerializer<KryptonGlowSquid> {

    private const val DARK_TICKS_REMAINING_TAG = "DarkTicksRemaining"

    override fun load(entity: KryptonGlowSquid, data: CompoundTag) {
        MobSerializer.load(entity, data)
        entity.remainingDarkTicks = data.getInt(DARK_TICKS_REMAINING_TAG)
    }

    override fun save(entity: KryptonGlowSquid): CompoundTag.Builder = MobSerializer.save(entity).apply {
        putInt(DARK_TICKS_REMAINING_TAG, entity.remainingDarkTicks)
    }
}

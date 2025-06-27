package net.aquamine.server.entity.serializer.monster

import net.aquamine.server.entity.monster.AquaEndermite
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.MobSerializer
import xyz.axie.nbt.CompoundTag

object EndermiteSerializer : EntitySerializer<AquaEndermite> {

    override fun load(entity: AquaEndermite, data: CompoundTag) {
        MobSerializer.load(entity, data)
        entity.remainingLife = data.getInt("Lifetime")
    }

    override fun save(entity: AquaEndermite): CompoundTag.Builder = MobSerializer.save(entity).apply {
        putInt("Lifetime", entity.remainingLife)
    }
}

package net.aquamine.server.entity.serializer.monster

import net.aquamine.server.entity.monster.KryptonEndermite
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.MobSerializer
import xyz.axie.nbt.CompoundTag

object EndermiteSerializer : EntitySerializer<KryptonEndermite> {

    override fun load(entity: KryptonEndermite, data: CompoundTag) {
        MobSerializer.load(entity, data)
        entity.remainingLife = data.getInt("Lifetime")
    }

    override fun save(entity: KryptonEndermite): CompoundTag.Builder = MobSerializer.save(entity).apply {
        putInt("Lifetime", entity.remainingLife)
    }
}

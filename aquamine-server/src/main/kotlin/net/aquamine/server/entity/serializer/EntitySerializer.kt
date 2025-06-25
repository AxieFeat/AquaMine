package net.aquamine.server.entity.serializer

import net.aquamine.server.entity.KryptonEntity
import xyz.axie.nbt.CompoundTag

interface EntitySerializer<E : KryptonEntity> {

    fun load(entity: E, data: CompoundTag)

    fun save(entity: E): CompoundTag.Builder
}

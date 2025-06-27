package net.aquamine.server.entity.serializer

import net.aquamine.server.entity.AquaEntity
import xyz.axie.nbt.CompoundTag

interface EntitySerializer<E : AquaEntity> {

    fun load(entity: E, data: CompoundTag)

    fun save(entity: E): CompoundTag.Builder
}

package net.aquamine.server.entity.components

import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.serializer.EntitySerializer
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.EndTag
import xyz.axie.nbt.MutableListTag

interface SerializableEntity : BaseEntity {

    val serializer: EntitySerializer<out AquaEntity>

    fun load(data: CompoundTag) {
        serializer().load(this as AquaEntity, data)
    }

    fun save(): CompoundTag.Builder = serializer().save(this as AquaEntity)

    fun saveWithPassengers(): CompoundTag.Builder = save().apply {
        if (isVehicle()) {
            val passengerList = MutableListTag.of(ArrayList(), EndTag.ID)
            passengers.forEach {
                if (it !is AquaEntity) return@forEach
                passengerList.add(it.saveWithPassengers().build())
            }
            if (!passengerList.isEmpty()) put("Passengers", passengerList)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun serializer(): EntitySerializer<AquaEntity> = serializer as EntitySerializer<AquaEntity>
}

package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Mooshroom
import net.aquamine.api.entity.animal.type.MooshroomVariant
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.MooshroomSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonMooshroom(world: KryptonWorld) : KryptonCow(world), Mooshroom {

    override val type: KryptonEntityType<KryptonMooshroom>
        get() = KryptonEntityTypes.MOOSHROOM
    override val serializer: EntitySerializer<KryptonMooshroom>
        get() = MooshroomSerializer

    override var variant: MooshroomVariant
        get() = deserializeType(data.get(MetadataKeys.Mooshroom.TYPE))
        set(value) = data.set(MetadataKeys.Mooshroom.TYPE, value.name.lowercase())

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Mooshroom.TYPE, MooshroomVariant.RED.name.lowercase())
    }

    companion object {

        private val TYPE_NAMES = MooshroomVariant.values().associateBy { it.name.lowercase() }

        @JvmStatic
        fun deserializeType(name: String): MooshroomVariant = TYPE_NAMES.getOrDefault(name, MooshroomVariant.RED)
    }
}

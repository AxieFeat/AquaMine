package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Pig
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.PigSerializer
import net.aquamine.server.world.AquaWorld

class AquaPig(world: AquaWorld) : AquaAnimal(world), Pig {

    override val type: AquaEntityType<AquaPig>
        get() = AquaEntityTypes.PIG
    override val serializer: EntitySerializer<AquaPig>
        get() = PigSerializer

    override var isSaddled: Boolean
        get() = data.get(MetadataKeys.Pig.SADDLE)
        set(value) = data.set(MetadataKeys.Pig.SADDLE, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Pig.SADDLE, false)
        data.define(MetadataKeys.Pig.BOOST_TIME, 0)
    }

    companion object {

        private const val DEFAULT_MAX_HEALTH = 10.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.25

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
    }
}

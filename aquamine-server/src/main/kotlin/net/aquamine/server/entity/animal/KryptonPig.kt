package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Pig
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.PigSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonPig(world: KryptonWorld) : KryptonAnimal(world), Pig {

    override val type: KryptonEntityType<KryptonPig>
        get() = KryptonEntityTypes.PIG
    override val serializer: EntitySerializer<KryptonPig>
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
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
    }
}

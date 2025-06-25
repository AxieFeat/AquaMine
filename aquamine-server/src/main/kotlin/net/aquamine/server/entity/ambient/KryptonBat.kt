package net.aquamine.server.entity.ambient

import net.aquamine.api.entity.ambient.Bat
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.ambient.BatSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonBat(world: KryptonWorld) : KryptonAmbientCreature(world), Bat {

    override val type: KryptonEntityType<KryptonBat>
        get() = KryptonEntityTypes.BAT
    override val serializer: EntitySerializer<KryptonBat>
        get() = BatSerializer

    override var isResting: Boolean
        get() = data.getFlag(MetadataKeys.Bat.FLAGS, FLAG_RESTING)
        set(value) = data.setFlag(MetadataKeys.Bat.FLAGS, FLAG_RESTING, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Bat.FLAGS, 0)
    }

    companion object {

        private const val FLAG_RESTING = 0
        private const val DEFAULT_MAX_HEALTH = 6.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes().add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
    }
}

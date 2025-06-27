package net.aquamine.server.entity.ambient

import net.aquamine.api.entity.ambient.Bat
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.ambient.BatSerializer
import net.aquamine.server.world.AquaWorld

class AquaBat(world: AquaWorld) : AquaAmbientCreature(world), Bat {

    override val type: AquaEntityType<AquaBat>
        get() = AquaEntityTypes.BAT
    override val serializer: EntitySerializer<AquaBat>
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
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes().add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
    }
}

package net.aquamine.server.entity.aquatic

import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.world.AquaWorld

open class AquaSquid(world: AquaWorld) : AquaAquaticAnimal(world) {

    override val type: AquaEntityType<AquaSquid>
        get() = AquaEntityTypes.SQUID

    override fun soundVolume(): Float = SOUND_VOLUME

    companion object {

        private const val SOUND_VOLUME = 0.4F
        private const val DEFAULT_MAX_HEALTH = 10.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes().add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
    }
}

package net.aquamine.server.entity.aquatic

import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.world.KryptonWorld

open class KryptonSquid(world: KryptonWorld) : KryptonAquaticAnimal(world) {

    override val type: KryptonEntityType<KryptonSquid>
        get() = KryptonEntityTypes.SQUID

    override fun soundVolume(): Float = SOUND_VOLUME

    companion object {

        private const val SOUND_VOLUME = 0.4F
        private const val DEFAULT_MAX_HEALTH = 10.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes().add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
    }
}

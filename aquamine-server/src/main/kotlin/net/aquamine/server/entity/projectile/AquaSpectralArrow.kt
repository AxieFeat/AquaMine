package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.SpectralArrow
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.SpectralArrowSerializer
import net.aquamine.server.world.AquaWorld

class AquaSpectralArrow(world: AquaWorld) : AquaArrowLike(world), SpectralArrow {

    override val type: AquaEntityType<AquaSpectralArrow>
        get() = AquaEntityTypes.SPECTRAL_ARROW
    override val serializer: EntitySerializer<AquaSpectralArrow>
        get() = SpectralArrowSerializer

    override var duration: Int = DEFAULT_DURATION

    companion object {

        private const val DEFAULT_DURATION = 200
    }
}

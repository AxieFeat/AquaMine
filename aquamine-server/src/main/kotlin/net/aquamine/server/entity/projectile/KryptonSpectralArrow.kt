package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.SpectralArrow
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.SpectralArrowSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonSpectralArrow(world: KryptonWorld) : KryptonArrowLike(world), SpectralArrow {

    override val type: KryptonEntityType<KryptonSpectralArrow>
        get() = KryptonEntityTypes.SPECTRAL_ARROW
    override val serializer: EntitySerializer<KryptonSpectralArrow>
        get() = SpectralArrowSerializer

    override var duration: Int = DEFAULT_DURATION

    companion object {

        private const val DEFAULT_DURATION = 200
    }
}

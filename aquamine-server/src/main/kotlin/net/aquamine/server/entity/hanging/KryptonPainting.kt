package net.aquamine.server.entity.hanging

import net.aquamine.api.entity.hanging.PaintingVariant
import net.aquamine.api.entity.hanging.Painting
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.hanging.PaintingSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonPainting(world: KryptonWorld) : KryptonHangingEntity(world), Painting {

    override val type: KryptonEntityType<KryptonPainting>
        get() = KryptonEntityTypes.PAINTING
    override val serializer: EntitySerializer<KryptonPainting>
        get() = PaintingSerializer

    override var variant: PaintingVariant? = null

    override fun width(): Int = variant?.width ?: 1

    override fun height(): Int = variant?.height ?: 1
}

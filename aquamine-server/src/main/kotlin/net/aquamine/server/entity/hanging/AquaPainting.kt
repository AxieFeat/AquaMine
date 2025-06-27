package net.aquamine.server.entity.hanging

import net.aquamine.api.entity.hanging.PaintingVariant
import net.aquamine.api.entity.hanging.Painting
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.hanging.PaintingSerializer
import net.aquamine.server.world.AquaWorld

class AquaPainting(world: AquaWorld) : AquaHangingEntity(world), Painting {

    override val type: AquaEntityType<AquaPainting>
        get() = AquaEntityTypes.PAINTING
    override val serializer: EntitySerializer<AquaPainting>
        get() = PaintingSerializer

    override var variant: PaintingVariant? = null

    override fun width(): Int = variant?.width ?: 1

    override fun height(): Int = variant?.height ?: 1
}

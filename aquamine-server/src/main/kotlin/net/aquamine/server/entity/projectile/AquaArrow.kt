package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.Arrow
import net.aquamine.api.util.Color
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.ArrowSerializer
import net.aquamine.server.world.AquaWorld

class AquaArrow(world: AquaWorld) : AquaArrowLike(world), Arrow {

    override val type: AquaEntityType<AquaArrow>
        get() = AquaEntityTypes.ARROW
    override val serializer: EntitySerializer<AquaArrow>
        get() = ArrowSerializer

    override var color: Color
        get() = Color(data.get(MetadataKeys.Arrow.COLOR))
        set(value) = data.set(MetadataKeys.Arrow.COLOR, value.encode())

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Arrow.COLOR, -1)
    }
}

package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.Arrow
import net.aquamine.api.util.Color
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.ArrowSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonArrow(world: KryptonWorld) : KryptonArrowLike(world), Arrow {

    override val type: KryptonEntityType<KryptonArrow>
        get() = KryptonEntityTypes.ARROW
    override val serializer: EntitySerializer<KryptonArrow>
        get() = ArrowSerializer

    override var color: Color
        get() = Color(data.get(MetadataKeys.Arrow.COLOR))
        set(value) = data.set(MetadataKeys.Arrow.COLOR, value.encode())

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Arrow.COLOR, -1)
    }
}

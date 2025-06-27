package net.aquamine.server.entity

import net.aquamine.api.effect.particle.ParticleTypes
import net.aquamine.api.entity.AreaEffectCloud
import net.aquamine.api.util.Color
import net.aquamine.server.effect.particle.ParticleOptions
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.AreaEffectCloudSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.world.AquaWorld

class AquaAreaEffectCloud(world: AquaWorld) : AquaEntity(world), AreaEffectCloud {

    override val type: AquaEntityType<AquaAreaEffectCloud>
        get() = AquaEntityTypes.AREA_EFFECT_CLOUD
    override val serializer: EntitySerializer<AquaAreaEffectCloud>
        get() = AreaEffectCloudSerializer

    var age: Int = 0
    override var duration: Int = 0

    override var radius: Float
        get() = data.get(MetadataKeys.AreaEffectCloud.RADIUS)
        set(value) = data.set(MetadataKeys.AreaEffectCloud.RADIUS, value)
    override var color: Color
        get() = Color(data.get(MetadataKeys.AreaEffectCloud.COLOR))
        set(value) = data.set(MetadataKeys.AreaEffectCloud.COLOR, value.encode())

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.AreaEffectCloud.RADIUS, DEFAULT_RADIUS)
        data.define(MetadataKeys.AreaEffectCloud.COLOR, 0)
        data.define(MetadataKeys.AreaEffectCloud.IGNORE_RADIUS, false)
        data.define(MetadataKeys.AreaEffectCloud.PARTICLE, ParticleOptions(ParticleTypes.EFFECT.get(), null))
    }

    companion object {

        private const val DEFAULT_RADIUS = 0.5F
    }
}

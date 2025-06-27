package net.aquamine.server.entity.aquatic

import net.aquamine.api.entity.aquatic.GlowSquid
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.aquatic.GlowSquidSerializer
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.damage.AquaDamageSource

class AquaGlowSquid(world: AquaWorld) : AquaSquid(world), GlowSquid {

    override val type: AquaEntityType<AquaGlowSquid>
        get() = AquaEntityTypes.GLOW_SQUID
    override val serializer: EntitySerializer<AquaGlowSquid>
        get() = GlowSquidSerializer

    override var remainingDarkTicks: Int
        get() = data.get(MetadataKeys.GlowSquid.REMAINING_DARK_TICKS)
        set(value) = data.set(MetadataKeys.GlowSquid.REMAINING_DARK_TICKS, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.GlowSquid.REMAINING_DARK_TICKS, 0)
    }

    override fun damage(source: AquaDamageSource, damage: Float): Boolean {
        val wasDamaged = super.damage(source, damage)
        // Glow squids will stop glowing for 5 seconds when they are damaged.
        if (wasDamaged) remainingDarkTicks = DAMAGED_DARK_TICKS
        return wasDamaged
    }

    companion object {

        private const val DAMAGED_DARK_TICKS = 5 * 20 // 5 seconds in ticks
    }
}

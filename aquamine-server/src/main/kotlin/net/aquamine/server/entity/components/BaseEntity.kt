package net.aquamine.server.entity.components

import net.kyori.adventure.identity.Identity
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.util.TriState
import net.aquamine.server.AquaServer
import net.aquamine.server.entity.Pose
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.world.AquaWorld
import java.util.function.UnaryOperator

interface BaseEntity : BaseDataHolder, NameableTeamMember, Rideable, WaterPushable, Damageable, Interactable {

    override val world: AquaWorld
    override val server: AquaServer
        get() = world.server

    fun isRemoved(): Boolean

    fun maxAirTicks(): Int = DEFAULT_MAX_AIR

    fun isAlive(): Boolean = !isRemoved()

    override fun defineData() {
        data.define(MetadataKeys.Entity.FLAGS, 0)
        data.define(MetadataKeys.Entity.AIR_TICKS, maxAirTicks())
        data.define(MetadataKeys.Entity.CUSTOM_NAME, null)
        data.define(MetadataKeys.Entity.CUSTOM_NAME_VISIBILITY, false)
        data.define(MetadataKeys.Entity.SILENT, false)
        data.define(MetadataKeys.Entity.NO_GRAVITY, false)
        data.define(MetadataKeys.Entity.POSE, Pose.STANDING)
        data.define(MetadataKeys.Entity.FROZEN_TICKS, 0)
    }

    override fun identity(): Identity = Identity.identity(uuid)

    override fun getPermissionValue(permission: String): TriState = TriState.FALSE

    override fun asHoverEvent(op: UnaryOperator<HoverEvent.ShowEntity>): HoverEvent<HoverEvent.ShowEntity> {
        return HoverEvent.showEntity(op.apply(HoverEvent.ShowEntity.of(type.key(), uuid, nameOrDescription())))
    }

    companion object {

        private const val DEFAULT_MAX_AIR = 15 * 20 // 15 seconds in ticks
    }
}

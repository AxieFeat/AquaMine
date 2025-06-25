package net.aquamine.server.entity.components

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.util.TriState
import net.aquamine.api.permission.PermissionFunction
import net.aquamine.api.util.Direction
import net.aquamine.server.util.enumhelper.Directions

interface BasePlayer : BaseEntity, KryptonEquipable, HungerDelegate, AbilitiesDelegate, PlayerAudience, Glider, GameModePlayer {

    val permissionFunction: PermissionFunction

    override val teamRepresentation: Component
        get() = nameOrDescription()
    override val facing: Direction
        get() = Directions.ofPitch(position.pitch.toDouble())

    override val displayName: Component
        get() {
            val componentName = nameOrDescription()
            val result = team?.formatName(componentName) ?: componentName
            return result.style {
                it.clickEvent(ClickEvent.suggestCommand("/tell $name "))
                it.hoverEvent(asHoverEvent())
                it.insertion(name)
            }
        }

    override fun nameOrDescription(): Component = Component.text(name)

    override fun getPermissionValue(permission: String): TriState = permissionFunction.getPermissionValue(permission)
}

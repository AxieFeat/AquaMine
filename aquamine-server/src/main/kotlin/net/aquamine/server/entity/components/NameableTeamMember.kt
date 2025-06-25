package net.aquamine.server.entity.components

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.aquamine.api.entity.Entity
import net.aquamine.api.scoreboard.Team
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.KryptonEntityType

interface NameableTeamMember : Entity {

    override val type: KryptonEntityType<KryptonEntity>

    override val name: String
        get() = PlainTextComponentSerializer.plainText().serialize(nameOrDescription())
    override val displayName: Component
        get() {
            val team = team ?: return nameOrDescription()
            return team.formatName(nameOrDescription()).style {
                it.hoverEvent(asHoverEvent())
                it.insertion(uuid.toString())
            }
        }
    override val teamRepresentation: Component
        get() = Component.text(uuid.toString())
    override val team: Team?
        get() = world.scoreboard.getMemberTeam(teamRepresentation)

    fun nameOrDescription(): Component = customName ?: type.description()
}

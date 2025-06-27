package net.aquamine.server.world.scoreboard

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.aquamine.api.scoreboard.Team
import net.aquamine.api.scoreboard.CollisionRule
import net.aquamine.api.scoreboard.Visibility
import java.util.Collections

class AquaTeam(override val scoreboard: AquaScoreboard, override val name: String) : Team {

    private var _displayName: Component = Component.text(name)
    private var _prefix: Component = Component.empty()
    private var _suffix: Component = Component.empty()
    private var _allowFriendlyFire = true
    private var _canSeeInvisibleMembers = true
    private var _nameTagVisibility = Visibility.ALWAYS
    private var _deathMessageVisibility = Visibility.ALWAYS
    private var _color = NamedTextColor.WHITE
    private var _collisionRule = CollisionRule.ALWAYS
    private val _members = ArrayList<Component>()

    override var displayName: Component
        get() = _displayName
        set(value) {
            _displayName = value
            scoreboard.onTeamUpdated(this)
        }
    override var prefix: Component
        get() = _prefix
        set(value) {
            _prefix = value
            scoreboard.onTeamUpdated(this)
        }
    override var suffix: Component
        get() = _suffix
        set(value) {
            _suffix = value
            scoreboard.onTeamUpdated(this)
        }
    override var allowFriendlyFire: Boolean
        get() = _allowFriendlyFire
        set(value) {
            _allowFriendlyFire = value
            scoreboard.onTeamUpdated(this)
        }
    override var canSeeInvisibleMembers: Boolean
        get() = _canSeeInvisibleMembers
        set(value) {
            _canSeeInvisibleMembers = value
            scoreboard.onTeamUpdated(this)
        }
    override var nameTagVisibility: Visibility
        get() = _nameTagVisibility
        set(value) {
            _nameTagVisibility = value
            scoreboard.onTeamUpdated(this)
        }
    override var deathMessageVisibility: Visibility
        get() = _deathMessageVisibility
        set(value) {
            _deathMessageVisibility = value
            scoreboard.onTeamUpdated(this)
        }
    override var color: NamedTextColor
        get() = _color
        set(value) {
            _color = value
            scoreboard.onTeamUpdated(this)
        }
    override var collisionRule: CollisionRule
        get() = _collisionRule
        set(value) {
            _collisionRule = value
            scoreboard.onTeamUpdated(this)
        }
    override val members: List<Component>
        get() = Collections.unmodifiableList(_members)

    override fun formatName(name: Component): Component = Component.text().append(prefix).append(name).append(suffix).color(color).build()

    override fun addMember(member: Component): Boolean = _members.add(member)

    override fun removeMember(member: Component): Boolean = _members.remove(member)

    class Builder(private val scoreboard: AquaScoreboard, private val name: String) : Team.Builder {

        private var displayName: Component = Component.text(name)
        private var prefix: Component = Component.empty()
        private var suffix: Component = Component.empty()
        private var friendlyFire = true
        private var seeInvisibles = true
        private var nameTags = Visibility.ALWAYS
        private var deathMessages = Visibility.ALWAYS
        private var color = NamedTextColor.WHITE
        private var collision = CollisionRule.ALWAYS
        private val members = HashSet<Component>()

        override fun displayName(name: Component): Team.Builder = apply { displayName = name }

        override fun prefix(prefix: Component): Team.Builder = apply { this.prefix = prefix }

        override fun suffix(suffix: Component): Team.Builder = apply { this.suffix = suffix }

        override fun friendlyFire(value: Boolean): Team.Builder = apply { friendlyFire = value }

        override fun canSeeInvisibleMembers(value: Boolean): Team.Builder = apply { seeInvisibles = value }

        override fun nameTagVisibility(visibility: Visibility): Team.Builder = apply { nameTags = visibility }

        override fun deathMessageVisibility(visibility: Visibility): Team.Builder = apply { deathMessages = visibility }

        override fun color(color: NamedTextColor): Team.Builder = apply { this.color = color }

        override fun collisionRule(rule: CollisionRule): Team.Builder = apply { collision = rule }

        override fun addMember(member: Component): Team.Builder = apply { members.add(member) }

        override fun buildAndRegister(): Team {
            require(scoreboard.getTeam(name) == null) { "A team called '$name' is already registered!" }
            val team = AquaTeam(scoreboard, name)
            team._displayName = displayName
            team._prefix = prefix
            team._suffix = suffix
            team._allowFriendlyFire = friendlyFire
            team._canSeeInvisibleMembers = seeInvisibles
            team._nameTagVisibility = nameTags
            team._deathMessageVisibility = deathMessages
            team._color = color
            team._collisionRule = collision
            team._members.addAll(members)
            scoreboard.addTeam(team)
            return team
        }
    }
}

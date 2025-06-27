package net.aquamine.server.entity.components

import net.aquamine.api.entity.player.Player
import net.aquamine.server.entity.player.Abilities
import net.aquamine.server.packet.out.play.PacketOutAbilities

/**
 * A delegate that moves the API implementations for abilities out of the main
 * AquaPlayer class to make it less cluttered.
 *
 * Note that, due to the way invulnerability works, it does not have an
 * overriding delegate here to abilities.
 */
interface AbilitiesDelegate : NetworkPlayer, Player {

    val abilities: Abilities

    override var isFlying: Boolean
        get() = abilities.flying
        set(value) {
            abilities.flying = value
            updateAbilities()
        }
    override var canFly: Boolean
        get() = abilities.canFly
        set(value) {
            abilities.canFly = value
            updateAbilities()
        }
    override var canInstantlyBuild: Boolean
        get() = abilities.canInstantlyBuild
        set(value) {
            abilities.canInstantlyBuild = value
            updateAbilities()
        }
    override var canBuild: Boolean
        get() = abilities.canBuild
        set(value) {
            abilities.canBuild = value
            updateAbilities()
        }
    override var walkingSpeed: Float
        get() = abilities.walkingSpeed
        set(value) {
            abilities.walkingSpeed = value
            updateAbilities()
        }
    override var flyingSpeed: Float
        get() = abilities.flyingSpeed
        set(value) {
            abilities.flyingSpeed = value
            updateAbilities()
        }

    private fun updateAbilities() {
        connection.send(PacketOutAbilities.create(abilities))
    }
}

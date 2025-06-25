package net.aquamine.server.entity.components

import net.aquamine.api.entity.player.Player
import net.aquamine.server.entity.system.PlayerHungerSystem
import net.aquamine.server.packet.out.play.PacketOutSetHealth

interface HungerDelegate : Player, NetworkPlayer {

    val hungerSystem: PlayerHungerSystem

    override var foodLevel: Int
        get() = hungerSystem.foodLevel
        set(value) {
            hungerSystem.foodLevel = value
            connection.send(PacketOutSetHealth(health, value, foodSaturationLevel))
        }
    override var foodExhaustionLevel: Float
        get() = hungerSystem.exhaustionLevel
        set(value) {
            hungerSystem.exhaustionLevel = value
        }
    override var foodSaturationLevel: Float
        get() = hungerSystem.saturationLevel
        set(value) {
            hungerSystem.saturationLevel = value
            connection.send(PacketOutSetHealth(health, foodLevel, value))
        }
}

package net.aquamine.server.packet.out.play

import net.aquamine.server.entity.player.Abilities
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet

@JvmRecord
data class PacketOutAbilities(
    val isInvulnerable: Boolean,
    val isFlying: Boolean,
    val canFly: Boolean,
    val canInstantlyBuild: Boolean,
    val flyingSpeed: Float,
    val walkingSpeed: Float
) : Packet {

    constructor(reader: BinaryReader) : this(reader.readByte().toInt(), reader.readFloat(), reader.readFloat())

    private constructor(flags: Int, flyingSpeed: Float, walkingSpeed: Float) : this(flags and FLAG_INVULNERABLE != 0, flags and FLAG_FLYING != 0,
        flags and FLAG_CAN_FLY != 0, flags and FLAG_CAN_INSTANTLY_BUILD != 0, flyingSpeed, walkingSpeed)

    override fun write(writer: BinaryWriter) {
        var flags = 0
        if (isInvulnerable) flags = flags or FLAG_INVULNERABLE
        if (isFlying) flags = flags or FLAG_FLYING
        if (canFly) flags = flags or FLAG_CAN_FLY
        if (canInstantlyBuild) flags = flags or FLAG_CAN_INSTANTLY_BUILD
        writer.writeByte(flags.toByte())
        writer.writeFloat(flyingSpeed)
        writer.writeFloat(walkingSpeed)
    }

    companion object {

        private const val FLAG_INVULNERABLE = 0x01
        private const val FLAG_FLYING = 0x02
        private const val FLAG_CAN_FLY = 0x04
        private const val FLAG_CAN_INSTANTLY_BUILD = 0x08

        @JvmStatic
        fun create(abilities: Abilities): PacketOutAbilities {
            return PacketOutAbilities(abilities.invulnerable, abilities.flying, abilities.canFly, abilities.canInstantlyBuild,
                abilities.flyingSpeed, abilities.walkingSpeed)
        }
    }
}

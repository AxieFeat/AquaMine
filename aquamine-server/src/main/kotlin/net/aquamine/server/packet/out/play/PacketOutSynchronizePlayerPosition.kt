package net.aquamine.server.packet.out.play

import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.packet.Packet
import net.aquamine.server.util.random.RandomSource
import java.util.EnumSet

@JvmRecord
data class PacketOutSynchronizePlayerPosition(
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
    val relativeArguments: Set<RelativeArgument> = emptySet(),
    val teleportId: Int = RANDOM.nextInt(RANDOM_TELEPORT_ID_UPPER_BOUND),
    val shouldDismount: Boolean = false
) : Packet {

    constructor(reader: BinaryReader) : this(reader.readDouble(), reader.readDouble(), reader.readDouble(), reader.readFloat(), reader.readFloat(),
        RelativeArgument.unpack(reader.readByte().toInt()), reader.readVarInt(), reader.readBoolean())

    override fun write(writer: BinaryWriter) {
        writer.writeDouble(x)
        writer.writeDouble(y)
        writer.writeDouble(z)
        writer.writeFloat(yaw)
        writer.writeFloat(pitch)
        writer.writeByte(RelativeArgument.pack(relativeArguments).toByte())
        writer.writeVarInt(teleportId)
        writer.writeBoolean(shouldDismount)
    }

    enum class RelativeArgument(private val bit: Int) {

        X(0),
        Y(1),
        Z(2),
        PITCH(3),
        YAW(4);

        private fun mask(): Int = 1 shl bit

        private fun isSet(flags: Int): Boolean = flags and mask() == mask()

        companion object {

            private val VALUES = entries.toTypedArray()

            @JvmStatic
            fun unpack(flags: Int): Set<RelativeArgument> {
                val result = EnumSet.noneOf(RelativeArgument::class.java)
                VALUES.forEach { if (it.isSet(flags)) result.add(it) }
                return result
            }

            @JvmStatic
            fun pack(arguments: Set<RelativeArgument>): Int {
                var result = 0
                arguments.forEach { result = result or it.mask() }
                return result
            }
        }
    }

    companion object {

        private val RANDOM = RandomSource.createThreadSafe()
        private const val RANDOM_TELEPORT_ID_UPPER_BOUND = 1000 // A number I chose because it seems sensible enough

        @JvmStatic
        fun fromPlayer(player: AquaPlayer): PacketOutSynchronizePlayerPosition {
            return PacketOutSynchronizePlayerPosition(player.position.x, player.position.y, player.position.z, player.position.yaw,
                player.position.pitch)
        }
    }
}

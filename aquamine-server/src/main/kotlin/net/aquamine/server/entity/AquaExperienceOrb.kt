package net.aquamine.server.entity

import net.aquamine.api.entity.ExperienceOrb
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.ExperienceOrbSerializer
import net.aquamine.server.packet.Packet
import net.aquamine.server.packet.out.play.PacketOutSpawnExperienceOrb
import net.aquamine.server.world.AquaWorld

class AquaExperienceOrb(world: AquaWorld) : AquaEntity(world), ExperienceOrb {

    override val type: AquaEntityType<AquaExperienceOrb>
        get() = AquaEntityTypes.EXPERIENCE_ORB
    override val serializer: EntitySerializer<AquaExperienceOrb>
        get() = ExperienceOrbSerializer

    var age: Int = 0
    override var count: Int = 1
    override var health: Int = 5
    override var experience: Int = 0
    override var following: AquaPlayer? = null

    override fun getSpawnPacket(): Packet = PacketOutSpawnExperienceOrb.create(this)
}

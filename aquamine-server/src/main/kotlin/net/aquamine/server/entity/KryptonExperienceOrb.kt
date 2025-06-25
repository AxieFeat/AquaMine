package net.aquamine.server.entity

import net.aquamine.api.entity.ExperienceOrb
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.ExperienceOrbSerializer
import net.aquamine.server.packet.Packet
import net.aquamine.server.packet.out.play.PacketOutSpawnExperienceOrb
import net.aquamine.server.world.KryptonWorld

class KryptonExperienceOrb(world: KryptonWorld) : KryptonEntity(world), ExperienceOrb {

    override val type: KryptonEntityType<KryptonExperienceOrb>
        get() = KryptonEntityTypes.EXPERIENCE_ORB
    override val serializer: EntitySerializer<KryptonExperienceOrb>
        get() = ExperienceOrbSerializer

    var age: Int = 0
    override var count: Int = 1
    override var health: Int = 5
    override var experience: Int = 0
    override var following: KryptonPlayer? = null

    override fun getSpawnPacket(): Packet = PacketOutSpawnExperienceOrb.create(this)
}

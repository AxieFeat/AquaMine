package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Endermite
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.monster.EndermiteSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonEndermite(world: KryptonWorld) : KryptonMonster(world), Endermite {

    override val type: KryptonEntityType<KryptonEndermite>
        get() = KryptonEntityTypes.ENDERMITE
    override val serializer: EntitySerializer<KryptonEndermite>
        get() = EndermiteSerializer

    override var life: Int = 2400
    override var remainingLife: Int = life

    override fun tick() {
        super.tick()
        tickLife()
    }

    private fun tickLife() {
        if (!isPersistent) remainingLife--
        if (remainingLife <= 0) remove()
    }

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMonster.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, 8.0)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, 0.25)
            .add(KryptonAttributeTypes.ATTACK_DAMAGE, 2.0)
    }
}

package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Endermite
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.monster.EndermiteSerializer
import net.aquamine.server.world.AquaWorld

class AquaEndermite(world: AquaWorld) : AquaMonster(world), Endermite {

    override val type: AquaEntityType<AquaEndermite>
        get() = AquaEntityTypes.ENDERMITE
    override val serializer: EntitySerializer<AquaEndermite>
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
        fun attributes(): AttributeSupplier.Builder = AquaMonster.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, 8.0)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, 0.25)
            .add(AquaAttributeTypes.ATTACK_DAMAGE, 2.0)
    }
}

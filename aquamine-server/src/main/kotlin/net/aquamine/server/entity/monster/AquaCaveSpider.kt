package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.CaveSpider
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.world.AquaWorld

class AquaCaveSpider(world: AquaWorld) : AquaSpider(world), CaveSpider {

    override val type: AquaEntityType<AquaCaveSpider>
        get() = AquaEntityTypes.CAVE_SPIDER

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaSpider.attributes().add(AquaAttributeTypes.MAX_HEALTH, 12.0)
    }
}

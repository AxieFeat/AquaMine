package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.CaveSpider
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.world.KryptonWorld

class KryptonCaveSpider(world: KryptonWorld) : KryptonSpider(world), CaveSpider {

    override val type: KryptonEntityType<KryptonCaveSpider>
        get() = KryptonEntityTypes.CAVE_SPIDER

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonSpider.attributes().add(KryptonAttributeTypes.MAX_HEALTH, 12.0)
    }
}

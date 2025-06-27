package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Spider
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.world.AquaWorld

open class AquaSpider(world: AquaWorld) : AquaMonster(world), Spider {

    override val type: AquaEntityType<AquaSpider>
        get() = AquaEntityTypes.SPIDER

    override var isClimbing: Boolean
        get() = data.getFlag(MetadataKeys.Spider.FLAGS, 0)
        set(value) = data.setFlag(MetadataKeys.Spider.FLAGS, 0, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Spider.FLAGS, 0)
    }

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMonster.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, 16.0)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, 0.3F.toDouble())
    }
}

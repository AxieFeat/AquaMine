package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Spider
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.world.KryptonWorld

open class KryptonSpider(world: KryptonWorld) : KryptonMonster(world), Spider {

    override val type: KryptonEntityType<KryptonSpider>
        get() = KryptonEntityTypes.SPIDER

    override var isClimbing: Boolean
        get() = data.getFlag(MetadataKeys.Spider.FLAGS, 0)
        set(value) = data.setFlag(MetadataKeys.Spider.FLAGS, 0, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Spider.FLAGS, 0)
    }

    companion object {

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMonster.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, 16.0)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, 0.3F.toDouble())
    }
}

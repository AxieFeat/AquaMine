package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Ocelot
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.OcelotSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonOcelot(world: KryptonWorld) : KryptonAnimal(world), Ocelot {

    override val type: KryptonEntityType<KryptonOcelot>
        get() = KryptonEntityTypes.OCELOT
    override val serializer: EntitySerializer<KryptonOcelot>
        get() = OcelotSerializer

    override var isTrusting: Boolean
        get() = data.get(MetadataKeys.Ocelot.TRUSTING)
        set(value) = data.set(MetadataKeys.Ocelot.TRUSTING, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Ocelot.TRUSTING, false)
    }

    override fun isFood(item: ItemStack): Boolean = TEMPT_INGREDIENTS.contains(item.type)

    companion object {

        private val TEMPT_INGREDIENTS = setOf(ItemTypes.COD.get(), ItemTypes.SALMON.get())
        private const val DEFAULT_MAX_HEALTH = 10.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.3
        private const val DEFAULT_ATTACK_DAMAGE = 3.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(KryptonAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
    }
}

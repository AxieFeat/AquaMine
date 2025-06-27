package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Ocelot
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.OcelotSerializer
import net.aquamine.server.world.AquaWorld

class AquaOcelot(world: AquaWorld) : AquaAnimal(world), Ocelot {

    override val type: AquaEntityType<AquaOcelot>
        get() = AquaEntityTypes.OCELOT
    override val serializer: EntitySerializer<AquaOcelot>
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
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(AquaAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
    }
}

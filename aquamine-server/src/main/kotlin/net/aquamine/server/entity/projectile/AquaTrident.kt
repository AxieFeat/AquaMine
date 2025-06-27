package net.aquamine.server.entity.projectile

import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.entity.projectile.Trident
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.TridentSerializer
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.world.AquaWorld

class AquaTrident(world: AquaWorld) : AquaArrowLike(world), Trident {

    override val type: AquaEntityType<AquaTrident>
        get() = AquaEntityTypes.TRIDENT
    override val serializer: EntitySerializer<AquaTrident>
        get() = TridentSerializer

    override var item: AquaItemStack = DEFAULT_ITEM
    override var dealtDamage: Boolean = false

    override var loyaltyLevel: Int
        get() = data.get(MetadataKeys.Trident.LOYALTY_LEVEL)
        set(value) = data.set(MetadataKeys.Trident.LOYALTY_LEVEL, value)
    override var isEnchanted: Boolean
        get() = data.get(MetadataKeys.Trident.ENCHANTED)
        set(value) = data.set(MetadataKeys.Trident.ENCHANTED, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Trident.LOYALTY_LEVEL, 0)
        data.define(MetadataKeys.Trident.ENCHANTED, false)
    }

    override fun defaultHitGroundSound(): SoundEvent = SoundEvents.TRIDENT_HIT_GROUND.get()

    companion object {

        private val DEFAULT_ITEM = AquaItemStack(ItemTypes.TRIDENT.get())
    }
}

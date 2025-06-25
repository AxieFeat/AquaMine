package net.aquamine.server.entity.projectile

import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.entity.projectile.Trident
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.projectile.TridentSerializer
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.world.KryptonWorld

class KryptonTrident(world: KryptonWorld) : KryptonArrowLike(world), Trident {

    override val type: KryptonEntityType<KryptonTrident>
        get() = KryptonEntityTypes.TRIDENT
    override val serializer: EntitySerializer<KryptonTrident>
        get() = TridentSerializer

    override var item: KryptonItemStack = DEFAULT_ITEM
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

        private val DEFAULT_ITEM = KryptonItemStack(ItemTypes.TRIDENT.get())
    }
}

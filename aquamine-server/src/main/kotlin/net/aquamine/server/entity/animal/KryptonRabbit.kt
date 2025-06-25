package net.aquamine.server.entity.animal

import net.kyori.adventure.sound.Sound
import net.aquamine.api.entity.animal.Rabbit
import net.aquamine.api.entity.animal.type.RabbitVariant
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.RabbitSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonRabbit(world: KryptonWorld) : KryptonAnimal(world), Rabbit {

    override val type: KryptonEntityType<KryptonRabbit>
        get() = KryptonEntityTypes.RABBIT
    override val serializer: EntitySerializer<KryptonRabbit>
        get() = RabbitSerializer

    internal var moreCarrotTicks = 0
    override var variant: RabbitVariant
        get() {
            val id = data.get(MetadataKeys.Rabbit.TYPE)
            // It'll do you a treat mate!
            // Oh yeah?
            // Manky scot's git!
            // I'm warning you!
            // What's he do? Nibble ya bum?
            if (id == KILLER_TYPE) return RabbitVariant.KILLER
            return TYPES.getOrNull(data.get(MetadataKeys.Rabbit.TYPE)) ?: RabbitVariant.BROWN
        }
        set(value) {
            if (value == RabbitVariant.KILLER) {
                data.set(MetadataKeys.Rabbit.TYPE, KILLER_TYPE)
                return
            }
            data.set(MetadataKeys.Rabbit.TYPE, value.ordinal)
        }

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Rabbit.TYPE, 0)
    }

    override fun isFood(item: ItemStack): Boolean = TEMPTING_ITEMS.contains(item.type)

    override fun soundSource(): Sound.Source = if (variant == RabbitVariant.KILLER) Sound.Source.HOSTILE else Sound.Source.NEUTRAL

    companion object {

        private const val KILLER_TYPE = 99
        private val TYPES = RabbitVariant.values()
        private val TEMPTING_ITEMS = setOf(ItemTypes.CARROT.get(), ItemTypes.GOLDEN_CARROT.get(), ItemTypes.DANDELION.get())

        private const val DEFAULT_MAX_HEALTH = 3.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.3

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
    }
}

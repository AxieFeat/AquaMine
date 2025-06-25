package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Chicken
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.ChickenSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonChicken(world: KryptonWorld) : KryptonAnimal(world), Chicken {

    override val type: KryptonEntityType<KryptonChicken>
        get() = KryptonEntityTypes.CHICKEN
    override val serializer: EntitySerializer<KryptonChicken>
        get() = ChickenSerializer

    override var eggCooldownTime: Int = random.nextInt(FIVE_MINUTES_TICKS) + FIVE_MINUTES_TICKS
    override var isJockey: Boolean = false

    override fun isFood(item: ItemStack): Boolean = FOOD_ITEMS.contains(item.type)

    companion object {

        private const val FIVE_MINUTES_TICKS = 6000
        private val FOOD_ITEMS = setOf(
            ItemTypes.WHEAT_SEEDS.get(),
            ItemTypes.MELON_SEEDS.get(),
            ItemTypes.PUMPKIN_SEEDS.get(),
            ItemTypes.BEETROOT_SEEDS.get()
        )

        private const val DEFAULT_MAX_HEALTH = 4.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.25

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes()
            .add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
    }
}

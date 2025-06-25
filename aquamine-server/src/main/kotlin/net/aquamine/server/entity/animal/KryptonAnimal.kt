package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Animal
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.server.entity.KryptonAgeable
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.AnimalSerializer
import net.aquamine.server.world.KryptonWorld
import java.util.UUID

abstract class KryptonAnimal(world: KryptonWorld) : KryptonAgeable(world), Animal {

    override val serializer: EntitySerializer<out KryptonAnimal>
        get() = AnimalSerializer

    final override var loveCause: UUID? = null
    final override var inLoveTime: Int = 0

    override fun isInLove(): Boolean = inLoveTime > 0

    override fun canFallInLove(): Boolean = inLoveTime <= 0

    fun loveCause(): KryptonPlayer? {
        val cause = loveCause ?: return null
        return world.entityManager.getByUUID(cause) as? KryptonPlayer
    }

    fun setLoveCause(cause: KryptonPlayer?) {
        inLoveTime = DEFAULT_IN_LOVE_TIME
        if (cause != null) loveCause = cause.uuid
    }

    override fun canMate(target: Animal): Boolean {
        if (target === this) return false
        if (target.javaClass != javaClass) return false
        return isInLove() && target.isInLove()
    }

    override fun isFood(item: ItemStack): Boolean = item.type === ItemTypes.WHEAT

    companion object {

        private const val DEFAULT_IN_LOVE_TIME = 30 * 20 // 30 seconds in ticks
    }
}

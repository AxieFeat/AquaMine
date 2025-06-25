package net.aquamine.server.shapes.collision

import net.aquamine.api.entity.Hand
import net.aquamine.api.fluid.Fluid
import net.aquamine.api.item.ItemType
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.entity.KryptonLivingEntity
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.shapes.VoxelShape
import java.util.function.Predicate

interface CollisionContext {

    val isDescending: Boolean

    fun isAbove(shape: VoxelShape, x: Int, y: Int, z: Int, canAscend: Boolean): Boolean

    fun isHoldingItem(item: ItemType): Boolean

    fun canStandOnFluid(fluid1: Fluid, fluid2: Fluid): Boolean

    companion object {

        @JvmStatic
        fun empty(): CollisionContext = EntityCollisionContext.EMPTY

        @JvmStatic
        fun of(entity: KryptonEntity): CollisionContext {
            val heldItem = if (entity is KryptonLivingEntity) entity.getHeldItem(Hand.MAIN) else KryptonItemStack.EMPTY
            val canStandOnFluid: Predicate<Fluid> = if (entity is KryptonLivingEntity) {
                Predicate { entity.canStandOnFluid(it) }
            } else {
                Predicate { false }
            }
            return EntityCollisionContext(entity.isSneaking, entity.position.y, heldItem, canStandOnFluid, entity)
        }
    }
}

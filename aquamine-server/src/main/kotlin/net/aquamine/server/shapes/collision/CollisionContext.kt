package net.aquamine.server.shapes.collision

import net.aquamine.api.entity.Hand
import net.aquamine.api.fluid.Fluid
import net.aquamine.api.item.ItemType
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.item.AquaItemStack
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
        fun of(entity: AquaEntity): CollisionContext {
            val heldItem = if (entity is AquaLivingEntity) entity.getHeldItem(Hand.MAIN) else AquaItemStack.EMPTY
            val canStandOnFluid: Predicate<Fluid> = if (entity is AquaLivingEntity) {
                Predicate { entity.canStandOnFluid(it) }
            } else {
                Predicate { false }
            }
            return EntityCollisionContext(entity.isSneaking, entity.position.y, heldItem, canStandOnFluid, entity)
        }
    }
}

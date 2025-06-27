package net.aquamine.server.shapes.collision

import net.aquamine.api.fluid.Fluid
import net.aquamine.api.item.ItemType
import net.aquamine.api.util.Direction
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.shapes.VoxelShape
import net.aquamine.server.util.math.Maths
import java.util.function.Predicate

open class EntityCollisionContext(
    override val isDescending: Boolean,
    private val entityBottom: Double,
    private val heldItem: AquaItemStack,
    private val canStandOnFluid: Predicate<Fluid>,
    val entity: AquaEntity?
) : CollisionContext {

    override fun isAbove(shape: VoxelShape, x: Int, y: Int, z: Int, canAscend: Boolean): Boolean =
        entityBottom > y.toDouble() + shape.max(Direction.Axis.Y) - Maths.EPSILON

    override fun isHoldingItem(item: ItemType): Boolean = item === heldItem.type

    override fun canStandOnFluid(fluid1: Fluid, fluid2: Fluid): Boolean = canStandOnFluid.test(fluid1) && fluid1 !== fluid2

    companion object {

        @JvmField
        val EMPTY: EntityCollisionContext = object : EntityCollisionContext(false, -Double.MAX_VALUE, AquaItemStack.EMPTY, { false }, null) {

            override fun isAbove(shape: VoxelShape, x: Int, y: Int, z: Int, canAscend: Boolean): Boolean = canAscend
        }
    }
}

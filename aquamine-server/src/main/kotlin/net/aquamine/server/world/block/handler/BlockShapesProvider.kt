package net.aquamine.server.world.block.handler

import net.aquamine.api.util.Vec3i
import net.aquamine.server.shapes.Shapes
import net.aquamine.server.shapes.VoxelShape
import net.aquamine.server.shapes.collision.CollisionContext
import net.aquamine.server.world.block.AquaBlock
import net.aquamine.server.world.block.state.AquaBlockState
import net.aquamine.server.world.components.BlockGetter

interface BlockShapesProvider {

    fun isApplicableBlockType(name: String): Boolean

    fun getShape(state: AquaBlockState, world: BlockGetter, pos: Vec3i, context: CollisionContext): VoxelShape {
        return Shapes.block()
    }

    fun getOcclusionShape(state: AquaBlockState, world: BlockGetter, pos: Vec3i): VoxelShape {
        return state.getShape(world, pos)
    }

    fun getCollisionShape(state: AquaBlockState, world: BlockGetter, pos: Vec3i, context: CollisionContext): VoxelShape {
        if (state.block.properties.hasCollision) return state.getShape(world, pos)
        return Shapes.empty()
    }

    fun getVisualShape(state: AquaBlockState, world: BlockGetter, pos: Vec3i, context: CollisionContext): VoxelShape {
        return getCollisionShape(state, world, pos, context)
    }

    fun getInteractionShape(state: AquaBlockState, world: BlockGetter, pos: Vec3i): VoxelShape {
        return Shapes.empty()
    }

    fun getBlockSupportShape(state: AquaBlockState, world: BlockGetter, pos: Vec3i): VoxelShape {
        return getCollisionShape(state, world, pos, CollisionContext.empty())
    }

    fun isCollisionShapeFullBlock(state: AquaBlockState, world: BlockGetter, pos: Vec3i): Boolean {
        return AquaBlock.isShapeFullBlock(state.getCollisionShape(world, pos))
    }

    fun isOcclusionShapeFullBlock(state: AquaBlockState, world: BlockGetter, pos: Vec3i): Boolean {
        return AquaBlock.isShapeFullBlock(state.getOcclusionShape(world, pos))
    }
}

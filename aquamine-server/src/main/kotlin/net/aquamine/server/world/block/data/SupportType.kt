package net.aquamine.server.world.block.data

import net.aquamine.api.util.Direction
import net.aquamine.api.util.Vec3i
import net.aquamine.server.shapes.Shapes
import net.aquamine.server.shapes.util.BooleanOperator
import net.aquamine.server.world.block.KryptonBlock
import net.aquamine.server.world.block.state.KryptonBlockState
import net.aquamine.server.world.components.BlockGetter

enum class SupportType {

    FULL {

        override fun isSupporting(state: KryptonBlockState, world: BlockGetter, pos: Vec3i, face: Direction): Boolean {
            return KryptonBlock.isFaceFull(state.getBlockSupportShape(world, pos), face)
        }
    },
    CENTER {

        private val supportShape = KryptonBlock.box(7.0, 0.0, 7.0, 9.0, 10.0, 9.0)

        override fun isSupporting(state: KryptonBlockState, world: BlockGetter, pos: Vec3i, face: Direction): Boolean {
            return !Shapes.joinIsNotEmpty(state.getBlockSupportShape(world, pos).getFaceShape(face), supportShape, BooleanOperator.ONLY_SECOND)
        }
    },
    RIGID {

        private val supportShape = Shapes.join(Shapes.block(), KryptonBlock.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0), BooleanOperator.ONLY_FIRST)

        override fun isSupporting(state: KryptonBlockState, world: BlockGetter, pos: Vec3i, face: Direction): Boolean {
            return !Shapes.joinIsNotEmpty(state.getBlockSupportShape(world, pos).getFaceShape(face), supportShape, BooleanOperator.ONLY_FIRST)
        }
    };

    abstract fun isSupporting(state: KryptonBlockState, world: BlockGetter, pos: Vec3i, face: Direction): Boolean
}

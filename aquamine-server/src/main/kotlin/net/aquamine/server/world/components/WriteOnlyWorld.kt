package net.aquamine.server.world.components

import net.aquamine.api.util.Vec3i
import net.aquamine.server.entity.KryptonEntity
import net.aquamine.server.world.block.state.KryptonBlockState

interface WriteOnlyWorld {

    fun setBlock(pos: Vec3i, state: KryptonBlockState, flags: Int, recursionLeft: Int): Boolean

    fun setBlock(pos: Vec3i, state: KryptonBlockState, flags: Int): Boolean = setBlock(pos, state, flags, 512)

    fun removeBlock(pos: Vec3i, moving: Boolean): Boolean

    fun destroyBlock(pos: Vec3i, drop: Boolean, entity: KryptonEntity?, recursionLeft: Int): Boolean

    fun destroyBlock(pos: Vec3i, drop: Boolean, entity: KryptonEntity?): Boolean = destroyBlock(pos, drop, entity, 512)

    fun destroyBlock(pos: Vec3i, drop: Boolean): Boolean = destroyBlock(pos, drop, null)
}

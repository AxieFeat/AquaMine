package net.aquamine.server.world.components

import net.aquamine.api.block.entity.BlockEntityContainer
import net.aquamine.api.block.entity.BlockEntityType
import net.aquamine.api.util.Vec3i
import net.aquamine.server.world.block.entity.KryptonBlockEntity

interface BlockEntityGetter : BlockEntityContainer {

    override fun getBlockEntity(x: Int, y: Int, z: Int): KryptonBlockEntity?

    @Suppress("UNCHECKED_CAST")
    fun <T : KryptonBlockEntity> getBlockEntity(pos: Vec3i, type: BlockEntityType<T>): T? {
        val entity = getBlockEntity(pos)
        return if (entity != null && entity.type === type) entity as T else null
    }
}

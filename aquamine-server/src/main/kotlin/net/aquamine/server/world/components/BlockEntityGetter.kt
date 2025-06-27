package net.aquamine.server.world.components

import net.aquamine.api.block.entity.BlockEntityContainer
import net.aquamine.api.block.entity.BlockEntityType
import net.aquamine.api.util.Vec3i
import net.aquamine.server.world.block.entity.AquaBlockEntity

interface BlockEntityGetter : BlockEntityContainer {

    override fun getBlockEntity(x: Int, y: Int, z: Int): AquaBlockEntity?

    @Suppress("UNCHECKED_CAST")
    fun <T : AquaBlockEntity> getBlockEntity(pos: Vec3i, type: BlockEntityType<T>): T? {
        val entity = getBlockEntity(pos)
        return if (entity != null && entity.type === type) entity as T else null
    }
}

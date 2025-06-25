package net.aquamine.server.world.block.entity

import net.aquamine.api.block.Block
import net.aquamine.api.block.entity.BlockEntity
import net.aquamine.api.block.entity.BlockEntityType
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.World

abstract class KryptonBlockEntity(
    override val type: BlockEntityType<*>,
    override val world: World,
    override val position: Vec3i,
    override val block: Block
) : BlockEntity

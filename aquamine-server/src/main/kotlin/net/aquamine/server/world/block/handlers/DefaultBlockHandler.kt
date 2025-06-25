package net.aquamine.server.world.block.handlers

import net.aquamine.server.world.block.handler.BlockHandler
import net.aquamine.server.world.block.handler.BlockPropertiesProvider
import net.aquamine.server.world.block.handler.BlockShapesProvider
import net.aquamine.server.world.block.handler.RedstoneDataProvider

object DefaultBlockHandler : BlockHandler, BlockPropertiesProvider, BlockShapesProvider, RedstoneDataProvider {

    override fun isApplicableBlockType(name: String): Boolean = true
}

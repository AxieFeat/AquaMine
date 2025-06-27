package net.aquamine.server.entity.vehicle

import net.kyori.adventure.text.Component
import net.aquamine.api.entity.vehicle.CommandBlockMinecart
import net.aquamine.api.entity.vehicle.MinecartVariant
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.metadata.MetadataKey
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.vehicle.CommandBlockMinecartSerializer
import net.aquamine.server.world.command.CommandBlockHandler
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.block.AquaBlocks
import net.aquamine.server.world.block.state.AquaBlockState

class AquaCommandBlockMinecart(world: AquaWorld) : AquaMinecartLike(world), CommandBlockMinecart {

    override val type: AquaEntityType<AquaCommandBlockMinecart>
        get() = AquaEntityTypes.COMMAND_BLOCK_MINECART
    override val serializer: EntitySerializer<AquaCommandBlockMinecart>
        get() = CommandBlockMinecartSerializer

    internal val commandBlock = Handler()
    override val variant: MinecartVariant
        get() = MinecartVariant.COMMAND_BLOCK
    override var command: String
        get() = data.get(MetadataKeys.CommandBlockMinecart.COMMAND)
        set(value) = data.set(MetadataKeys.CommandBlockMinecart.COMMAND, value)
    override var lastOutput: Component
        get() = data.get(MetadataKeys.CommandBlockMinecart.LAST_OUTPUT)
        set(value) = data.set(MetadataKeys.CommandBlockMinecart.LAST_OUTPUT, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.CommandBlockMinecart.COMMAND, "")
        data.define(MetadataKeys.CommandBlockMinecart.LAST_OUTPUT, Component.empty())
    }

    override fun onDataUpdate(key: MetadataKey<*>) {
        super.onDataUpdate(key)
        when (key) {
            MetadataKeys.CommandBlockMinecart.LAST_OUTPUT -> commandBlock.lastOutput = lastOutput
            MetadataKeys.CommandBlockMinecart.COMMAND -> commandBlock.command = command
        }
    }

    override fun defaultCustomBlock(): AquaBlockState = AquaBlocks.COMMAND_BLOCK.defaultState

    inner class Handler : CommandBlockHandler() {

        override fun world(): AquaWorld = this@AquaCommandBlockMinecart.world

        override fun createCommandSourceStack(): CommandSourceStack {
            return CommandSourceStack(this, position, world(), name, displayName, world().server, this@AquaCommandBlockMinecart)
        }

        override fun onUpdated() {
            this@AquaCommandBlockMinecart.command = command
            this@AquaCommandBlockMinecart.lastOutput = lastOutput ?: Component.empty()
        }
    }
}

package net.aquamine.server.entity.vehicle

import net.kyori.adventure.text.Component
import net.aquamine.api.entity.vehicle.CommandBlockMinecart
import net.aquamine.api.entity.vehicle.MinecartVariant
import net.aquamine.server.command.CommandSourceStack
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.metadata.MetadataKey
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.vehicle.CommandBlockMinecartSerializer
import net.aquamine.server.world.command.CommandBlockHandler
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.block.KryptonBlocks
import net.aquamine.server.world.block.state.KryptonBlockState

class KryptonCommandBlockMinecart(world: KryptonWorld) : KryptonMinecartLike(world), CommandBlockMinecart {

    override val type: KryptonEntityType<KryptonCommandBlockMinecart>
        get() = KryptonEntityTypes.COMMAND_BLOCK_MINECART
    override val serializer: EntitySerializer<KryptonCommandBlockMinecart>
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

    override fun defaultCustomBlock(): KryptonBlockState = KryptonBlocks.COMMAND_BLOCK.defaultState

    inner class Handler : CommandBlockHandler() {

        override fun world(): KryptonWorld = this@KryptonCommandBlockMinecart.world

        override fun createCommandSourceStack(): CommandSourceStack {
            return CommandSourceStack(this, position, world(), name, displayName, world().server, this@KryptonCommandBlockMinecart)
        }

        override fun onUpdated() {
            this@KryptonCommandBlockMinecart.command = command
            this@KryptonCommandBlockMinecart.lastOutput = lastOutput ?: Component.empty()
        }
    }
}

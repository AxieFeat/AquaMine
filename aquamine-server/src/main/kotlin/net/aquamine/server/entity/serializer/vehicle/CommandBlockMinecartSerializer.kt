package net.aquamine.server.entity.serializer.vehicle

import net.kyori.adventure.text.Component
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.vehicle.KryptonCommandBlockMinecart
import xyz.axie.nbt.CompoundTag

object CommandBlockMinecartSerializer : EntitySerializer<KryptonCommandBlockMinecart> {

    override fun load(entity: KryptonCommandBlockMinecart, data: CompoundTag) {
        MinecartLikeSerializer.load(entity, data)
        entity.commandBlock.load(data)
        entity.command = entity.commandBlock.command
        entity.lastOutput = entity.commandBlock.lastOutput ?: Component.empty()
    }

    override fun save(entity: KryptonCommandBlockMinecart): CompoundTag.Builder = MinecartLikeSerializer.save(entity).apply {
        entity.commandBlock.save(this)
    }
}

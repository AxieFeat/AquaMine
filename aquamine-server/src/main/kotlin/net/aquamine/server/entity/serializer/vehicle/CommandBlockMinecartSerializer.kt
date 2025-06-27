package net.aquamine.server.entity.serializer.vehicle

import net.kyori.adventure.text.Component
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.vehicle.AquaCommandBlockMinecart
import xyz.axie.nbt.CompoundTag

object CommandBlockMinecartSerializer : EntitySerializer<AquaCommandBlockMinecart> {

    override fun load(entity: AquaCommandBlockMinecart, data: CompoundTag) {
        MinecartLikeSerializer.load(entity, data)
        entity.commandBlock.load(data)
        entity.command = entity.commandBlock.command
        entity.lastOutput = entity.commandBlock.lastOutput ?: Component.empty()
    }

    override fun save(entity: AquaCommandBlockMinecart): CompoundTag.Builder = MinecartLikeSerializer.save(entity).apply {
        entity.commandBlock.save(this)
    }
}

package net.aquamine.server.world.data

import net.aquamine.server.entity.player.AquaPlayer
import xyz.axie.nbt.CompoundTag
import java.util.UUID

interface PlayerDataSerializer {

    fun loadById(uuid: UUID): CompoundTag?

    fun load(player: AquaPlayer): CompoundTag?

    fun save(player: AquaPlayer): CompoundTag
}

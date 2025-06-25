package net.aquamine.server.world.data

import net.aquamine.server.entity.player.KryptonPlayer
import xyz.axie.nbt.CompoundTag
import java.util.UUID

interface PlayerDataSerializer {

    fun loadById(uuid: UUID): CompoundTag?

    fun load(player: KryptonPlayer): CompoundTag?

    fun save(player: KryptonPlayer): CompoundTag
}

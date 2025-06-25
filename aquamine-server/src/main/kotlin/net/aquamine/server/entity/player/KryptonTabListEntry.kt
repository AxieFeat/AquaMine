package net.aquamine.server.entity.player

import net.kyori.adventure.text.Component
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.entity.player.TabListEntry
import net.aquamine.api.world.GameMode
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate
import java.util.UUID

class KryptonTabListEntry(
    override val tabList: KryptonTabList,
    override val uuid: UUID,
    override val profile: GameProfile,
    displayName: Component?,
    gameMode: GameMode,
    latency: Int,
    listed: Boolean
) : TabListEntry {

    override var displayName: Component? = displayName
        set(value) {
            field = value
            tabList.updateEntry(PacketOutPlayerInfoUpdate.Action.UPDATE_DISPLAY_NAME, this)
        }
    override var gameMode: GameMode = gameMode
        set(value) {
            field = value
            tabList.updateEntry(PacketOutPlayerInfoUpdate.Action.UPDATE_GAME_MODE, this)
        }
    override var latency: Int = latency
        set(value) {
            field = value
            tabList.updateEntry(PacketOutPlayerInfoUpdate.Action.UPDATE_LATENCY, this)
        }
    override var listed: Boolean = listed
        set(value) {
            field = value
            tabList.updateEntry(PacketOutPlayerInfoUpdate.Action.UPDATE_LISTED, this)
        }

    override fun equals(other: Any?): Boolean {
        return this === other || other is KryptonTabListEntry && uuid == other.uuid
    }

    override fun hashCode(): Int = uuid.hashCode()

    override fun toString(): String = "KryptonTabListEntry(uuid=$uuid, profile=$profile, displayName=$displayName, gameMode=$gameMode, " +
            "latency=$latency, listed=$listed)"

    class Builder(private val tabList: KryptonTabList, private val uuid: UUID, private val profile: GameProfile) : TabListEntry.Builder {

        private var displayName: Component? = null
        private var gameMode = GameMode.SURVIVAL
        private var latency = 0
        private var listed = false

        override fun displayName(name: Component?): Builder = apply { displayName = name }

        override fun gameMode(mode: GameMode): Builder = apply { gameMode = mode }

        override fun latency(latency: Int): Builder = apply { this.latency = latency }

        override fun listed(value: Boolean): Builder = apply { listed = value }

        override fun buildAndRegister(): TabListEntry {
            val entry = KryptonTabListEntry(tabList, uuid, profile, displayName, gameMode, latency, listed)
            tabList.addEntry(entry)
            return entry
        }
    }
}

package net.aquamine.server.entity.player

import net.kyori.adventure.text.Component
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.entity.player.TabList
import net.aquamine.api.entity.player.TabListEntry
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoRemove
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate.Action as PacketAction
import net.aquamine.server.packet.out.play.PacketOutPlayerInfoUpdate.Entry as PacketEntry
import net.aquamine.server.packet.out.play.PacketOutSetTabListHeaderAndFooter
import net.aquamine.server.util.ImmutableLists
import java.util.Collections
import java.util.EnumSet
import java.util.UUID

class KryptonTabList(private val player: KryptonPlayer) : TabList {

    override var header: Component = Component.empty()
    override var footer: Component = Component.empty()

    private val entryMap = HashMap<UUID, TabListEntry>()
    override val entries: Collection<TabListEntry>
        get() = Collections.unmodifiableCollection(entryMap.values)

    override fun setHeaderAndFooter(header: Component, footer: Component) {
        this.header = header
        this.footer = footer
        player.connection.send(PacketOutSetTabListHeaderAndFooter(header, footer))
    }

    override fun getEntry(uuid: UUID): TabListEntry? = entryMap.get(uuid)

    override fun createEntryBuilder(uuid: UUID, profile: GameProfile): TabListEntry.Builder {
        require(getEntry(uuid) == null) { "An entry with UUID $uuid already exists on this list!" }
        return KryptonTabListEntry.Builder(this, uuid, profile)
    }

    fun addEntry(entry: KryptonTabListEntry) {
        entryMap.put(entry.uuid, entry)
        sendAdd(entry)
    }

    override fun removeEntry(uuid: UUID): Boolean {
        entryMap.remove(uuid) ?: return false
        sendRemove(uuid)
        return true
    }

    private fun sendAdd(entry: TabListEntry) {
        val packetEntry = PacketEntry(entry.uuid, entry.profile, entry.listed, entry.latency, entry.gameMode, entry.displayName, null)
        val packet = PacketOutPlayerInfoUpdate(EnumSet.allOf(PacketAction::class.java), ImmutableLists.of(packetEntry))
        player.connection.send(packet)
    }

    private fun sendRemove(uuid: UUID) {
        player.connection.send(PacketOutPlayerInfoRemove(ImmutableLists.of(uuid)))
    }

    fun updateEntry(action: PacketAction, entry: TabListEntry) {
        val packetEntry = PacketEntry(entry.uuid, entry.profile, entry.listed, entry.latency, entry.gameMode, entry.displayName, null)
        val packet = PacketOutPlayerInfoUpdate(EnumSet.of(action), ImmutableLists.of(packetEntry))
        player.connection.send(packet)
    }
}

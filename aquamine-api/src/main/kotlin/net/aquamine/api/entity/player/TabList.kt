package net.aquamine.api.entity.player

import net.kyori.adventure.text.Component
import net.aquamine.api.auth.GameProfile
import java.util.UUID

/**
 * A tab list that is used to display information to players.
 *
 * While the proper name for it is the player list, it is commonly referred to
 * as the tab list because it is generally displayed by pressing
 * the tab key.
 */
interface TabList {

    /**
     * The header of the tab list, shown above the entries.
     */
    val header: Component

    /**
     * The footer of the tab list, shown below the entries.
     */
    val footer: Component

    /**
     * The entries shown in the tab list.
     */
    val entries: Collection<TabListEntry>

    /**
     * Sets the header and footer of the tab list to the given [header]
     * and [footer] values.
     *
     * To reset the header or footer, use [Component.empty].
     *
     * @param header The header.
     * @param footer The footer.
     */
    fun setHeaderAndFooter(header: Component, footer: Component)

    /**
     * Gets the entry with the given [uuid], if it exists.
     *
     * @param uuid The uuid.
     *
     * @return The entry, or null if not present.
     */
    fun getEntry(uuid: UUID): TabListEntry?

    /**
     * Creates a new builder for building a tab list entry.
     *
     * @param uuid The uuid.
     * @param profile The game profile.
     *
     * @return A new builder.
     *
     * @throws IllegalArgumentException If there is already an entry with the
     * same UUID.
     */
    fun createEntryBuilder(uuid: UUID, profile: GameProfile): TabListEntry.Builder

    /**
     * Removes the entry with the given [uuid] from the tab list, returning
     * whether the entry was removed successfully or not.
     *
     * If no entry with the given UUID exists, this will return false.
     *
     * @param uuid The uuid.
     *
     * @return The result of removing the entry.
     */
    fun removeEntry(uuid: UUID): Boolean
}

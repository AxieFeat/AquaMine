package net.aquamine.api.entity.player

import net.kyori.adventure.text.Component
import org.jetbrains.annotations.Contract
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.world.GameMode
import java.util.UUID

/**
 * An entry in a tab list.
 */
interface TabListEntry {

    /**
     * The tab list this entry is registered to.
     */
    val tabList: TabList

    /**
     * The UUID of the entry.
     */
    val uuid: UUID

    /**
     * The game profile used to determine the name, UUID, and displayed skin
     * of the entry.
     */
    val profile: GameProfile

    /**
     * The display name of the entry on the list.
     */
    var displayName: Component?

    /**
     * The displayed game mode of the entry.
     */
    var gameMode: GameMode

    /**
     * The displayed latency, or ping, of the entry.
     */
    var latency: Int

    /**
     * Whether the entry is displayed on the list.
     */
    var listed: Boolean

    /**
     * A builder for building tab list entries.
     */
    interface Builder {

        /**
         * Sets the display name of the entry to the given [name].
         *
         * @param name The name.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun displayName(name: Component?): Builder

        /**
         * Sets the game mode of the entry to the given [mode].
         *
         * @param mode The game mode.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun gameMode(mode: GameMode): Builder

        /**
         * Sets the latency of the entry to the given [latency].
         *
         * @param latency The latency.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun latency(latency: Int): Builder

        /**
         * Sets whether the entry is displayed on the list to the
         * given [value].
         *
         * @param value The value of the setting.
         *
         * @return This builder.
         */
        @Contract("_ -> this", mutates = "this")
        fun listed(value: Boolean): Builder

        /**
         * Builds the new tab list entry and registers it to the tab list that
         * the builder was created from.
         *
         * @return The built entry.
         */
        fun buildAndRegister(): TabListEntry
    }
}

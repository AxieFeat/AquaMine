package net.aquamine.api.scoreboard

import net.kyori.adventure.text.format.NamedTextColor

/**
 * The slot that a scoreboard may be displayed in.
 *
 * @param teamColor The team color associated with this slot, or null if this
 * slot is not associated with a team color.
 */
enum class DisplaySlot(val teamColor: NamedTextColor?) {

    LIST(null),
    SIDEBAR(null),
    BELOW_NAME(null),
    SIDEBAR_TEAM_BLACK(NamedTextColor.BLACK),
    SIDEBAR_TEAM_DARK_BLUE(NamedTextColor.DARK_BLUE),
    SIDEBAR_TEAM_DARK_GREEN(NamedTextColor.DARK_GREEN),
    SIDEBAR_TEAM_DARK_AQUA(NamedTextColor.DARK_AQUA),
    SIDEBAR_TEAM_DARK_RED(NamedTextColor.DARK_RED),
    SIDEBAR_TEAM_DARK_PURPLE(NamedTextColor.DARK_PURPLE),
    SIDEBAR_TEAM_GOLD(NamedTextColor.GOLD),
    SIDEBAR_TEAM_GRAY(NamedTextColor.GRAY),
    SIDEBAR_TEAM_DARK_GRAY(NamedTextColor.DARK_GRAY),
    SIDEBAR_TEAM_BLUE(NamedTextColor.BLUE),
    SIDEBAR_TEAM_GREEN(NamedTextColor.GREEN),
    SIDEBAR_TEAM_AQUA(NamedTextColor.AQUA),
    SIDEBAR_TEAM_RED(NamedTextColor.RED),
    SIDEBAR_TEAM_LIGHT_PURPLE(NamedTextColor.LIGHT_PURPLE),
    SIDEBAR_TEAM_YELLOW(NamedTextColor.YELLOW),
    SIDEBAR_TEAM_WHITE(NamedTextColor.WHITE);

    companion object {

        private val BY_COLOR = DisplaySlot.entries.filter { it.teamColor != null }.associateBy { it.teamColor }

        /**
         * Gets the display slot with the given [color].
         *
         * @param color The color.
         *
         * @return The display slot with the color.
         */
        @JvmStatic
        fun fromColor(color: NamedTextColor): DisplaySlot {
            return BY_COLOR.getValue(color)
        }
    }
}

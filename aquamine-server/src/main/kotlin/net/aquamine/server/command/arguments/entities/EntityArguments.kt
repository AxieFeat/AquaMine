package net.aquamine.server.command.arguments.entities

object EntityArguments {

    @JvmField
    val ALL_SELECTORS: List<String> = listOf("@p", "@r", "@a", "@e", "@s")
    @JvmField
    val PLAYER_SELECTORS: List<String> = listOf("@p", "@r", "@a", "@s")
    @JvmField
    val SINGLE_PLAYER_SELECTORS: List<String> = listOf("@p", "@r", "@s")
    @JvmField
    val VALID: List<String> = listOf(
        "x", "y", "z", "distance", "dx", "dy", "dz", "scores", "tag", "team", "limit", "sort", "level", "gamemode", "name", "x_rotation",
        "y_rotation", "type", "nbt", "advancements", "predicate"
    )
    @JvmField
    val EXCLUDE_ARGUMENTS: List<String> = listOf("team", "tag", "gamemode", "name", "predicate")

    enum class Sorter {

        /**
         * Sort by increasing distance.
         */
        NEAREST,

        /**
         * Sort by decreasing distance.
         */
        FURTHEST,

        /**
         * Sort randomly.
         */
        RANDOM,

        /**
         * Sort by time created.
         */
        ARBITRARY;

        companion object {

            private val BY_NAME = entries.associateBy { it.name.lowercase() }

            /**
             * Gets the sorter with the given [name].
             */
            @JvmStatic
            fun fromName(name: String): Sorter? = BY_NAME.get(name)
        }
    }
}

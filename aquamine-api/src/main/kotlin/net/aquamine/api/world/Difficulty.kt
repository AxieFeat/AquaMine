package net.aquamine.api.world

import net.kyori.adventure.translation.Translatable

/**
 * Represents the difficulty of a world. That being a measure of how difficult
 * it is to play the game (though this is not always accurate).
 */
enum class Difficulty : Translatable {

    /**
     * In peaceful mode, no hostile monsters will spawn in the world.
     *
     * Players will also regain health over time, and the hunger bar
     * does not deplete.
     */
    PEACEFUL,

    /**
     * In easy mode, hostile mobs spawn at normal rates, but they deal less
     * damage than in normal difficulty. The hunger bar does deplete, and
     * starving can deal up to 5 hearts of damage.
     */
    EASY,

    /**
     * In normal mode, hostile mobs spawn at normal rates, and they deal normal
     * amounts of damage. The hunger bar does deplete, and starving can deal up
     * to 9.5 hearts of damage.
     */
    NORMAL,

    /**
     * In hard mode, hostile mobs spawn at normal rates, but they deal much
     * greater damage than in normal difficulty. The hunger bar does deplete,
     * and starving can kill players.
     */
    HARD;

    override fun translationKey(): String = "options.difficulty.${name.lowercase()}"
}

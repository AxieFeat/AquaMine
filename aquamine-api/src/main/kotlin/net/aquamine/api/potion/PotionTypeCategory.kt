package net.aquamine.api.potion

import net.kyori.adventure.text.format.NamedTextColor

/**
 * Represents a category of [PotionType] and its effect on an entity.
 *
 * @property color The colour the text will appear.
 */
enum class PotionTypeCategory(val color: NamedTextColor) {

    /**
     * Beneficial effects that positively impact an entity, such as Regeneration,
     * Absorption, or Fire Resistance.
     */
    BENEFICIAL(NamedTextColor.BLUE),

    /**
     * Harmful effects that negatively impact an entity, such as Blindness, Wither,
     * or Levitation.
     */
    HARMFUL(NamedTextColor.RED),

    /**
     * Neutral effects that have neither a positive nor negative effect on an
     * entity, such as Glowing or Bad Omen.
     */
    NEUTRAL(NamedTextColor.BLUE);

    companion object {

        /**
         * Get potion category by name.
         *
         * @param name Name of category.
         *
         * @return [PotionTypeCategory] or null if not found.
         */
        @JvmStatic
        fun from(name: String): PotionTypeCategory? {
            return entries.find { it.name == name }
        }

    }

}

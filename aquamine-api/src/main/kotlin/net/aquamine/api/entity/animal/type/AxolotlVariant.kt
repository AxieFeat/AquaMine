package net.aquamine.api.entity.animal.type

/**
 * A variant of an axolotl.
 */
enum class AxolotlVariant {

    LUCY,
    WILD,
    GOLD,
    CYAN,
    BLUE;

    /**
     * Gets whether this axolotl variant is common to find.
     *
     * Currently, only the blue variant has this property set to false. All
     * other variants are common.
     *
     * @return `true` if common, `false` otherwise.
     */
    fun isCommon(): Boolean = this != BLUE
}

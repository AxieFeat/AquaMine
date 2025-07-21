package net.aquamine.api.potion

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.aquamine.api.util.Color
import net.kyori.adventure.key.Keyed
import net.kyori.adventure.translation.Translatable

/**
 * Represents a type of potion.
 */
@CataloguedBy(PotionTypes::class)
@ImmutableType
interface PotionType : Translatable, Keyed {

    /**
     * The category of the potion.
     */
    val category: PotionTypeCategory

    /**
     * The particles color of the potion.
     */
    val color: Color

}
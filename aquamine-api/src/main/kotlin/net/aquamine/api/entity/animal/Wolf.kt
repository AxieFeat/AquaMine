package net.aquamine.api.entity.animal

import net.aquamine.api.item.data.DyeColor

/**
 * A wolf.
 */
interface Wolf : Tamable {

    /**
     * If this wolf is currently angry.
     */
    var isAngry: Boolean

    /**
     * The colour of this wolf's collar.
     */
    var collarColor: DyeColor

    /**
     * If this wolf is currently begging for food.
     */
    var isBeggingForFood: Boolean
}

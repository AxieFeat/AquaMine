package net.aquamine.api.entity.animal

import net.aquamine.api.entity.animal.type.MooshroomVariant

/**
 * A mooshroom.
 */
interface Mooshroom : Cow {

    /**
     * The variant of this mooshroom.
     */
    var variant: MooshroomVariant
}

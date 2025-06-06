package net.aquamine.api.entity.animal

import net.aquamine.api.entity.animal.type.ParrotVariant

/**
 * A parrot.
 */
interface Parrot : Tamable {

    /**
     * The variant of this parrot.
     */
    var variant: ParrotVariant
}

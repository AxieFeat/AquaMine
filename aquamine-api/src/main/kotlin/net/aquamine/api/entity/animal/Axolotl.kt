package net.aquamine.api.entity.animal

import net.aquamine.api.entity.animal.type.AxolotlVariant
import net.aquamine.api.entity.Bucketable

/**
 * An axolotl.
 */
interface Axolotl : Animal, Bucketable {

    /**
     * The variant of this axolotl.
     */
    var variant: AxolotlVariant

    /**
     * If this axolotl is currently playing dead.
     */
    var isPlayingDead: Boolean
}

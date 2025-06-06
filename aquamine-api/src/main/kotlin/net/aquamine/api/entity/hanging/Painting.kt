package net.aquamine.api.entity.hanging

/**
 * A painting.
 */
interface Painting : HangingEntity {

    /**
     * The variant of this painting, or null if this painting has no variant.
     */
    var variant: PaintingVariant?
}

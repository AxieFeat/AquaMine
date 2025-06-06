package net.aquamine.api.entity.hanging

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed

/**
 * A variant of a painting.
 *
 * This determines what image will actually appear on the painting when it is
 * rendered by the client.
 */
@CataloguedBy(PaintingVariants::class)
@ImmutableType
interface PaintingVariant : Keyed {

    /**
     * The width of the image.
     *
     * This is measured in pixels, not blocks, and the pixel measurements are
     * relative to the default texture pack, which renders 16x16 block
     * textures, so a painting that is 16x16 is exactly one block wide.
     */
    val width: Int

    /**
     * The height of the image.
     *
     * This is measured in pixels, not blocks, and the pixel measurements are
     * relative to the default texture pack, which renders 16x16 block
     * textures, so a painting that is 16x16 is exactly one block wide.
     */
    val height: Int
}

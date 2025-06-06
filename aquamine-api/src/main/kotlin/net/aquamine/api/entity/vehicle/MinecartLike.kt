package net.aquamine.api.entity.vehicle

import net.aquamine.api.block.BlockState

/**
 * Something that shares some (or all) functionality with that of a [Minecart].
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface MinecartLike : DamageableVehicle {

    /**
     * The variant of this minecart like an object.
     */
    val variant: MinecartVariant

    /**
     * If the custom block is shown on the minecart.
     */
    @get:JvmName("showCustomBlock")
    var showCustomBlock: Boolean

    /**
     * The custom block state shown within the minecart.
     */
    var customBlock: BlockState

    /**
     * The offset of the custom block.
     */
    var customBlockOffset: Int
}

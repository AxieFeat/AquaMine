package net.aquamine.api.block.entity

import net.aquamine.api.util.Vec3i

/**
 * A structure block.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface Structure : BlockEntity {

    /**
     * The position of the structure.
     */
    var structurePosition: Vec3i

    /**
     * The size of the structure.
     */
    var size: Vec3i

    /**
     * The author of the structure.
     */
    var author: String

    /**
     * Whether the structure block is powered.
     */
    var isPowered: Boolean

    /**
     * Whether the bounding box of the structure block is visible.
     */
    @get:JvmName("showBoundingBox")
    var showBoundingBox: Boolean

    /**
     * Whether the air within the structure should be visible.
     */
    @get:JvmName("showAir")
    var showAir: Boolean

    /**
     * Whether this structure block should ignore entities.
     */
    @get:JvmName("ignoreEntities")
    var ignoreEntities: Boolean

    /**
     * The seed of the structure to be generated.
     */
    var seed: Long

    /**
     * The integrity of the structure.
     *
     * This determines how complete the structure that gets placed will be.
     */
    var integrity: Double
}

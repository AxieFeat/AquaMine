package net.aquamine.api.entity.projectile

import net.aquamine.api.block.BlockState

/**
 * Something that shares some (or all) functionality with that of an [Arrow].
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface ArrowLike : Projectile {

    /**
     * The base damage that this arrow like object will do to an entity it
     * comes in to contact with.
     */
    var baseDamage: Double

    /**
     * The block this arrow like object is currently stuck in, or null if this
     * object is not currently stuck in a block.
     */
    var stuckInBlock: BlockState?

    /**
     * If this arrow like object is currently stuck in the ground.
     */
    var isInGround: Boolean

    /**
     * If this arrow like an object is critical.
     */
    var isCritical: Boolean

    /**
     * If this arrow like an object ignores physics.
     */
    var ignoresPhysics: Boolean

    /**
     * If this arrow like object was shot from a crossbow.
     */
    @get:JvmName("wasShotFromCrossbow")
    var wasShotFromCrossbow: Boolean

    /**
     * The number of remaining times that this arrow like object can pierce
     * through an entity.
     *
     * When this value reaches 0, it will no longer pierce through entities.
     */
    var piercingLevel: Int

    /**
     * The current pickup state of this arrow like an object.
     */
    var pickupRule: PickupRule

    /**
     * A rule that determines whether an arrow like an object can be picked up.
     */
    enum class PickupRule {

        /**
         * The arrow like object cannot ever be picked up.
         */
        DISALLOWED,

        /**
         * The arrow like object can always be picked up.
         */
        ALLOWED,

        /**
         * The arrow like object can only be picked up in creative mode.
         */
        CREATIVE_ONLY
    }
}

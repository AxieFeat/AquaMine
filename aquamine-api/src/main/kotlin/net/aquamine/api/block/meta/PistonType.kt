package net.aquamine.api.block.meta

/**
 * Indicates the type of piston a piston block this property is applied to
 * represents.
 */
enum class PistonType {

    /**
     * Normal pistons will push blocks on extension and do nothing on
     * retraction.
     */
    NORMAL,

    /**
     * Sticky pistons will push blocks on extension and pull blocks on
     * retraction, as blocks will stick to them.
     */
    STICKY
}

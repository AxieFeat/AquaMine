package net.aquamine.api.block.meta

/**
 * Indicates the phase that a sculk sensor this property is applied to is
 * currently in.
 */
// TODO: Find out about sculk sensor phases
@Suppress("UndocumentedPublicProperty")
enum class SculkSensorPhase {

    INACTIVE,
    ACTIVE,
    COOLDOWN
}

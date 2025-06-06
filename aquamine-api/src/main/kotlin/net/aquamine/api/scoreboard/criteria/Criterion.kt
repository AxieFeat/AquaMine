package net.aquamine.api.scoreboard.criteria

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.scoreboard.ObjectiveRenderType

/**
 * A criterion for a scoreboard objective to be displayed.
 */
@ImmutableType
interface Criterion {

    /**
     * The name of this criterion.
     */
    val name: String

    /**
     * If this criterion is mutable.
     */
    val isMutable: Boolean

    /**
     * The render type of this criterion.
     */
    val renderType: ObjectiveRenderType
}

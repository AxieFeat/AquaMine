package net.aquamine.server.world.scoreboard

import net.aquamine.api.scoreboard.ObjectiveRenderType
import net.aquamine.api.scoreboard.criteria.Criterion

open class KryptonCriterion(
    override val name: String,
    override val isMutable: Boolean,
    override val renderType: ObjectiveRenderType
) : Criterion {

    constructor(name: String) : this(name, false, ObjectiveRenderType.INTEGER)
}

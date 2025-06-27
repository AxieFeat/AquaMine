package net.aquamine.server.world.scoreboard

import net.kyori.adventure.key.Key
import net.aquamine.api.scoreboard.ObjectiveRenderType
import net.aquamine.api.scoreboard.criteria.KeyedCriterion

class AquaKeyedCriterion(
    private val key: Key,
    name: String,
    isMutable: Boolean,
    renderType: ObjectiveRenderType
) : AquaCriterion(name, isMutable, renderType), KeyedCriterion {

    override fun key(): Key = key

    override fun toString(): String = "KeyedCriterion(name=$name, isMutable=$isMutable, renderType=$renderType)"
}

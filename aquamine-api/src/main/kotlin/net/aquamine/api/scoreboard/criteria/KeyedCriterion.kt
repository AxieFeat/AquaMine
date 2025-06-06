package net.aquamine.api.scoreboard.criteria

import net.aquamine.annotations.CataloguedBy
import net.aquamine.annotations.ImmutableType
import net.kyori.adventure.key.Keyed

/**
 * A criterion for a scoreboard objective to be displayed.
 */
@CataloguedBy(Criteria::class)
@ImmutableType
interface KeyedCriterion : Criterion, Keyed

package net.aquamine.api.entity

import net.aquamine.api.entity.ai.goal.GoalSelector
import net.aquamine.api.entity.ai.pathfinding.Navigator

/**
 * An entity with simple artificial intelligence that can drop items.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
interface Mob : LivingEntity, Equipable {

    /**
     * If this mob is persistent (will have its data saved on removal).
     */
    var isPersistent: Boolean

    /**
     * If this mob can pick up loot.
     *
     * For example, if this mob can wear armor/use weapons, it picks up.
     */
    @get:JvmName("canPickUpLoot")
    var canPickUpLoot: Boolean

    /**
     * If this mob is hostile.
     */
    var isAggressive: Boolean

    /**
     * The main hand of this mob.
     */
    var mainHand: MainHand

    /**
     * If this mob has artificial intelligence.
     */
    @get:JvmName("hasAI")
    var hasAI: Boolean

    /**
     * The goal selector for selecting goals and targets.
     */
    val goalSelector: GoalSelector

    /**
     * The navigator used for pathfinding.
     */
    val navigator: Navigator
}

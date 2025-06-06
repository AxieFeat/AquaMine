package net.aquamine.api.entity.ai.goal

import net.aquamine.api.entity.Entity

/**
 * Something that selects goals for an entity.
 */
interface GoalSelector {

    /**
     * All the goals that this selector will process.
     */
    val availableGoals: Collection<Goal>

    /**
     * All the target finders that this selector will try to find targets
     * with.
     *
     * This works like a fallback system. It will search all the finders in
     * order and use the first one that finds a target.
     */
    val targetFinders: Collection<TargetFinder>

    /**
     * Adds the given [goal] to this goal selector with the given [priority].
     *
     * The priority system is very simple. The lower the priority, the higher
     * the goal is on the list of goals to be executed.
     *
     * For example, a goal with priority 0 will be executed before a goal with
     * priority 1.
     *
     * @param priority The priority of the goal.
     * @param goal The goal to add.
     */
    fun addGoal(priority: Int, goal: Goal)

    /**
     * Removes the given [goal] from this goal selector.
     *
     * @param goal The goal to remove.
     */
    fun removeGoal(goal: Goal)

    /**
     * Adds the given target [finder] to this goal selector with the
     * given [priority].
     *
     * The priority system is very simple. The lower the priority, the higher
     * the target finder is on the list when searching for targets.
     *
     * For example, a target finder with priority 0 will be queried for a
     * target before a target finder with priority 1.
     *
     * @param priority The priority of the target finder.
     * @param finder The target finder to add.
     */
    fun addTargetFinder(priority: Int, finder: TargetFinder)

    /**
     * Removes the given target [finder] from this goal selector.
     *
     * @param finder The target finder to remove.
     */
    fun removeTargetFinder(finder: TargetFinder)

    /**
     * Finds the target for this goal selector, trying all target finders
     * currently in use until it finds one that returns a non-null target.
     *
     * This is intended to be used by goals to easily find targets without
     * having to search the finders manually.
     *
     * This will return null if no target could be found. This could be
     * because there are no target finders in use or because none of the
     * target finders produced a non-null target.
     *
     * @return The target, or null if no target could be found.
     */
    fun findTarget(): Entity?
}

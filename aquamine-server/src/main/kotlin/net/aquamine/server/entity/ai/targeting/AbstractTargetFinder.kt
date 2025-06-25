package net.aquamine.server.entity.ai.targeting

import net.aquamine.api.entity.ai.goal.TargetFinder
import net.aquamine.api.util.Position
import net.aquamine.server.entity.KryptonLivingEntity
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.player.KryptonPlayer

@Suppress("UnusedPrivateMember")
abstract class AbstractTargetFinder(
    protected val mob: KryptonMob,
    protected val mustSee: Boolean,
    private val mustReach: Boolean
) : TargetFinder {

    protected var targetMob: KryptonMob? = null

    override fun shouldRemove(): Boolean {
        var target = mob.target()
        if (target == null) target = targetMob
        if (target == null) return true
        if (!mob.canAttack(target)) return true

        // Mobs on the same team won't target each other
        val team = mob.team
        val targetTeam = target.team
        if (team != null && targetTeam == team) return true

        val followRange = getFollowRange()
        if (mob.position.distanceSquared(target.position) > followRange * followRange) return true

        // TODO: Check line of sight if must see
        mob.setTarget(target)
        return false
    }

    override fun onRemove() {
        mob.setTarget(null)
    }

    protected open fun getFollowRange(): Double = mob.attributes.getValue(KryptonAttributeTypes.FOLLOW_RANGE)

    protected fun <T : KryptonLivingEntity> findNearestEntity(nearby: Collection<T>, conditions: TargetingConditions, attacker: KryptonLivingEntity?,
                                                              position: Position): T? {
        var nearestDistance = Double.MAX_VALUE
        var result: T? = null

        for (entity in nearby) {
            if (!conditions.canTarget(attacker, entity)) continue
            val distance = entity.position.distanceSquared(position)
            if (distance < nearestDistance) {
                nearestDistance = distance
                result = entity
            }
        }
        return result
    }

    protected fun findNearestPlayer(conditions: TargetingConditions, attacker: KryptonLivingEntity?, position: Position): KryptonPlayer? {
        return findNearestEntity(mob.world.players, conditions, attacker, position)
    }
}

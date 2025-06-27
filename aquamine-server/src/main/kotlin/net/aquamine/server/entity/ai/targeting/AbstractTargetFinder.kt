package net.aquamine.server.entity.ai.targeting

import net.aquamine.api.entity.ai.goal.TargetFinder
import net.aquamine.api.util.Position
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.player.AquaPlayer

@Suppress("UnusedPrivateMember")
abstract class AbstractTargetFinder(
    protected val mob: AquaMob,
    protected val mustSee: Boolean,
    private val mustReach: Boolean
) : TargetFinder {

    protected var targetMob: AquaMob? = null

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

    protected open fun getFollowRange(): Double = mob.attributes.getValue(AquaAttributeTypes.FOLLOW_RANGE)

    protected fun <T : AquaLivingEntity> findNearestEntity(nearby: Collection<T>, conditions: TargetingConditions, attacker: AquaLivingEntity?,
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

    protected fun findNearestPlayer(conditions: TargetingConditions, attacker: AquaLivingEntity?, position: Position): AquaPlayer? {
        return findNearestEntity(mob.world.players, conditions, attacker, position)
    }
}

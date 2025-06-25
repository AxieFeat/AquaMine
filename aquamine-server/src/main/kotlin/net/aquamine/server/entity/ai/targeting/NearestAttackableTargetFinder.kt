package net.aquamine.server.entity.ai.targeting

import net.aquamine.api.entity.Entity
import net.aquamine.api.util.BoundingBox
import net.aquamine.server.entity.KryptonLivingEntity
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.player.KryptonPlayer
import java.util.function.Predicate

open class NearestAttackableTargetFinder<T : KryptonLivingEntity>(
    mob: KryptonMob,
    private val targetType: Class<T>,
    randomInterval: Int,
    mustSee: Boolean,
    mustReach: Boolean,
    selector: Predicate<KryptonLivingEntity>?
) : AbstractTargetFinder(mob, mustSee, mustReach) {

    private val randomInterval = -Math.floorDiv(-randomInterval, 2)
    private val conditions = TargetingConditions.combat().range(getFollowRange()).selector(selector).build()

    constructor(mob: KryptonMob, targetType: Class<T>, mustSee: Boolean) : this(mob, targetType, 10, mustSee, false, null)

    constructor(mob: KryptonMob, targetType: Class<T>, mustSee: Boolean, mustReach: Boolean) : this(mob, targetType, 10, mustSee, mustReach, null)

    override fun canUse(): Boolean {
        return randomInterval <= 0 || mob.random.nextInt(randomInterval) == 0
    }

    override fun findTarget(): Entity? {
        val target = findNearestTarget() ?: return null
        mob.setTarget(target)
        return target
    }

    protected fun getSearchArea(distance: Double): BoundingBox = mob.boundingBox.inflate(distance, 4.0, distance)

    private fun findNearestTarget(): KryptonLivingEntity? {
        if (KryptonPlayer::class.java.isAssignableFrom(targetType)) {
            return findNearestPlayer(conditions, mob, mob.position)
        }
        val searchArea = getSearchArea(getFollowRange())
        val nearby = mob.world.getEntitiesOfType(targetType) { it.boundingBox.intersects(searchArea) }
        return findNearestEntity(nearby, conditions, mob, mob.position)
    }
}

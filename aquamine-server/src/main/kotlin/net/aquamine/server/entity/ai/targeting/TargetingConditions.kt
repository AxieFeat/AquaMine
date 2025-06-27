package net.aquamine.server.entity.ai.targeting

import net.aquamine.api.entity.EquipmentSlot
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.world.Difficulty
import net.aquamine.server.entity.AquaArmorStand
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.item.AquaItemStack
import java.util.function.Predicate
import kotlin.math.max

@Suppress("UnusedPrivateMember")
class TargetingConditions(
    private val isCombat: Boolean,
    private val range: Double,
    private val checkLineOfSight: Boolean,
    private val testInvisible: Boolean,
    private val selector: Predicate<AquaLivingEntity>?
) {

    fun canTarget(attacker: AquaLivingEntity?, target: AquaLivingEntity): Boolean {
        if (attacker === target) return false // Can't attack ourselves
        if (!target.canBeSeenByAnyone()) return false // Target cannot be seen at all by anyone
        if (selector != null && !selector.test(target)) return false // Target doesn't match the selector

        if (attacker == null) {
            if (!isCombat) return true
            // Combat targeting isn't allowed if the target cannot be seen as an enemy or the difficulty is peaceful
            return target.canBeSeenAsEnemy() && target.world.difficulty != Difficulty.PEACEFUL
        }

        if (isCombat && !attacker.canAttack(target)) return false

        if (range > 0.0) {
            val visiblePercent = if (testInvisible) getVisibilityPercent(target, attacker) else 1.0
            val newRange = max(range * visiblePercent, 2.0)
            val distance = attacker.position.distanceSquared(target.position)
            if (distance > newRange * newRange) return false // Out of range
        }

        // TODO: Check mob has line of sight if required
        return true
    }

    private fun getVisibilityPercent(entity: AquaLivingEntity, viewer: AquaLivingEntity): Double {
        var result = 1.0

        // Hostile mobs are less likely to see targets when they are sneaking
        if (entity.isSneaking) result *= 0.8

        // Hostile mobs can only see visible players when they are wearing enough armour
        if (entity.isInvisible) {
            var armorCoverPercentage = getArmorCoverPercentage(entity)
            if (armorCoverPercentage < 0.1F) armorCoverPercentage = 0.1F
            result *= 0.7 * armorCoverPercentage
        }

        // The detection range of hostile mobs is halved when the target is wearing a head of the mob's type
        val head = entity.getEquipment(EquipmentSlot.HEAD)
        if (hasHeadForType(viewer.type, head)) result *= 0.5

        return result
    }

    private fun getArmorCoverPercentage(entity: AquaLivingEntity): Float {
        val armor = when (entity) {
            is AquaArmorStand -> entity.armorItems
            is AquaMob -> entity.armorItems
            is AquaPlayer -> entity.inventory.armor
            else -> return 0F
        }

        var count = 0
        var nonEmpty = 0
        for (item in armor) {
            if (!item.isEmpty()) nonEmpty++
            count++
        }

        return if (count > 0) nonEmpty.toFloat() / count.toFloat() else 0F
    }

    private fun hasHeadForType(type: AquaEntityType<*>, head: AquaItemStack): Boolean {
        return type === AquaEntityTypes.SKELETON && head.eq(ItemTypes.SKELETON_SKULL.get()) ||
                type === AquaEntityTypes.ZOMBIE && head.eq(ItemTypes.ZOMBIE_HEAD.get()) ||
//                type === KryptonEntityTypes.PIGLIN && head.eq(ItemTypes.PIGLIN_HEAD.get()) ||
//                type === KryptonEntityTypes.PIGLIN_BRUTE && head.eq(ItemTypes.PIGLIN_HEAD.get()) ||
                type === AquaEntityTypes.CREEPER && head.eq(ItemTypes.CREEPER_HEAD.get())
    }

    class Builder(private val combat: Boolean) {

        private var range = -1.0
        private var lineOfSight = true
        private var invisibles = true
        private var selector: Predicate<AquaLivingEntity>? = null

        fun range(amount: Double): Builder = apply { range = amount }

        fun ignoreLineOfSight(): Builder = apply { lineOfSight = false }

        fun ignoreInvisibles(): Builder = apply { invisibles = false }

        fun selector(value: Predicate<AquaLivingEntity>?): Builder = apply { selector = value }

        fun build(): TargetingConditions = TargetingConditions(combat, range, lineOfSight, invisibles, selector)
    }

    companion object {

        @JvmStatic
        fun combat(): Builder = Builder(true)

        @JvmStatic
        fun nonCombat(): Builder = Builder(false)
    }
}

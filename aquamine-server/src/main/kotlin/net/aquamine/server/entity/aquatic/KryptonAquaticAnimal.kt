package net.aquamine.server.entity.aquatic

import net.aquamine.api.entity.aquatic.AquaticAnimal
import net.aquamine.api.world.damage.type.DamageTypes
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.world.KryptonWorld
import net.aquamine.server.world.damage.KryptonDamageSource

abstract class KryptonAquaticAnimal(world: KryptonWorld) : KryptonMob(world), AquaticAnimal {

    override fun tick() {
        val air = airSupply
        super.tick()
        handleAir(air)
    }

    protected open fun handleAir(amount: Int) {
        if (isAlive() && !waterPhysicsSystem.isInWaterOrBubbleColumn()) {
            // Aquatic creatures must be underwater to breathe. If they are out of water, they start to run out of air,
            // and eventually suffocate.
            airSupply = amount - 1
            if (airSupply == DROWNING_THRESHOLD) {
                // If the creature is out of air, it takes 2 points of drowning damage every tick.
                airSupply = 0
                damage(KryptonDamageSource(DamageTypes.DROWNING), DROWNING_DAMAGE)
            }
        } else {
            airSupply = AIR_RESET_AMOUNT
        }
    }

    final override fun isPushedByFluid(): Boolean = false

    companion object {

        private const val DROWNING_THRESHOLD = -20
        private const val DROWNING_DAMAGE = 2F
        private const val AIR_RESET_AMOUNT = 300
    }
}

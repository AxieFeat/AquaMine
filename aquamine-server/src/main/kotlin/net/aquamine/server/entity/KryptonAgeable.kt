package net.aquamine.server.entity

import net.aquamine.api.entity.Ageable
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.AgeableSerializer
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.world.KryptonWorld

abstract class KryptonAgeable(world: KryptonWorld) : KryptonMob(world), Ageable {

    override val serializer: EntitySerializer<out KryptonAgeable>
        get() = AgeableSerializer

    final override var age: Int = 0
        set(value) {
            val old = field
            field = value
            if (old < 0 && value >= 0 || old >= 0 && value < 0) {
                data.set(MetadataKeys.Ageable.BABY, value < 0)
                onAgeTransformation()
            }
        }
    final override var isBaby: Boolean
        get() = age < 0
        set(value) {
            age = if (value) BABY_AGE else 0
        }

    internal var forcedAge = 0
    private var forcedAgeTimer = 0

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Ageable.BABY, false)
    }

    protected open fun onAgeTransformation() {
        // nothing to do by default
    }

    private fun increaseAge(amount: Int, forced: Boolean) {
        val old = age
        var newAge = old + amount * 20
        if (newAge > 0) newAge = 0
        val difference = newAge - old
        age = newAge

        if (forced) {
            forcedAge += difference
            if (forcedAgeTimer == 0) forcedAgeTimer = FORCED_AGE_TIME
        }
        if (age == 0) age = forcedAge
    }

    override fun increaseAge(amount: Int) {
        increaseAge(amount, false)
    }

    override fun canBreedNaturally(): Boolean = false

    companion object {

        private const val BABY_AGE = -24000
        private const val FORCED_AGE_TIME = 40
    }
}

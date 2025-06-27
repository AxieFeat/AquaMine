package net.aquamine.server.entity.projectile

import net.aquamine.api.entity.projectile.FishingHook
import net.aquamine.api.util.Vec3d
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.metadata.MetadataKey
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.util.math.Maths
import net.aquamine.server.util.random.RandomSource
import net.aquamine.server.world.AquaWorld

class AquaFishingHook(world: AquaWorld) : AquaProjectile(world), FishingHook {

    override val type: AquaEntityType<AquaFishingHook>
        get() = AquaEntityTypes.FISHING_HOOK

    override var hooked: AquaEntity? = null
        set(value) {
            field = value
            data.set(MetadataKeys.FishingHook.HOOKED, if (value != null) value.id + 1 else 0)
        }
    override var state: FishingHook.State = FishingHook.State.FLYING
    private var biting = false

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.FishingHook.HOOKED, 0)
        data.define(MetadataKeys.FishingHook.BITING, false)
    }

    override fun onDataUpdate(key: MetadataKey<*>) {
        if (key === MetadataKeys.FishingHook.HOOKED) {
            val id = data.get(MetadataKeys.FishingHook.HOOKED)
            hooked = if (id > 0) world.entityManager.getById(id - 1) else null
        }

        if (key === MetadataKeys.FishingHook.BITING) {
            biting = data.get(MetadataKeys.FishingHook.BITING)
            if (biting) velocity = randomizeBitingVelocity(random, velocity)
        }

        super.onDataUpdate(key)
    }

    override fun isBiting(): Boolean = biting

    companion object {

        private const val BITING_VELOCITY_RANDOMNESS_MIN = 0.6F
        private const val BITING_VELOCITY_MULTIPLIER = -0.4F

        @JvmStatic
        private fun randomizeBitingVelocity(random: RandomSource, existing: Vec3d): Vec3d {
            // This always comes out to be a number between -0.4 and -0.24. These numbers are from vanilla.
            val randomY = BITING_VELOCITY_MULTIPLIER * Maths.nextFloat(random, BITING_VELOCITY_RANDOMNESS_MIN, 1F)
            return Vec3d(existing.x, randomY.toDouble(), existing.z)
        }
    }
}

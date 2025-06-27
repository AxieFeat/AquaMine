package net.aquamine.server.entity.aquatic

import net.aquamine.api.entity.aquatic.Dolphin
import net.aquamine.api.util.Vec3i
import net.aquamine.api.world.damage.type.DamageTypes
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.aquatic.DolphinSerializer
import net.aquamine.server.util.random.RandomSource
import net.aquamine.server.world.AquaWorld
import net.aquamine.server.world.damage.AquaDamageSource

class AquaDolphin(world: AquaWorld) : AquaAquaticAnimal(world), Dolphin {

    override val type: AquaEntityType<AquaDolphin>
        get() = AquaEntityTypes.DOLPHIN
    override val serializer: EntitySerializer<AquaDolphin>
        get() = DolphinSerializer

    override var treasurePosition: Vec3i
        get() = data.get(MetadataKeys.Dolphin.TREASURE_POSITION)
        set(value) = data.set(MetadataKeys.Dolphin.TREASURE_POSITION, value)
    override var hasGotFish: Boolean
        get() = data.get(MetadataKeys.Dolphin.GOT_FISH)
        set(value) = data.set(MetadataKeys.Dolphin.GOT_FISH, value)
    override var skinMoisture: Int
        get() = data.get(MetadataKeys.Dolphin.MOISTURE)
        set(value) = data.set(MetadataKeys.Dolphin.MOISTURE, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Dolphin.TREASURE_POSITION, Vec3i.ZERO)
        data.define(MetadataKeys.Dolphin.GOT_FISH, false)
        data.define(MetadataKeys.Dolphin.MOISTURE, FULL_SKIN_MOISTURE)
    }

    override fun handleAir(amount: Int) {
        // Dolphins have special air handling logic that is in the tick function directly.
    }

    override fun tick() {
        super.tick()
        if (!hasAI) {
            airSupply = maxAirTicks()
            return
        }
        // Dolphins don't immediately start to suffocate out of water, as they can survive out of water for extended periods of time.
        // In real life, dolphins can't breathe underwater, so they have to resurface. In Minecraft, this behaviour isn't
        // simulated, but what is simulated is the ability for dolphins to survive for extended periods of time out of water.
        if (isInWater() || waterPhysicsSystem.isInBubbleColumn()) { // TODO: Also check for being in rain
            // If the dolphin is in water, rain, or a bubble column then it has full moisture.
            skinMoisture = FULL_SKIN_MOISTURE
            return
        }
        // For every tick the dolphin is not in water, its moisture decreases by 1.
        skinMoisture--
        // When the moisture reaches 0, the dolphin takes one damage every tick from dry out.
        if (skinMoisture <= 0) damage(AquaDamageSource(DamageTypes.DRY_OUT), 1F)
        if (isOnGround) {
            velocity = velocity.add(randomVelocityModifier(random), GROUND_Y_VELOCITY_INCREASE, randomVelocityModifier(random))
            teleport(position.withPitch(random.nextFloat() * MAX_ANGLE))
            isOnGround = false
        }
    }

    override fun maxAirTicks(): Int = MAX_AIR

    companion object {

        private const val MAX_AIR = 4 * 60 * 20 // 4 minutes in ticks
        private const val FULL_SKIN_MOISTURE = 2 * 60 * 20 // 2 minutes in ticks
        private const val MAX_ANGLE = 360F
        private const val GROUND_Y_VELOCITY_INCREASE = 0.5

        private const val DEFAULT_MAX_HEALTH = 10.0
        private const val DEFAULT_MOVEMENT_SPEED = 1.2
        private const val DEFAULT_ATTACK_DAMAGE = 3.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(AquaAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)

        // this will always produce a value between -0.2 and 0.2
        // Broken down: Random.nextFloat() produces a value between 0 and 1, multiplied by 2 produces a value between 0 and 2
        // taking 1 away produces a value between -1 and 1, then multiplying by 0.2 produces a value between -0.2 and 0.2
        @JvmStatic
        @Suppress("MagicNumber") // Not magic if it's explained in a comment
        private fun randomVelocityModifier(random: RandomSource): Double = ((random.nextFloat() * 2F - 1F) * 0.2F).toDouble()
    }
}

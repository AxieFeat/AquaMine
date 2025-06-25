package net.aquamine.server.entity.monster

import net.aquamine.api.entity.monster.Zombie
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.monster.ZombieSerializer
import net.aquamine.server.world.KryptonWorld

open class KryptonZombie(world: KryptonWorld) : KryptonMonster(world), Zombie {

    override val type: KryptonEntityType<KryptonZombie>
        get() = KryptonEntityTypes.ZOMBIE
    override val serializer: EntitySerializer<KryptonZombie>
        get() = ZombieSerializer

    internal var conversionTime = 0

    override var isBaby: Boolean
        get() = data.get(MetadataKeys.Zombie.BABY)
        set(value) = data.set(MetadataKeys.Zombie.BABY, value)
    override var isConverting: Boolean
        get() = data.get(MetadataKeys.Zombie.CONVERTING)
        set(value) = data.set(MetadataKeys.Zombie.CONVERTING, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Zombie.BABY, false)
        data.define(MetadataKeys.Zombie.CONVERTING, false)
    }

    companion object {

        private const val DEFAULT_FOLLOW_RANGE = 35.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.23
        private const val DEFAULT_ATTACK_DAMAGE = 3.0
        private const val DEFAULT_ARMOR = 2.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMonster.attributes()
            .add(KryptonAttributeTypes.FOLLOW_RANGE, DEFAULT_FOLLOW_RANGE)
            .add(KryptonAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(KryptonAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
            .add(KryptonAttributeTypes.ARMOR, DEFAULT_ARMOR)
            .add(KryptonAttributeTypes.SPAWN_REINFORCEMENTS_CHANCE)
    }
}

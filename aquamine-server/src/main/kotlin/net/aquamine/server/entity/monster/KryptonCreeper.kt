package net.aquamine.server.entity.monster

import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.entity.monster.Creeper
import net.aquamine.server.entity.KryptonEntityType
import net.aquamine.server.entity.KryptonEntityTypes
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.monster.CreeperSerializer
import net.aquamine.server.world.KryptonWorld

class KryptonCreeper(world: KryptonWorld) : KryptonMonster(world), Creeper {

    override val type: KryptonEntityType<KryptonCreeper>
        get() = KryptonEntityTypes.CREEPER
    override val serializer: EntitySerializer<KryptonCreeper>
        get() = CreeperSerializer

    override var fuse: Int = 30
    override var explosionRadius: Int = 0

    override var isCharged: Boolean
        get() = data.get(MetadataKeys.Creeper.CHARGED)
        set(value) = data.set(MetadataKeys.Creeper.CHARGED, value)

    override val isIgnited: Boolean
        get() = data.get(MetadataKeys.Creeper.IGNITED)
    override var currentFuse: Int
        get() = data.get(MetadataKeys.Creeper.STATE)
        set(value) = data.set(MetadataKeys.Creeper.STATE, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Creeper.STATE, -1)
        data.define(MetadataKeys.Creeper.CHARGED, false)
        data.define(MetadataKeys.Creeper.IGNITED, false)
    }

    override fun tick() {
        super.tick()
        tickIgnite()
    }

    private fun tickIgnite() {
        if (!isAlive() || !isIgnited) return
        if (currentFuse > 0) currentFuse--
        if (currentFuse <= 0) {
            explode()
            currentFuse = -1
            setIgnited(false)
        }
    }

    override fun explode() {
        playSound(SoundEvents.GENERIC_EXPLODE.get(), 4F, (1F + (random.nextFloat() - random.nextFloat()) * 0.2F) * 0.7F)
        remove()
        // TODO: Creeper explosions
    }

    override fun ignite() {
        setIgnited(true)
        currentFuse = fuse
        playSound(SoundEvents.CREEPER_PRIMED.get(), 1F, 0.5F)
    }

    fun setIgnited(ignited: Boolean) {
        data.set(MetadataKeys.Creeper.IGNITED, ignited)
    }

    companion object {

        private const val DEFAULT_MOVEMENT_SPEED = 0.25

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMonster.attributes().add(KryptonAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
    }
}

package net.aquamine.server.entity.animal

import net.kyori.adventure.sound.Sound
import net.aquamine.api.entity.animal.Animal
import net.aquamine.api.entity.animal.Parrot
import net.aquamine.api.entity.animal.type.ParrotVariant
import net.aquamine.api.item.ItemStack
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.ParrotSerializer
import net.aquamine.server.world.AquaWorld

class AquaParrot(world: AquaWorld) : AquaTamable(world), Parrot {

    override val type: AquaEntityType<AquaParrot>
        get() = AquaEntityTypes.PARROT
    override val serializer: EntitySerializer<AquaParrot>
        get() = ParrotSerializer

    override var variant: ParrotVariant
        get() = TYPES.getOrNull(data.get(MetadataKeys.Parrot.TYPE)) ?: ParrotVariant.RED_AND_BLUE
        set(value) = data.set(MetadataKeys.Parrot.TYPE, value.ordinal)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Parrot.TYPE, ParrotVariant.RED_AND_BLUE.ordinal)
    }

    override fun isFood(item: ItemStack): Boolean = false

    override fun canMate(target: Animal): Boolean = false

    override fun soundSource(): Sound.Source = Sound.Source.NEUTRAL

    override fun voicePitch(): Float = (random.nextFloat() - random.nextFloat()) * 0.2F + 1F

    companion object {

        private val TYPES = ParrotVariant.entries.toTypedArray()

        private const val DEFAULT_MAX_HEALTH = 6.0
        private const val DEFAULT_FLYING_SPEED = 0.4
        private const val DEFAULT_MOVEMENT_SPEED = 0.2

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.FLYING_SPEED, DEFAULT_FLYING_SPEED)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
    }
}

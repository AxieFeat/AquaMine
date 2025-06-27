package net.aquamine.server.entity.animal

import net.kyori.adventure.sound.Sound
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.entity.animal.Animal
import net.aquamine.api.entity.animal.Cat
import net.aquamine.api.entity.animal.type.CatVariant
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.item.data.DyeColor
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.CatSerializer
import net.aquamine.server.util.enumhelper.DyeColors
import net.aquamine.server.world.AquaWorld

class AquaCat(world: AquaWorld) : AquaTamable(world), Cat {

    override val type: AquaEntityType<AquaCat>
        get() = AquaEntityTypes.CAT
    override val serializer: EntitySerializer<AquaCat>
        get() = CatSerializer

    override var variant: CatVariant
        get() = VARIANTS[data.get(MetadataKeys.Cat.VARIANT)]
        set(value) = data.set(MetadataKeys.Cat.VARIANT, value.ordinal)
    override var isLying: Boolean
        get() = data.get(MetadataKeys.Cat.LYING)
        set(value) = data.set(MetadataKeys.Cat.LYING, value)
    override var isRelaxed: Boolean
        get() = data.get(MetadataKeys.Cat.RELAXED)
        set(value) = data.set(MetadataKeys.Cat.RELAXED, value)
    override var collarColor: DyeColor
        get() = DyeColors.fromId(data.get(MetadataKeys.Cat.COLLAR_COLOR))
        set(value) = data.set(MetadataKeys.Cat.COLLAR_COLOR, value.ordinal)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Cat.VARIANT, CatVariant.BLACK.ordinal)
        data.define(MetadataKeys.Cat.LYING, false)
        data.define(MetadataKeys.Cat.RELAXED, false)
        data.define(MetadataKeys.Cat.COLLAR_COLOR, DyeColor.RED.ordinal)
    }

    override fun hiss() {
        playSound(Sound.sound(SoundEvents.CAT_HISS, soundSource(), soundVolume(), voicePitch()), position.x, position.y, position.z)
    }

    override fun canMate(target: Animal): Boolean {
        if (!isTamed) return false
        if (target !is Cat) return false
        return target.isTamed && super.canMate(target)
    }

    override fun isFood(item: ItemStack): Boolean = item.type === ItemTypes.COD || item.type === ItemTypes.SALMON

    companion object {

        private val VARIANTS = CatVariant.values()
        private const val DEFAULT_MAX_HEALTH = 10.0
        private const val DEFAULT_MOVEMENT_SPEED = 0.3
        private const val DEFAULT_ATTACK_DAMAGE = 3.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(AquaAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
    }
}

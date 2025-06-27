package net.aquamine.server.entity.animal

import net.aquamine.api.entity.animal.Fox
import net.aquamine.api.entity.animal.type.FoxVariant
import net.aquamine.api.item.ItemStack
import net.aquamine.api.tags.ItemTags
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaLivingEntity
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.FoxSerializer
import net.aquamine.server.item.downcast
import net.aquamine.server.world.AquaWorld
import java.util.UUID

class AquaFox(world: AquaWorld) : AquaAnimal(world), Fox {

    override val type: AquaEntityType<AquaFox>
        get() = AquaEntityTypes.FOX
    override val serializer: EntitySerializer<AquaFox>
        get() = FoxSerializer

    override var variant: FoxVariant
        get() = TYPES.getOrNull(data.get(MetadataKeys.Fox.TYPE)) ?: FoxVariant.RED
        set(value) = data.set(MetadataKeys.Fox.TYPE, value.ordinal)
    override var isSitting: Boolean
        get() = data.getFlag(MetadataKeys.Fox.FLAGS, FLAG_SITTING)
        set(value) = data.setFlag(MetadataKeys.Fox.FLAGS, FLAG_SITTING, value)
    override var isCrouching: Boolean
        get() = data.getFlag(MetadataKeys.Fox.FLAGS, FLAG_CROUCHING)
        set(value) = data.setFlag(MetadataKeys.Fox.FLAGS, FLAG_CROUCHING, value)
    override var isInterested: Boolean
        get() = data.getFlag(MetadataKeys.Fox.FLAGS, FLAG_INTERESTED)
        set(value) = data.setFlag(MetadataKeys.Fox.FLAGS, FLAG_INTERESTED, value)
    override var isPouncing: Boolean
        get() = data.getFlag(MetadataKeys.Fox.FLAGS, FLAG_POUNCING)
        set(value) = data.setFlag(MetadataKeys.Fox.FLAGS, FLAG_POUNCING, value)
    override var isSleeping: Boolean
        get() = data.getFlag(MetadataKeys.Fox.FLAGS, FLAG_SLEEPING)
        set(value) = data.setFlag(MetadataKeys.Fox.FLAGS, FLAG_SLEEPING, value)
    override var hasFaceplanted: Boolean
        get() = data.getFlag(MetadataKeys.Fox.FLAGS, FLAG_FACEPLANTED)
        set(value) = data.setFlag(MetadataKeys.Fox.FLAGS, FLAG_FACEPLANTED, value)
    override var isDefending: Boolean
        get() = data.getFlag(MetadataKeys.Fox.FLAGS, FLAG_DEFENDING)
        set(value) = data.setFlag(MetadataKeys.Fox.FLAGS, FLAG_DEFENDING, value)
    override var firstTrusted: UUID?
        get() = data.get(MetadataKeys.Fox.FIRST_TRUSTED)
        set(value) = data.set(MetadataKeys.Fox.FIRST_TRUSTED, value)
    override var secondTrusted: UUID?
        get() = data.get(MetadataKeys.Fox.SECOND_TRUSTED)
        set(value) = data.set(MetadataKeys.Fox.SECOND_TRUSTED, value)

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Fox.TYPE, FoxVariant.RED.ordinal)
        data.define(MetadataKeys.Fox.FLAGS, 0)
        data.define(MetadataKeys.Fox.FIRST_TRUSTED, null)
        data.define(MetadataKeys.Fox.SECOND_TRUSTED, null)
    }

    override fun trusts(uuid: UUID): Boolean = uuid == firstTrusted || uuid == secondTrusted

    override fun isFood(item: ItemStack): Boolean = item.type.downcast().eq(ItemTags.FOX_FOOD)

    override fun setTarget(target: AquaLivingEntity?) {
        if (isDefending && target == null) isDefending = false
        super.setTarget(target)
    }

    companion object {

        private const val FLAG_SITTING = 0
        private const val FLAG_CROUCHING = 2
        private const val FLAG_INTERESTED = 3
        private const val FLAG_POUNCING = 4
        private const val FLAG_SLEEPING = 5
        private const val FLAG_FACEPLANTED = 6
        private const val FLAG_DEFENDING = 7
        private val TYPES = FoxVariant.values()

        private const val DEFAULT_MOVEMENT_SPEED = 0.3
        private const val DEFAULT_MAX_HEALTH = 10.0
        private const val DEFAULT_FOLLOW_RANGE = 32.0
        private const val DEFAULT_ATTACK_DAMAGE = 2.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.FOLLOW_RANGE, DEFAULT_FOLLOW_RANGE)
            .add(AquaAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
    }
}

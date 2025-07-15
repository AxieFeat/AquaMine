package net.aquamine.server.entity.animal

import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.entity.animal.Axolotl
import net.aquamine.api.entity.animal.type.AxolotlVariant
import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemTypes
import net.aquamine.api.item.ItemType
import net.aquamine.api.tags.ItemTags
import net.aquamine.server.entity.components.BucketStorable
import net.aquamine.server.entity.AquaEntityType
import net.aquamine.server.entity.AquaEntityTypes
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.ai.memory.MemoryKeys
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.animal.AxolotlSerializer
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.item.downcast
import net.aquamine.server.world.AquaWorld
import xyz.axie.nbt.CompoundTag

class AquaAxolotl(world: AquaWorld) : AquaAnimal(world), Axolotl, BucketStorable {

    override val type: AquaEntityType<AquaAxolotl>
        get() = AquaEntityTypes.AXOLOTL
    override val serializer: EntitySerializer<AquaAxolotl>
        get() = AxolotlSerializer

    override var variant: AxolotlVariant
        get() = VARIANTS.getOrNull(data.get(MetadataKeys.Axolotl.VARIANT)) ?: AxolotlVariant.LUCY
        set(value) = data.set(MetadataKeys.Axolotl.VARIANT, value.ordinal)
    override var isPlayingDead: Boolean
        get() = data.get(MetadataKeys.Axolotl.PLAYING_DEAD)
        set(value) = data.set(MetadataKeys.Axolotl.PLAYING_DEAD, value)

    override val bucketType: ItemType
        get() = ItemTypes.AXOLOTL_BUCKET.get()
    override val bucketPickupSound: SoundEvent
        get() = SoundEvents.BUCKET_FILL_AXOLOTL.get()

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Axolotl.VARIANT, AxolotlVariant.LUCY.ordinal)
        data.define(MetadataKeys.Axolotl.PLAYING_DEAD, false)
        data.define(MetadataKeys.Axolotl.FROM_BUCKET, false)
    }

    override fun wasSpawnedFromBucket(): Boolean = data.get(MetadataKeys.Axolotl.FROM_BUCKET)

    override fun setSpawnedFromBucket(value: Boolean) {
        data.set(MetadataKeys.Axolotl.FROM_BUCKET, value)
    }

    override fun isFood(item: ItemStack): Boolean = item.type.downcast().eq(ItemTags.AXOLOTL_TEMPT_ITEMS)

    override fun loadFromBucket(tag: CompoundTag) {
        BucketStorable.loadDefaultsFromBucket(this, tag)
        data.set(MetadataKeys.Axolotl.VARIANT, tag.getInt("Variant"))
        if (tag.contains("Age")) age = tag.getInt("Age")
        if (tag.contains("HuntingCooldown")) brain.setExpirableMemory(MemoryKeys.HAS_HUNTING_COOLDOWN, true, tag.getLong("HuntingCooldown"))
    }

    /* FIXME
    override fun saveToBucket(item: KryptonItemStack) {
        saveDefaultsToBucket(this, item)
        item.meta.nbt.apply {
            putInt("Variant", data.get(MetadataKeys.AXOLOTL.VARIANT))
            putInt("Age", age)
            if (brain.contains(MemoryKeys.HAS_HUNTING_COOLDOWN)) {
                putLong("HuntingCooldown", brain.expiryTime(MemoryKeys.HAS_HUNTING_COOLDOWN))
            }
        }
    }
     */

    // TODO: Implement proper bucket item meta
    override fun asBucket(): AquaItemStack = AquaItemStack.EMPTY

    override fun bucket(): AquaItemStack {
        remove()
        return asBucket()
    }

    override fun isPushedByFluid(): Boolean = false

    override fun maxAirTicks(): Int = MAX_AIR_TICKS

    override fun canBeSeenAsEnemy(): Boolean = !isPlayingDead && super.canBeSeenAsEnemy()

    companion object {

        private const val MAX_AIR_TICKS = 5 * 60 * 20 // 5 minutes in ticks
        private val VARIANTS = AxolotlVariant.entries.toTypedArray()

        private const val DEFAULT_MAX_HEALTH = 14.0
        private const val DEFAULT_MOVEMENT_SPEED = 1.0
        private const val DEFAULT_ATTACK_DAMAGE = 2.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes()
            .add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
            .add(AquaAttributeTypes.MOVEMENT_SPEED, DEFAULT_MOVEMENT_SPEED)
            .add(AquaAttributeTypes.ATTACK_DAMAGE, DEFAULT_ATTACK_DAMAGE)
    }
}

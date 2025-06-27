package net.aquamine.server.entity.aquatic

import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.entity.aquatic.Fish
import net.aquamine.server.entity.components.BucketStorable
import net.aquamine.server.entity.AquaMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.AquaAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.aquatic.FishSerializer
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.world.AquaWorld
import xyz.axie.nbt.CompoundTag

abstract class AquaFish(world: AquaWorld) : AquaAquaticAnimal(world), Fish, BucketStorable {

    override val serializer: EntitySerializer<out AquaFish>
        get() = FishSerializer

    override val bucketPickupSound: SoundEvent
        get() = SoundEvents.BUCKET_FILL_FISH.get()

    override fun defineData() {
        super.defineData()
        data.define(MetadataKeys.Fish.FROM_BUCKET, false)
    }

    override fun wasSpawnedFromBucket(): Boolean = data.get(MetadataKeys.Fish.FROM_BUCKET)

    override fun setSpawnedFromBucket(value: Boolean) {
        data.set(MetadataKeys.Fish.FROM_BUCKET, value)
    }

    override fun loadFromBucket(tag: CompoundTag) {
        BucketStorable.loadDefaultsFromBucket(this, tag)
    }

    /* FIXME
    override fun saveToBucket(item: KryptonItemStack) {
        saveDefaultsToBucket(this, item)
    }
     */

    // TODO: Implement proper bucket item meta
    override fun asBucket(): AquaItemStack = AquaItemStack.EMPTY

    override fun bucket(): AquaItemStack {
        remove()
        return asBucket()
    }

    companion object {

        private const val DEFAULT_MAX_HEALTH = 3.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = AquaMob.attributes().add(AquaAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
    }
}

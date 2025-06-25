package net.aquamine.server.entity.aquatic

import net.aquamine.api.effect.sound.SoundEvent
import net.aquamine.api.effect.sound.SoundEvents
import net.aquamine.api.entity.aquatic.Fish
import net.aquamine.server.entity.components.BucketStorable
import net.aquamine.server.entity.KryptonMob
import net.aquamine.server.entity.attribute.AttributeSupplier
import net.aquamine.server.entity.attribute.KryptonAttributeTypes
import net.aquamine.server.entity.metadata.MetadataKeys
import net.aquamine.server.entity.serializer.EntitySerializer
import net.aquamine.server.entity.serializer.aquatic.FishSerializer
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.world.KryptonWorld
import xyz.axie.nbt.CompoundTag

abstract class KryptonFish(world: KryptonWorld) : KryptonAquaticAnimal(world), Fish, BucketStorable {

    override val serializer: EntitySerializer<out KryptonFish>
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
    override fun asBucket(): KryptonItemStack = KryptonItemStack.EMPTY

    override fun bucket(): KryptonItemStack {
        remove()
        return asBucket()
    }

    companion object {

        private const val DEFAULT_MAX_HEALTH = 3.0

        @JvmStatic
        fun attributes(): AttributeSupplier.Builder = KryptonMob.attributes().add(KryptonAttributeTypes.MAX_HEALTH, DEFAULT_MAX_HEALTH)
    }
}

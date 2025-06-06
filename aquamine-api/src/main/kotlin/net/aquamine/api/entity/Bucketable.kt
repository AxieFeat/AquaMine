package net.aquamine.api.entity

import net.aquamine.api.item.ItemStack
import net.aquamine.api.item.ItemType
import net.aquamine.api.effect.sound.SoundEvent

/**
 * An entity that can be picked up in a bucket.
 */
interface Bucketable {

    /**
     * The type of the bucket this entity can be captured in.
     */
    val bucketType: ItemType

    /**
     * The sound that is played when the entity is picked up in a bucket.
     */
    val bucketPickupSound: SoundEvent

    /**
     * If this entity was spawned from a bucket.
     *
     * Certain mobs, such as fish, can be captured in a water bucket by the
     * player. When a mob is captured, it is removed from the world, and it is
     * stored on the bucket item. It can be placed back in to the world, in
     * which it would be spawned from a bucket.
     *
     * @return `true` if this entity was spawned from a bucket.
     */
    fun wasSpawnedFromBucket(): Boolean

    /**
     * Creates a new bucket from this entity. Does not remove the entity from
     * the world.
     */
    fun asBucket(): ItemStack

    /**
     * Buckets this entity, removing it from the world and returning the
     * created bucket containing this entity.
     */
    fun bucket(): ItemStack
}

package net.aquamine.server.registry

import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registry
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.tags.TagKey
import net.aquamine.api.tags.TagSet
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.registry.holder.HolderLookup
import net.aquamine.server.registry.holder.HolderOwner
import net.aquamine.server.registry.holder.HolderSet
import net.aquamine.server.resource.AquaResourceKey
import net.aquamine.server.tags.AquaTagSet
import net.aquamine.server.util.ImmutableLists
import net.aquamine.server.util.Keys
import net.aquamine.server.util.map.IntBiMap
import net.aquamine.server.util.successOrError
import net.aquamine.serialization.Codec
import java.util.Optional
import java.util.stream.Stream
import java.util.stream.StreamSupport

/**
 * The base registry class that specifies the public API of the backend registries.
 */
interface AquaRegistry<T> : Registry<T>, IntBiMap<T> {

    override fun containsKey(key: Key): Boolean

    override fun containsKey(key: ResourceKey<T>): Boolean

    override fun getKey(value: T): Key?

    override fun getResourceKey(value: T): ResourceKey<T>?

    override fun getId(value: T): Int

    override fun get(key: Key): T?

    override fun get(key: ResourceKey<T>): T?

    fun getOrThrow(key: ResourceKey<T>): T = checkNotNull(get(key)) { "Could not find key $key in registry ${this.key}!" }

    fun holders(): Collection<Holder.Reference<T>>

    fun getHolder(id: Int): Holder.Reference<T>?

    fun getHolder(key: ResourceKey<T>): Holder.Reference<T>?

    fun getHolderOrThrow(key: ResourceKey<T>): Holder.Reference<T> =
        checkNotNull(getHolder(key)) { "Could not find key $key in registry ${this.key}!" }

    fun wrapAsHolder(value: T & Any): Holder<T>

    fun createIntrusiveHolder(value: T): Holder.Reference<T>

    fun getTag(key: TagKey<T>): HolderSet.Named<T>?

    fun getTagOrEmpty(key: TagKey<T>): Iterable<Holder<T>> = getTag(key) ?: ImmutableLists.of()

    override fun getTagValues(key: TagKey<T>): TagSet<T>? = getTag(key)?.let { AquaTagSet(this, it) }

    fun getOrCreateTag(key: TagKey<T>): HolderSet.Named<T>

    fun isKnownTagKey(key: TagKey<T>): Boolean

    fun resetTags()

    fun bindTags(tags: Map<TagKey<T>, List<Holder<T>>>)

    fun tagEntries(): Map<TagKey<T>, HolderSet.Named<T>>

    fun tagNames(): Stream<TagKey<T>>

    fun freeze(): AquaRegistry<T>

    fun holderOwner(): HolderOwner<T>

    fun asLookup(): HolderLookup.ForRegistry<T>

    fun asTagAddingLookup(): HolderLookup.ForRegistry<T> = object : HolderLookup.ForRegistry.Forwarding<T>() {
        override fun delegate(): HolderLookup.ForRegistry<T> = asLookup()

        override fun get(key: TagKey<T>): HolderSet.Named<T> = getOrThrow(key)

        override fun getOrThrow(key: TagKey<T>): HolderSet.Named<T> = getOrCreateTag(key)
    }

    fun asHolderIdMap(): IntBiMap<Holder<T>> = object : IntBiMap<Holder<T>> {

        override fun size(): Int = this@AquaRegistry.size()

        override fun get(id: Int): Holder<T>? = getHolder(id)

        override fun getId(value: Holder<T>): Int = this@AquaRegistry.getId(value.value())

        override fun iterator(): Iterator<Holder<T>> = holders().iterator()
    }

    fun byNameCodec(): Codec<T> = Keys.CODEC.flatXmap(
        { Optional.ofNullable(get(it)).successOrError { "Unknown registry key $it in $key!" } },
        { value -> Optional.ofNullable(getResourceKey(value)).map { it.location }.successOrError { "Unknown registry element $value in $key!" } }
    )

    fun holderByNameCodec(): Codec<Holder<T>> = Keys.CODEC.flatXmap(
        { Optional.ofNullable(getHolder(AquaResourceKey.of(key, it))).successOrError { "Unknown registry key $it in $key!" } },
        { holder -> holder.unwrapKey().map { it.location }.successOrError { "Unknown registry element $holder in $key!" } }
    )

    override fun stream(): Stream<T> = StreamSupport.stream(spliterator(), false)
}

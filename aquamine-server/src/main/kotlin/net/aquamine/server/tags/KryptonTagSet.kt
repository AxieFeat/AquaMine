package net.aquamine.server.tags

import com.google.common.collect.Iterators
import net.aquamine.api.tags.TagKey
import net.aquamine.api.tags.TagSet
import net.aquamine.server.registry.KryptonRegistry
import net.aquamine.server.registry.holder.HolderSet
import java.util.stream.Stream

class KryptonTagSet<T>(override val registry: KryptonRegistry<T>, private val delegate: HolderSet.Named<T>) : TagSet<T> {

    override val key: TagKey<T>
        get() = delegate.key

    override fun size(): Int = delegate.size()

    override fun contains(value: T): Boolean = delegate.contains(registry.wrapAsHolder(value!!))

    override fun get(index: Int): T = delegate.get(index).value()

    override fun iterator(): Iterator<T> = Iterators.transform(delegate.iterator()) { it.value() }

    override fun stream(): Stream<T> = delegate.stream().map { it.value() }
}

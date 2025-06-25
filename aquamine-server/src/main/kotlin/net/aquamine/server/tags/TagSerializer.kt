package net.aquamine.server.tags

import it.unimi.dsi.fastutil.ints.IntArrayList
import it.unimi.dsi.fastutil.ints.IntList
import net.kyori.adventure.key.Key
import net.aquamine.api.registry.Registry
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.resource.ResourceKey
import net.aquamine.server.network.Writable
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.registry.KryptonRegistry
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.registry.network.RegistrySerialization

object TagSerializer {

    @JvmStatic
    fun serializeTagsToNetwork(dynamicHolder: RegistryHolder): Map<ResourceKey<out Registry<*>>, NetworkPayload> {
        return RegistrySerialization.networkSafeRegistries(dynamicHolder).registries.asSequence()
            .map { Pair(it.key, serializeToNetwork(it as KryptonRegistry<*>)) }
            .filter { !it.second.isEmpty() }
            .associate { it.first to it.second }
    }

    @JvmStatic
    private fun <T> serializeToNetwork(registry: KryptonRegistry<T>): NetworkPayload {
        val tags = HashMap<Key, IntList>()
        registry.tagEntries().forEach { (key, set) ->
            val ids = IntArrayList(set.size())
            set.forEach {
                if (it.kind() != Holder.Kind.REFERENCE) error("Cannot serialize unregistered tag value $it!")
                ids.add(registry.getId(it.value()))
            }
            tags.put(key.location, ids)
        }
        return NetworkPayload(tags)
    }

    @JvmRecord
    data class NetworkPayload(val tags: Map<Key, IntList>) : Writable {

        constructor(reader: BinaryReader) : this(reader.readMap(BinaryReader::readKey, BinaryReader::readIntIdList))

        fun isEmpty(): Boolean = tags.isEmpty()

        override fun write(writer: BinaryWriter) {
            writer.writeMap(tags, BinaryWriter::writeKey, BinaryWriter::writeIntIdList)
        }
    }
}

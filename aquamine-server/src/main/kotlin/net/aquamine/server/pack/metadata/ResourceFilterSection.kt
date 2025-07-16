package net.aquamine.server.pack.metadata

import net.aquamine.server.util.ImmutableLists
import net.aquamine.server.util.KeyPattern
import net.aquamine.serialization.Codec
import net.aquamine.serialization.codecs.RecordCodecBuilder

class ResourceFilterSection(blockList: List<KeyPattern>) {

    private val blockList = ImmutableLists.copyOf(blockList)

    fun isNamespaceFiltered(namespace: String): Boolean = blockList.any { it.namespacePredicate().test(namespace) }

    fun isPathFiltered(path: String): Boolean = blockList.any { it.valuePredicate().test(path) }

    companion object {

        private val CODEC: Codec<ResourceFilterSection> = RecordCodecBuilder.create { instance ->
            instance.group(KeyPattern.CODEC.listOf().fieldOf("block").getting { it.blockList }).apply(instance, ::ResourceFilterSection)
        }
        @JvmField
        val SERIALIZER: MetadataSectionSerializer<ResourceFilterSection> = MetadataSectionSerializer.fromCodec("filter", CODEC)
    }
}

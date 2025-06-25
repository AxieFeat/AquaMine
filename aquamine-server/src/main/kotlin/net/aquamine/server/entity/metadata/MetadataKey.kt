package net.aquamine.server.entity.metadata

import net.aquamine.annotations.CataloguedBy

/**
 * A key that can be used to retrieve entity metadata from a [MetadataHolder].
 */
@CataloguedBy(MetadataKeys::class)
class MetadataKey<T>(val id: Byte, val serializer: MetadataSerializer<T>) {

    override fun toString(): String = "MetadataKey(id=$id, serializer=$serializer)"
}

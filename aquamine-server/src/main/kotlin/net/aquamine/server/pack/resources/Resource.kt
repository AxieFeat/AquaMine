package net.aquamine.server.pack.resources

import net.aquamine.server.pack.PackResources
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.function.Supplier

class Resource(
    private val source: PackResources,
    private val streamSupplier: Supplier<InputStream>,
    private val metadataSupplier: Supplier<ResourceMetadata>
) {

    private var cachedMetadata: ResourceMetadata? = null

    constructor(source: PackResources, streamSupplier: Supplier<InputStream>) : this(source, streamSupplier, ResourceMetadata.EMPTY_SUPPLIER) {
        cachedMetadata = ResourceMetadata.EMPTY
    }

    fun source(): PackResources = source

    fun sourcePackId(): String = source.packId()

    fun isBuiltin(): Boolean = source.isBuiltin()

    fun open(): InputStream = streamSupplier.get()

    fun openAsReader(): BufferedReader = BufferedReader(InputStreamReader(open(), Charsets.UTF_8))

    fun metadata(): ResourceMetadata {
        if (cachedMetadata == null) cachedMetadata = metadataSupplier.get()
        return cachedMetadata!!
    }
}

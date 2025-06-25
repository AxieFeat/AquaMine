package net.aquamine.server.world.region

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.aquamine.server.util.FastBufferedInputStream
import java.io.BufferedOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.zip.DeflaterOutputStream
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream
import java.util.zip.InflaterInputStream

class RegionFileVersion private constructor(
    val id: Int,
    private val decompressor: Converter<InputStream>,
    private val compressor: Converter<OutputStream>
) {

    fun compress(output: OutputStream): OutputStream = compressor.convert(output)

    fun decompress(input: InputStream): InputStream = decompressor.convert(input)

    private fun interface Converter<T> {

        fun convert(stream: T): T
    }

    companion object {

        private val BY_ID = Int2ObjectOpenHashMap<RegionFileVersion>()

        @JvmField
        val GZIP: RegionFileVersion = register(1, ::GZIPInputStream, ::GZIPOutputStream)
        @JvmField
        val ZLIB: RegionFileVersion = register(2, ::InflaterInputStream, ::DeflaterOutputStream)
        @JvmField
        val NONE: RegionFileVersion = register(RegionFileVersion(3, { it }, { it }))

        @JvmStatic
        fun fromId(id: Int): RegionFileVersion? = BY_ID.get(id)

        @JvmStatic
        private fun register(version: RegionFileVersion): RegionFileVersion = version.apply { BY_ID.put(version.id, version) }

        @JvmStatic
        private fun register(id: Int, decompressor: Converter<InputStream>, compressor: Converter<OutputStream>): RegionFileVersion =
            register(RegionFileVersion(id, { FastBufferedInputStream(decompressor.convert(it)) }, { BufferedOutputStream(compressor.convert(it)) }))
    }
}

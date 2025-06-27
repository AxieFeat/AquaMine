package net.aquamine.server.entity.metadata

import net.aquamine.annotations.CataloguedBy
import net.aquamine.server.network.buffer.BinaryReader
import net.aquamine.server.network.buffer.BinaryWriter
import net.aquamine.server.registry.AquaRegistry

@CataloguedBy(MetadataSerializers::class)
interface MetadataSerializer<T> {

    fun read(reader: BinaryReader): T

    fun write(writer: BinaryWriter, item: T)

    fun createKey(id: Byte): MetadataKey<T> = MetadataKey(id, this)

    companion object {

        @JvmStatic
        inline fun <T> simple(crossinline readerFunction: (BinaryReader) -> T,
                              crossinline writerFunction: (BinaryWriter, T) -> Unit): MetadataSerializer<T> = object : MetadataSerializer<T> {

            override fun read(reader: BinaryReader): T = readerFunction(reader)

            override fun write(writer: BinaryWriter, item: T) {
                writerFunction(writer, item)
            }
        }

        @JvmStatic
        inline fun <T> nullable(crossinline reader: (BinaryReader) -> T, crossinline writer: (BinaryWriter, T) -> Unit): MetadataSerializer<T?> =
            simple({ it.readNullable(reader) }, { buf, value -> buf.writeNullable(value, writer) })

        @JvmStatic
        inline fun <reified E : Enum<E>> simpleEnum(): MetadataSerializer<E> = simple(BinaryReader::readEnum, BinaryWriter::writeEnum)

        @JvmStatic
        fun <T : Any> simpleId(registry: AquaRegistry<T>): MetadataSerializer<T> {
            return simple({ it.readById(registry)!! }, { buf, value -> buf.writeId(registry, value) })
        }
    }
}

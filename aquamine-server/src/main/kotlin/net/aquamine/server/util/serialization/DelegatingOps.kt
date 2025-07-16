package net.aquamine.server.util.serialization

import net.aquamine.serialization.DataOps
import net.aquamine.serialization.DataResult
import net.aquamine.serialization.ListBuilder
import net.aquamine.serialization.MapLike
import net.aquamine.serialization.RecordBuilder
import net.aquamine.util.Pair
import java.nio.ByteBuffer
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.stream.IntStream
import java.util.stream.LongStream
import java.util.stream.Stream

abstract class DelegatingOps<T> protected constructor(protected val delegate: DataOps<T>) : DataOps<T> {

    override fun empty(): T & Any = delegate.empty()

    override fun getBooleanValue(input: T & Any): DataResult<Boolean> = delegate.getBooleanValue(input)

    override fun getNumberValue(input: T & Any): DataResult<Number> = delegate.getNumberValue(input)

    override fun getStringValue(input: T & Any): DataResult<String> = delegate.getStringValue(input)

    override fun getStream(input: T & Any): DataResult<Stream<T>> = delegate.getStream(input)

    override fun getList(input: T & Any): DataResult<Consumer<Consumer<T>>> = delegate.getList(input)

    override fun mergeToList(list: T & Any, value: T & Any): DataResult<T> = delegate.mergeToList(list, value)

    override fun mergeToList(list: T & Any, values: MutableList<T>): DataResult<T> = delegate.mergeToList(list, values)

    override fun getByteBuffer(input: T & Any): DataResult<ByteBuffer> = delegate.getByteBuffer(input)

    override fun getIntStream(input: T & Any): DataResult<IntStream> = delegate.getIntStream(input)

    override fun getLongStream(input: T & Any): DataResult<LongStream> = delegate.getLongStream(input)

    override fun getMapValues(input: T & Any): DataResult<Stream<Pair<T, T>>> = delegate.getMapValues(input)

    override fun getMapEntries(input: T & Any): DataResult<Consumer<BiConsumer<T, T>>> = delegate.getMapEntries(input)

    override fun getMap(input: T & Any): DataResult<MapLike<T>> = delegate.getMap(input)

    override fun remove(input: T & Any, key: String): T & Any = delegate.remove(input, key)

    override fun mergeToMap(map: T & Any, key: T & Any, value: T & Any): DataResult<T> = delegate.mergeToMap(map, key, value)

    override fun mergeToMap(map: T & Any, values: MapLike<T>): DataResult<T> = delegate.mergeToMap(map, values)

    override fun createNumber(number: Number): T & Any = delegate.createNumber(number)

    override fun createBoolean(value: Boolean): T & Any = delegate.createBoolean(value)

    override fun createByte(value: Byte): T & Any = delegate.createByte(value)

    override fun createShort(value: Short): T & Any = delegate.createShort(value)

    override fun createInt(value: Int): T = delegate.createInt(value)

    override fun createLong(value: Long): T = delegate.createLong(value)

    override fun createFloat(value: Float): T = delegate.createFloat(value)

    override fun createDouble(value: Double): T = delegate.createDouble(value)

    override fun createString(value: String): T & Any = delegate.createString(value)

    override fun createList(input: Stream<T>): T & Any = delegate.createList(input)

    override fun createByteList(input: ByteBuffer): T & Any = delegate.createByteList(input)

    override fun createIntList(input: IntStream): T & Any = delegate.createIntList(input)

    override fun createLongList(input: LongStream): T & Any = delegate.createLongList(input)

    override fun listBuilder(): ListBuilder<T> = ListBuilder.Default(this)

    override fun createMap(map: Stream<Pair<T, T>>): T & Any = delegate.createMap(map)

    override fun mapBuilder(): RecordBuilder<T> = RecordBuilder.Default(this)

    override fun <U> convertTo(outOps: DataOps<U>, input: T & Any): U & Any = delegate.convertTo(outOps, input)
}

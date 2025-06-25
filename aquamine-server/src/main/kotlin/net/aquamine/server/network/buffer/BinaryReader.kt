package net.aquamine.server.network.buffer

import com.google.common.collect.Maps
import it.unimi.dsi.fastutil.ints.IntArrayList
import it.unimi.dsi.fastutil.ints.IntList
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.auth.ProfileProperty
import net.aquamine.api.util.Direction
import net.aquamine.api.util.Rotation
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.server.auth.AquaGameProfile
import net.aquamine.server.auth.AquaProfileProperty
import net.aquamine.server.coordinate.BlockPos
import net.aquamine.server.item.ItemFactory
import net.aquamine.server.item.KryptonItemStack
import net.aquamine.server.registry.KryptonRegistries
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.util.crypto.Crypto
import net.aquamine.server.util.hit.BlockHitResult
import net.aquamine.server.util.map.IntBiMap
import net.aquamine.server.util.math.Maths
import net.aquamine.server.util.readVarInt
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.EndTag
import xyz.axie.nbt.io.TagCompression
import xyz.axie.nbt.io.TagIO
import org.kryptonmc.serialization.Decoder
import org.kryptonmc.serialization.nbt.NbtOps
import java.io.InputStream
import java.nio.ByteBuffer
import java.security.PublicKey
import java.time.Instant
import java.util.BitSet
import java.util.EnumSet
import java.util.UUID
import java.util.function.Function
import java.util.function.IntFunction

class BinaryReader(private val buffer: ByteBuffer) {

    private var inputStream: InputStream? = null

    fun readableBytes(): Int = buffer.remaining()

    fun readByte(): Byte = buffer.get()

    fun readShort(): Short = buffer.short

    fun readUnsignedShort(): Int = buffer.short.toInt() and 0xFFFF

    fun readInt(): Int = buffer.int

    fun readLong(): Long = buffer.long

    fun readBoolean(): Boolean = buffer.get() == 1.toByte()

    fun readFloat(): Float = buffer.float

    fun readDouble(): Double = buffer.double

    fun readBytes(length: Int): ByteArray {
        val bytes = ByteArray(length)
        buffer.get(bytes, 0, length)
        return bytes
    }

    fun readAllBytes(): ByteArray = readBytes(buffer.remaining())

    fun readVarInt(): Int = buffer.readVarInt()

    fun readVarLong(): Long {
        var result = 0L
        var shift = 0
        while (shift < 56) {
            val next = readByte()
            result = result or ((next.toInt() and 0x7F) shl shift).toLong()
            if (next >= 0) return result
            shift += 7
        }
        return result or ((readByte().toInt() and 0xFF) shl 56).toLong()
    }

    fun readString(): String {
        val length = readVarInt()
        return String(readBytes(length), Charsets.UTF_8)
    }

    fun readByteArray(): ByteArray {
        val length = readVarInt()
        return readBytes(length)
    }

    fun readVarIntArray(): IntArray = IntArray(readVarInt()) { readVarInt() }

    fun readLongArray(): LongArray = LongArray(readVarInt()) { readLong() }

    fun readUUID(): UUID = UUID(readLong(), readLong())

    fun readNBT(): CompoundTag {
        val index = buffer.position()
        val type = readByte()
        if (type == EndTag.ID.toByte()) return CompoundTag.EMPTY

        buffer.position(index) // Reset the head if it's not an end tag so we can read the type again
        if (inputStream == null) inputStream = ByteBufferInputStream(buffer)
        return TagIO.read(inputStream!!, TagCompression.NONE)
    }

    fun readComponent(): Component {
        val text = readString()
        return GsonComponentSerializer.gson().deserialize(text)
    }

    fun readItem(): KryptonItemStack {
        if (!readBoolean()) return KryptonItemStack.EMPTY
        val type = readById(KryptonRegistries.ITEM)!!
        val count = readByte()
        val nbt = readNBT()
        return KryptonItemStack(type, count.toInt(), ItemFactory.create(type, nbt))
    }

    fun readBlockPos(): Vec3i = BlockPos.unpack(readLong())

    fun readRotation(): Rotation = Rotation(readFloat(), readFloat(), readFloat())

    fun readVec3d(): Vec3d = Vec3d(readDouble(), readDouble(), readDouble())

    fun readKey(): Key = Key.key(readString())

    fun readGameProfile(): GameProfile = AquaGameProfile.full(readUUID(), readString(), readProfileProperties())

    fun readProfileProperty(): ProfileProperty = AquaProfileProperty(readString(), readString(), readNullable { it.readString() })

    fun readProfileProperties(): List<ProfileProperty> = readList { it.readProfileProperty() }

    fun readInstant(): Instant = Instant.ofEpochMilli(readLong())

    fun readPublicKey(): PublicKey = Crypto.bytesToRsaPublicKey(readByteArray())

    fun readBlockHitResult(): BlockHitResult {
        val position = readBlockPos()
        val direction = readEnum<Direction>()
        val cursorX = readFloat().toDouble()
        val cursorY = readFloat().toDouble()
        val cursorZ = readFloat().toDouble()
        val isInside = readBoolean()
        return BlockHitResult(Vec3d(position.x + cursorX, position.y + cursorY, position.z + cursorZ), direction, position, isInside)
    }

    inline fun <T> readNullable(reader: (BinaryReader) -> T): T? {
        if (!readBoolean()) return null
        return reader(this)
    }

    fun <T> readById(registry: IntBiMap<T>): T? = registry.get(readVarInt())

    fun <T> readById(registry: IntBiMap<Holder<T>>, reader: Reader<T & Any>): Holder<T> {
        val id = readVarInt()
        if (id == 0) return Holder.Direct(reader.apply(this))
        return checkNotNull(registry.get(id - 1)) { "Cannot find registry element with ID $id!" }
    }

    fun <T> decode(decoder: Decoder<T>): T {
        val nbt = readNBT()
        val result = decoder.read(nbt, NbtOps.INSTANCE)
        result.error().ifPresent { error("Failed to decode: ${it.message} $nbt") }
        return result.result().get()
    }

    inline fun <reified T : Enum<T>> readEnum(): T = T::class.java.enumConstants[readVarInt()]

    fun <E : Enum<E>> readEnumSet(type: Class<E>): EnumSet<E> {
        val constants = type.enumConstants
        val bits = readFixedBitSet(constants.size)
        val result = EnumSet.noneOf(type)
        for (i in constants.indices) {
            if (bits.get(i)) result.add(constants[i])
        }
        return result
    }

    inline fun <reified E : Enum<E>> readEnumSet(): EnumSet<E> = readEnumSet(E::class.java)

    fun readBitSet(): BitSet = BitSet.valueOf(readLongArray())

    fun readFixedBitSet(fixedBits: Int): BitSet {
        val length = Maths.positiveCeilDivide(fixedBits, 8)
        val bytes = ByteArray(length)
        buffer.get(bytes, 0, length)
        return BitSet.valueOf(bytes)
    }

    fun readIntIdList(): IntList {
        val size = readVarInt()
        val result = IntArrayList(size)
        for (i in 0 until size) {
            result.add(readVarInt())
        }
        return result
    }

    fun <C : MutableCollection<E>, E> readCollection(collectionCreator: IntFunction<C>, reader: Reader<E>): C {
        val size = readVarInt()
        val result = collectionCreator.apply(size)
        for (i in 0 until size) {
            result.add(reader.apply(this))
        }
        return result
    }

    fun <E> readList(reader: Reader<E>): List<E> {
        return readCollection({ ArrayList(it) }, reader)
    }

    fun <E> readList(maxSize: Int, reader: Reader<E>): List<E> {
        return readCollection({
            if (it > maxSize) error("List cannot be longer than $maxSize!")
            ArrayList(it)
        }, reader)
    }

    fun <M : MutableMap<K, V>, K, V> readMap(mapCreator: IntFunction<M>, keyReader: Reader<K>, valueReader: Reader<V>): M {
        val size = readVarInt()
        val result = mapCreator.apply(size)
        for (i in 0 until size) {
            result.put(keyReader.apply(this), valueReader.apply(this))
        }
        return result
    }

    fun <K, V> readMap(keyReader: Reader<K>, valueReader: Reader<V>): Map<K, V> {
        return readMap({ Maps.newHashMapWithExpectedSize(it) }, keyReader, valueReader)
    }
}

private typealias Reader<T> = Function<BinaryReader, T>

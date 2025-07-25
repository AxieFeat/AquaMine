package net.aquamine.server.network.buffer

import it.unimi.dsi.fastutil.ints.IntList
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.aquamine.api.auth.GameProfile
import net.aquamine.api.auth.ProfileProperty
import net.aquamine.api.resource.ResourceKey
import net.aquamine.api.util.Rotation
import net.aquamine.api.util.Vec3d
import net.aquamine.api.util.Vec3i
import net.aquamine.server.coordinate.BlockPos
import net.aquamine.server.item.AquaItemStack
import net.aquamine.server.registry.AquaRegistries
import net.aquamine.server.registry.holder.Holder
import net.aquamine.server.util.hit.BlockHitResult
import net.aquamine.server.util.map.IntBiMap
import net.aquamine.server.util.math.Maths
import net.aquamine.server.util.writeVarInt
import xyz.axie.nbt.CompoundTag
import xyz.axie.nbt.io.TagCompression
import xyz.axie.nbt.io.TagIO
import net.aquamine.serialization.Encoder
import net.aquamine.serialization.nbt.NbtOps
import net.aquamine.server.potion.AquaPotionEffect
import java.io.OutputStream
import java.nio.ByteBuffer
import java.security.PublicKey
import java.time.Instant
import java.util.BitSet
import java.util.EnumSet
import java.util.UUID
import java.util.function.BiConsumer
import java.util.function.Consumer

class BinaryWriter(private val buffer: ByteBuffer) {

    private var outputStream: OutputStream? = null

    fun writeByte(value: Byte) {
        buffer.put(value)
    }

    fun writeShort(value: Short) {
        buffer.putShort(value)
    }

    fun writeInt(value: Int) {
        buffer.putInt(value)
    }

    fun writeLong(value: Long) {
        buffer.putLong(value)
    }

    fun writeBoolean(value: Boolean) {
        buffer.put(if (value) 1 else 0)
    }

    fun writeFloat(value: Float) {
        buffer.putFloat(value)
    }

    fun writeDouble(value: Double) {
        buffer.putDouble(value)
    }

    fun writeBytes(bytes: ByteArray) {
        buffer.put(bytes)
    }

    fun writeVarInt(value: Int) {
        buffer.writeVarInt(value)
    }

    fun writeVarLong(value: Long) {
        when {
            value and (0xFFFFFFFF shl 7) == 0L -> writeByte(value.toByte())
            value and (0xFFFFFFFF shl 14) == 0L -> writeShort((value and 0x7F or 0x80 shl 8 or (value ushr 7)).toShort())
            value and (0xFFFFFFFF shl 21) == 0L -> {
                writeByte((value and 0x7F or 0x80).toByte())
                writeByte(((value shr 7) and 0x7F or 0x80).toByte())
                writeByte((value shr 14).toByte())
            }
            value and (0xFFFFFFFF shl 28) == 0L -> writeInt((value and 0x7F or 0x80 shl 24 or
                    (value ushr 7 and 0x7F or 0x80 shl 16) or (value ushr 14 and 0x7F or 0x80 shl 8) or
                    (value ushr 21)).toInt())
            value and (0xFFFFFFFF shl 35) == 0L -> {
                writeInt((value and 0x7F or 0x80 shl 24 or (value ushr 7 and 0x7F or 0x80 shl 16) or
                        (value ushr 14 and 0x7F or 0x80 shl 8) or (value ushr 21 and 0x7F or 0x80)).toInt())
                writeByte((value ushr 28).toByte())
            }
            value and (0xFFFFFFFF shl 42) == 0L -> {
                writeInt((value and 0x7F or 0x80 shl 24 or (value ushr 7 and 0x7F or 0x80 shl 16) or
                        (value ushr 14 and 0x7F or 0x80 shl 8) or (value ushr 21 and 0x7F or 0x80)).toInt())
                writeShort((value ushr 28 and 0x7F or 0x80 shl 8 or (value ushr 35)).toShort())
            }
            value and (0xFFFFFFFF shl 49) == 0L -> {
                writeInt((value and 0x7F or 0x80 shl 24 or (value ushr 7 and 0x7F or 0x80 shl 16) or
                        (value ushr 14 and 0x7F or 0x80 shl 8) or (value ushr 21 and 0x7F or 0x80)).toInt())
                writeByte((value ushr 28 and 0x7F or 0x80).toByte())
                writeByte((value ushr 35 and 0x7F or 0x80).toByte())
                writeByte((value ushr 42).toByte())
            }
            value and (0xFFFFFFFF shl 56) == 0L -> writeLong(value and 0x7F or 0x80 shl 56 or
                    (value ushr 7 and 0x7F or 0x80 shl 48) or (value ushr 14 and 0x7F or 0x80 shl 40) or
                    (value ushr 21 and 0x7F or 0x80 shl 32) or (value ushr 28 and 0x7F or 0x80 shl 24) or
                    (value ushr 35 and 0x7F or 0x80 shl 16) or (value ushr 42 and 0x7F or 0x80 shl 8) or
                    (value ushr 49))
            value and (0xFFFFFFFF shl 63) == 0L -> {
                writeLong(value and 0x7F or 0x80 shl 56 or (value ushr 7 and 0x7F or 0x80 shl 48) or
                        (value ushr 14 and 0x7F or 0x80 shl 40) or (value ushr 21 and 0x7F or 0x80 shl 32) or
                        (value ushr 28 and 0x7F or 0x80 shl 24) or (value ushr 35 and 0x7F or 0x80 shl 16) or
                        (value ushr 42 and 0x7F or 0x80 shl 8) or (value ushr 49 and 0x7F or 0x80))
                writeByte((value ushr 56).toByte())
            }
            value and (0xFFFFFFFF shl 70) == 0L -> {
                writeLong(value and 0x7F or 0x80 shl 56 or (value ushr 7 and 0x7F or 0x80 shl 48) or
                        (value ushr 14 and 0x7F or 0x80 shl 40) or (value ushr 21 and 0x7F or 0x80 shl 32) or
                        (value ushr 28 and 0x7F or 0x80 shl 24) or (value ushr 35 and 0x7F or 0x80 shl 16) or
                        (value ushr 42 and 0x7F or 0x80 shl 8) or (value ushr 49 and 0x7F or 0x80))
                writeShort((value ushr 56 and 0x7F or 0x80 shl 8 or (value ushr 63)).toShort())
            }
        }
    }

    fun writeString(value: String) {
        val bytes = value.encodeToByteArray()
        writeVarInt(bytes.size)
        writeBytes(bytes)
    }

    fun writeByteArray(value: ByteArray) {
        writeVarInt(value.size)
        writeBytes(value)
    }

    fun writeVarIntArray(value: IntArray) {
        writeVarInt(value.size)
        for (i in value) {
            writeVarInt(i)
        }
    }

    fun writeLongArray(value: LongArray) {
        writeVarInt(value.size)
        for (i in value) {
            writeLong(i)
        }
    }

    fun writeUUID(value: UUID) {
        writeLong(value.mostSignificantBits)
        writeLong(value.leastSignificantBits)
    }

    fun writeNBT(tag: CompoundTag?) {
        if (tag == null) {
            writeByte(0)
            return
        }
        if (outputStream == null) outputStream = ByteBufferOutputStream(buffer)
        TagIO.write(outputStream!!, tag, TagCompression.NONE)
    }

    fun writeComponent(value: Component) {
        writeString(GsonComponentSerializer.gson().serialize(value))
    }

    fun writeItem(item: AquaItemStack) {
        if (item === AquaItemStack.EMPTY) {
            writeBoolean(false)
            return
        }
        writeBoolean(true)
        writeId(AquaRegistries.ITEM, item.type)
        writeByte(item.amount.toByte())
        writeNBT(item.meta.data)
    }

    fun writeBlockPos(pos: Vec3i) {
        writeLong(BlockPos.pack(pos))
    }

    fun writeRotation(rotation: Rotation) {
        writeFloat(rotation.x)
        writeFloat(rotation.y)
        writeFloat(rotation.z)
    }

    fun writeVec3d(pos: Vec3d) {
        writeDouble(pos.x)
        writeDouble(pos.y)
        writeDouble(pos.z)
    }

    fun writeKey(key: Key) {
        writeString(key.asString())
    }

    fun writeResourceKey(key: ResourceKey<*>) {
        writeKey(key.location)
    }

    fun writeGameProfile(profile: GameProfile) {
        writeUUID(profile.uuid)
        writeString(profile.name)
        writeProfileProperties(profile.properties)
    }

    fun writeProfileProperty(property: ProfileProperty) {
        writeString(property.name)
        writeString(property.value)
        writeNullable(property.signature, BinaryWriter::writeString)
    }

    fun writeProfileProperties(properties: Collection<ProfileProperty>) {
        writeCollection(properties, ::writeProfileProperty)
    }

    fun writeInstant(instant: Instant) {
        writeLong(instant.toEpochMilli())
    }

    fun writePublicKey(key: PublicKey) {
        writeByteArray(key.encoded)
    }

    fun writeBlockHitResult(hitResult: BlockHitResult) {
        val position = hitResult.position
        writeBlockPos(position)
        writeEnum(hitResult.direction)
        val location = hitResult.location
        writeFloat((location.x - position.x).toFloat())
        writeFloat((location.y - position.y).toFloat())
        writeFloat((location.z - position.z).toFloat())
        writeBoolean(hitResult.isInside)
    }

    fun writePotion(potion: AquaPotionEffect) {
        writeVarInt(AquaRegistries.POTION_TYPE.getId(potion.type) + 1)
        writeByte(potion.amplifier.toByte())
        writeVarInt(potion.duration)
        writeByte(potion.flags)
    }

    inline fun <T> writeNullable(value: T?, writer: (BinaryWriter, T) -> Unit) {
        writeBoolean(value != null)
        if (value != null) writer(this, value)
    }

    fun writeEnum(value: Enum<*>) {
        writeVarInt(value.ordinal)
    }

    fun <T> encode(encoder: Encoder<T>, value: T) {
        val result = encoder.encodeStart(value, NbtOps.INSTANCE)
        result.error().ifPresent { error("Failed to encode: ${it.message} $value") }
        writeNBT(result.result().get() as? CompoundTag)
    }

    fun <E : Enum<E>> writeEnumSet(set: EnumSet<E>, type: Class<E>) {
        val constants = type.enumConstants
        val bits = BitSet(constants.size)
        for (i in constants.indices) {
            bits.set(i, set.contains(constants[i]))
        }
        writeFixedBitSet(bits, constants.size)
    }

    inline fun <reified E : Enum<E>> writeEnumSet(set: EnumSet<E>) {
        writeEnumSet(set, E::class.java)
    }

    fun writeBitSet(set: BitSet) {
        writeLongArray(set.toLongArray())
    }

    fun writeFixedBitSet(set: BitSet, fixedBits: Int) {
        if (set.length() > fixedBits) error("Bit set $set has different length than expected bits $fixedBits!")
        writeBytes(set.toByteArray().copyOf(Maths.positiveCeilDivide(fixedBits, 8)))
    }

    fun <T> writeId(registry: IntBiMap<T>, value: T) {
        val id = registry.getId(value)
        require(id != -1) { "Cannot find ID for $value in $registry!" }
        writeVarInt(id)
    }

    fun <T> writeId(registry: IntBiMap<Holder<T>>, entry: Holder<T>, writer: BiConsumer<BinaryWriter, T>) {
        when (entry.kind()) {
            Holder.Kind.REFERENCE -> {
                val id = registry.getId(entry)
                require(id != -1) { "Cannot find ID for value ${entry.value()} in registry $registry!" }
                writeVarInt(id + 1)
            }
            Holder.Kind.DIRECT -> {
                writeVarInt(0)
                writer.accept(this, entry.value())
            }
        }
    }

    fun <E> writeCollection(collection: Collection<E>, writer: Consumer<E>) {
        writeVarInt(collection.size)
        collection.forEach(writer)
    }

    fun <K, V> writeMap(map: Map<K, V>, keyWriter: BiConsumer<BinaryWriter, K>, valueWriter: BiConsumer<BinaryWriter, V>) {
        writeVarInt(map.size)
        map.forEach { (key, value) ->
            keyWriter.accept(this, key)
            valueWriter.accept(this, value)
        }
    }

    fun writeIntIdList(ids: IntList) {
        writeVarInt(ids.size)
        ids.forEach(::writeVarInt)
    }
}

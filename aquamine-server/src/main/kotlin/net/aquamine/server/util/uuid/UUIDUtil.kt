package net.aquamine.server.util.uuid

import net.aquamine.server.util.serialization.Codecs
import xyz.axie.nbt.IntArrayTag
import xyz.axie.nbt.Tag
import org.kryptonmc.serialization.Codec
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.Arrays
import java.util.UUID

object UUIDUtil {

    @JvmField
    val NIL_UUID: UUID = UUID(0L, 0L)
    @JvmField
    val CODEC: Codec<UUID> = Codec.INT_STREAM.comapFlatMap(
        { input -> Codecs.fixedSize(input, 4).map { fromIntArray(it) } },
        { Arrays.stream(toIntArray(it)) }
    )

    @JvmStatic
    fun toByteArray(uuid: UUID): ByteArray {
        val result = ByteArray(16)
        ByteBuffer.wrap(result).order(ByteOrder.BIG_ENDIAN).putLong(uuid.mostSignificantBits).putLong(uuid.leastSignificantBits)
        return result
    }

    @JvmStatic
    fun fromIntArray(data: IntArray): UUID = UUID(
        data[0].toLong() shl 32 or (data[1].toLong() and 0xFFFFFFFFL),
        data[2].toLong() shl 32 or (data[3].toLong() and 0xFFFFFFFFL)
    )

    @JvmStatic
    fun toIntArray(uuid: UUID): IntArray {
        val most = uuid.mostSignificantBits
        val least = uuid.leastSignificantBits
        return intArrayOf((most shr 32).toInt(), most.toInt(), (least shr 32).toInt(), least.toInt())
    }

    @JvmStatic
    fun createOfflinePlayerId(name: String): UUID = UUID.nameUUIDFromBytes("OfflinePlayer:$name".encodeToByteArray())

    @JvmStatic
    fun loadUUID(tag: Tag): UUID {
        require(tag.id == IntArrayTag.ID) { "Expected UUID tag to be of type ${IntArrayTag.TYPE.prettyName}, was ${tag.type().prettyName}!" }
        val array = (tag as IntArrayTag).data
        require(array.size == 4) { "Expected UUID array to be of length 4, was ${array.size}!" }
        return fromIntArray(array)
    }

    @JvmStatic
    fun createUUID(value: UUID): IntArrayTag = IntArrayTag.of(toIntArray(value))
}

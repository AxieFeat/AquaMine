package net.aquamine.server.util

import net.kyori.adventure.key.InvalidKeyException
import net.kyori.adventure.key.Key
import net.aquamine.serialization.Codec
import net.aquamine.serialization.DataResult

object Keys {

    @JvmField
    val CODEC: Codec<Key> = Codec.STRING.comapFlatMap({ read(it) }, { it.asString() }).stable()

    @JvmStatic
    fun isValidCharacter(char: Char): Boolean = Key.allowedInValue(char) || char == ':'

    @JvmStatic
    fun create(namespace: String, path: String): Key? {
        return try {
            Key.key(namespace, path)
        } catch (_: InvalidKeyException) {
            null
        }
    }

    @JvmStatic
    fun create(input: String): Key? {
        return try {
            Key.key(input)
        } catch (_: InvalidKeyException) {
            null
        }
    }

    @JvmStatic
    fun read(value: String): DataResult<Key> {
        return try {
            DataResult.success(Key.key(value))
        } catch (exception: InvalidKeyException) {
            DataResult.error("$value is not a valid resource location! ${exception.message}")
        }
    }

    @JvmStatic
    fun translation(type: String, key: Key?): String {
        if (key == null) return "$type.unregistered_sadface"
        return "$type.${key.namespace()}.${key.value().replace('/', '.')}"
    }
}

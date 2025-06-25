package net.aquamine.server.pack.metadata

import com.google.gson.JsonObject
import org.kryptonmc.serialization.Codec
import org.kryptonmc.serialization.gson.GsonOps

interface MetadataSectionSerializer<T> {

    fun metadataSectionName(): String

    fun fromJson(json: JsonObject): T

    companion object {

        @JvmStatic
        fun <T> fromCodec(name: String, codec: Codec<T>): MetadataSectionSerializer<T> = object : MetadataSectionSerializer<T> {
            override fun metadataSectionName(): String = name

            override fun fromJson(json: JsonObject): T = codec.read(json, GsonOps.INSTANCE).getOrThrow(false) {}
        }
    }
}

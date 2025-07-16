package net.aquamine.server.pack.metadata

import com.google.gson.JsonObject
import net.aquamine.serialization.Codec
import net.aquamine.serialization.gson.GsonOps

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

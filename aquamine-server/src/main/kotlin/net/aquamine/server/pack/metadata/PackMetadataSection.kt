package net.aquamine.server.pack.metadata

import com.google.gson.JsonObject
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer

class PackMetadataSection(val description: Component, val format: Int) {

    object Serializer : MetadataSectionSerializer<PackMetadataSection> {

        override fun metadataSectionName(): String = "pack"

        override fun fromJson(json: JsonObject): PackMetadataSection =
            PackMetadataSection(GsonComponentSerializer.gson().deserialize(json.get("description").asString), json.get("pack_format").asInt)
    }
}

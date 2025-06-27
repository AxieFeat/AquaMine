package net.aquamine.server.packet.out.status

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.aquamine.api.auth.GameProfile
import net.aquamine.server.AquaPlatform

@JvmRecord
data class ServerStatus(val motd: Component, val players: Players, val favicon: String?) {

    class Players(val max: Int, var online: Int, var sample: Array<GameProfile> = emptyArray()) {

        companion object : TypeAdapter<Players>() {

            override fun read(`in`: JsonReader?): Players {
                throw UnsupportedOperationException("Reading server status players is not supported!")
            }

            override fun write(out: JsonWriter, value: Players) {
                out.beginObject()
                out.name("max")
                out.value(value.max)
                out.name("online")
                out.value(value.online)
                if (value.sample.isNotEmpty()) {
                    out.name("sample")
                    out.beginArray()
                    value.sample.forEach {
                        out.beginObject()
                        out.name("id")
                        out.value(it.uuid.toString())
                        out.name("name")
                        out.value(it.name)
                        out.endObject()
                    }
                    out.endArray()
                }
                out.endObject()
            }
        }
    }

    companion object : TypeAdapter<ServerStatus>() {

        private val COMPONENT_ADAPTER = GsonComponentSerializer.gson().serializer().getAdapter(Component::class.java)

        override fun read(`in`: JsonReader?): ServerStatus {
            throw UnsupportedOperationException("Reading server status is not supported!")
        }

        override fun write(out: JsonWriter, value: ServerStatus) {
            out.beginObject()
            if (value.motd !== Component.empty()) {
                out.name("description")
                COMPONENT_ADAPTER.write(out, value.motd)
            }
            out.name("players")
            Players.write(out, value.players)
            out.name("version")
            out.beginObject()
            out.name("name")
            out.value(AquaPlatform.minecraftVersion)
            out.name("protocol")
            out.value(AquaPlatform.protocolVersion)
            out.endObject()
            value.favicon?.let {
                out.name("favicon")
                out.value(it)
            }
            out.endObject()
        }
    }
}

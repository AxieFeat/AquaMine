package net.aquamine.server.pack

import net.aquamine.server.AquaPlatform

enum class PackType(val directory: String) {

    CLIENT_RESOURCES("assets"),
    SERVER_DATA("data");

    fun version(): Int = if (this == SERVER_DATA) AquaPlatform.dataPackVersion else AquaPlatform.resourcePackVersion
}

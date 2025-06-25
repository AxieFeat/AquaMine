package net.aquamine.server.pack

import net.aquamine.server.KryptonPlatform

enum class PackType(val directory: String) {

    CLIENT_RESOURCES("assets"),
    SERVER_DATA("data");

    fun version(): Int = if (this == SERVER_DATA) KryptonPlatform.dataPackVersion else KryptonPlatform.resourcePackVersion
}

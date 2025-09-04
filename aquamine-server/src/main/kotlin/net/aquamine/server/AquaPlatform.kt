package net.aquamine.server

import net.aquamine.api.Platform
import java.util.Properties

object AquaPlatform : Platform {

    private val versions = Properties().apply { load(ClassLoader.getSystemResourceAsStream("META-INF/versions.properties")) }

    override val name: String = "AquaMine"
    override val version: String = versions.getProperty("aquamine")
    override val minecraftVersion: String = versions.getProperty("minecraft")
    const val isStableMinecraft: Boolean = true
    override val worldVersion: Int = 4440
    override val protocolVersion: Int = 772
    override val dataPackVersion: Int = 81
    const val resourcePackVersion: Int = 64
    @JvmField
    val dataVersionPrefix: String = versions.getProperty("data")
}

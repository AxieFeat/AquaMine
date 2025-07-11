package net.aquamine.server

import net.aquamine.api.Platform
import java.util.Properties

// TODO: Check on update
object KryptonPlatform : Platform {

    private val versions = Properties().apply { load(ClassLoader.getSystemResourceAsStream("META-INF/versions.properties")) }

    override val name: String = "Krypton"
    override val version: String = versions.getProperty("krypton")
    override val minecraftVersion: String = versions.getProperty("minecraft")
    const val isStableMinecraft: Boolean = true
    override val worldVersion: Int = 3105
    override val protocolVersion: Int = 761
    override val dataPackVersion: Int = 10
    const val resourcePackVersion: Int = 12
    @JvmField
    val dataVersionPrefix: String = versions.getProperty("data")
}

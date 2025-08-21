package net.aquamine.spark

import me.lucko.spark.common.platform.PlatformInfo
import net.aquamine.api.Server

class AquaPlatformInfo(
    val server: Server
) : PlatformInfo {

    override fun getName(): String = server.platform.name

    override fun getBrand(): String = server.platform.name

    override fun getVersion(): String = server.platform.version

    override fun getMinecraftVersion(): String = server.platform.minecraftVersion

    override fun getType(): PlatformInfo.Type = PlatformInfo.Type.SERVER
}

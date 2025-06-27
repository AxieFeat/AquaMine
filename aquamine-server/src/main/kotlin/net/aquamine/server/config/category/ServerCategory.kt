package net.aquamine.server.config.category

import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Comment

@ConfigSerializable
@JvmRecord
data class ServerCategory(
    @Comment("The IP used by players to connect. 0.0.0.0 means listen on all interfaces.")
    val ip: String = "0.0.0.0",
    @Comment("The port used by players to connect.")
    val port: Int = 25565,
    @Comment("Whether the server authenticates users with Mojang.")
    val onlineMode: Boolean = true,
    @Comment("The threshold at which packets larger will be compressed. Set to -1 to disable.")
    val compressionThreshold: Int = 256,
    @Comment("If console messages should be sent to admins with the permission aquamine.broadcast_admins")
    val broadcastConsoleToAdmins: Boolean = true,
    @Comment("Settings for the server resource pack")
    val resourcePack: ResourcePackCategory = ResourcePackCategory()
)

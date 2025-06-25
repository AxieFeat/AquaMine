package net.aquamine.server.config.category

import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Comment

@ConfigSerializable
@JvmRecord
data class AdvancedCategory(
    @Comment("Whether to use the DSYNC option for saving region files to disk.")
    val synchronizeChunkWrites: Boolean = true,
    @Comment("Whether the server should load and save player data to and from files")
    val serializePlayerData: Boolean = true,
    @Comment("If the server should enforce that all game profiles have properties with valid signatures.")
    val enforceSecureProfiles: Boolean = true,
    @Comment("The maximum number of updates to block neighbours that may be executed in bulk.")
    val maximumChainedNeighbourUpdates: Int = 1000000,
    @Comment("If we should enable bStats metrics for the server")
    val metrics: Boolean = true,
    @Comment("The duration (in seconds) a single tick must take before the single tick profiler reports it.")
    val saveThreshold: Int = 5,
    @Comment("Whether player chat messages should be sent to the console. Disable if you have a lot of players sending a lot of messages.")
    val logPlayerChatMessages: Boolean = true,
    @Comment("The view (render) distance for entities, in chunks.")
    val entityViewDistance: Int = 5
)

package net.aquamine.server.config

import net.kyori.adventure.serializer.configurate4.ConfigurateComponentSerializer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.aquamine.api.ServerConfig
import net.aquamine.server.config.category.AdvancedCategory
import net.aquamine.server.config.category.ModulesCategory
import net.aquamine.server.config.category.ProxyCategory
import net.aquamine.server.config.category.ServerCategory
import net.aquamine.server.config.category.StatusCategory
import net.aquamine.server.config.category.WorldCategory
import net.aquamine.server.config.serializer.EnumTypeSerializer
import net.aquamine.server.config.serializer.LocaleTypeSerializer
import net.aquamine.server.util.enumhelper.Difficulties
import net.aquamine.server.util.enumhelper.GameModes
import org.spongepowered.configurate.ConfigurationOptions
import org.spongepowered.configurate.hocon.HoconConfigurationLoader
import org.spongepowered.configurate.kotlin.extensions.get
import org.spongepowered.configurate.kotlin.objectMapperFactory
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Comment
import java.io.IOException
import java.nio.file.Path

@ConfigSerializable
@JvmRecord
data class AquaConfig(
    @Comment("The main server settings.")
    val server: ServerCategory = ServerCategory(),
    @Comment("Status configuration")
    val status: StatusCategory = StatusCategory(),
    @Comment("Global world configuration options")
    val world: WorldCategory = WorldCategory(),
    @Comment("Advanced settings. Don't touch these unless you know what you're doing.")
    val advanced: AdvancedCategory = AdvancedCategory(),
    @Comment("Proxy IP forwarding settings.")
    val proxy: ProxyCategory = ProxyCategory(),
    @Comment("Module settings.")
    val modules: ModulesCategory = ModulesCategory()
) : ServerConfig {

    override val isOnline: Boolean
        get() = server.onlineMode
    override val ip: String
        get() = server.ip
    override val port: Int
        get() = server.port
    override val motd: Component
        get() = status.motd
    override val maxPlayers: Int
        get() = status.maxPlayers

    companion object {

        private val HEADER = """
            This is the main AquaMine configuration file. All settings in this file apply globally
            across the entire server, regardless of what they are.
        """.trimIndent()

        private val OPTIONS = ConfigurationOptions.defaults()
            .header(HEADER)
            .serializers { builder ->
                builder.registerAll(ConfigurateComponentSerializer.builder()
                    .scalarSerializer(LegacyComponentSerializer.builder()
                        .character(LegacyComponentSerializer.AMPERSAND_CHAR)
                        .extractUrls()
                        .hexColors()
                        .build())
                    .outputStringComponents(true)
                    .build()
                    .serializers())
                builder.register(EnumTypeSerializer.of("difficulty", Difficulties::fromId, Difficulties::fromName))
                builder.register(EnumTypeSerializer.of("game mode", GameModes::fromId, GameModes::fromName))
                builder.register(LocaleTypeSerializer)
                builder.registerAnnotatedObjects(objectMapperFactory())
            }

        @JvmStatic
        fun load(path: Path): AquaConfig {
            val loader = HoconConfigurationLoader.builder().path(path).defaultOptions(OPTIONS).build()
            val node = loader.load()
            val config = node.get<AquaConfig>() ?: throw IOException("Unable to load configuration!")
            loader.save(node)
            return config
        }
    }
}

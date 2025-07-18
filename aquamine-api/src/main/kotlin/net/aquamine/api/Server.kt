package net.aquamine.api

import net.aquamine.api.command.CommandManager
import net.kyori.adventure.audience.ForwardingAudience
import net.aquamine.api.command.ConsoleSender
import net.aquamine.api.entity.player.Player
import net.aquamine.api.event.GlobalEventNode
import net.aquamine.api.plugin.PluginManager
import net.aquamine.api.registry.RegistryHolder
import net.aquamine.api.scheduling.Scheduler
import net.aquamine.api.service.ServicesManager
import net.aquamine.api.util.FactoryProvider
import net.aquamine.api.world.WorldManager
import net.aquamine.api.user.UserManager
import java.util.UUID

/**
 * The server is the center of the API. It provides access to everything and
 * is the central manager for most of the server.
 *
 * You can use the server to retrieve information, managers for various aspects
 * of the API, configuration options, status, and players.
 */
interface Server : ForwardingAudience {

    /**
     * Information about this server implementation.
     */
    val platform: Platform

    /**
     * The world manager for this server.
     *
     * The world manager is used to load, save, create, and update
     * worlds.
     */
    val worldManager: WorldManager

    /**
     * The command manager for this server.
     *
     * This is used to register and unregister commands.
     */
    val commandManager: CommandManager

    /**
     * The plugin manager for this server.
     *
     * This is used to retrieve plugins and check whether they have
     * been initialized or not.
     */
    val pluginManager: PluginManager

    /**
     * The services manager for this server.
     */
    val servicesManager: ServicesManager

    /**
     * The global event node for the server.
     *
     * This is the root of the event tree. All other event nodes should be
     * registered with this node, or they will not receive any events from
     * the server.
     *
     * You should not register any event listeners to this node. It is
     * designed for calling events and registering child nodes.
     *
     * If you wish to register listeners, use the event node that is provided
     * with your plugin, which can be injected in to your plugin's main class.
     */
    val eventNode: GlobalEventNode

    /**
     * The global registry holder for the server.
     */
    val registryHolder: RegistryHolder

    /**
     * The factory provider for this server.
     *
     * This provides factories from the implementation, usually used to
     * construct certain things, like an object, or a builder.
     */
    val factoryProvider: FactoryProvider

    /**
     * The user manager for this server.
     */
    val userManager: UserManager

    /**
     * The scheduler for this server.
     *
     * This can be used to run and schedule asynchronous tasks.
     */
    val scheduler: Scheduler

    /**
     * The configuration for the server.
     */
    val config: ServerConfig

    /**
     * The list of online players.
     */
    val players: Collection<Player>

    /**
     * The console's [net.aquamine.api.command.Sender] object.
     */
    val console: ConsoleSender

    /**
     * Gets the online player with the given [uuid], or returns null if there
     * is no player online with the given [uuid].
     *
     * @param uuid The UUID.
     *
     * @return The player, or null if not present.
     */
    fun getPlayer(uuid: UUID): Player?

    /**
     * Gets the online player with the given [name], or returns null if there
     * is no player online with the given [name].
     *
     * Warning: Usernames are not unique past a single session. Do **not** use
     * usernames to uniquely identify users!
     *
     * @param name The player's name.
     *
     * @return The player, or null if not present.
     */
    fun getPlayer(name: String): Player?

    /**
     * Attempts save all and stop the server.
     */
    fun stop()

}

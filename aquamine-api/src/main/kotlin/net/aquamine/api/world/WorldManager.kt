package net.aquamine.api.world

import net.kyori.adventure.key.Key
import net.aquamine.api.Server
import net.aquamine.api.resource.ResourceKey
import java.util.concurrent.CompletableFuture

/**
 * The world manager for this server. Can be used to retrieve loaded worlds,
 * or to load, save and update existing worlds.
 */
interface WorldManager {

    /**
     * The server this world manager is bound to.
     */
    val server: Server

    /**
     * The map of all currently loaded worlds by dimension.
     */
    val worlds: Map<ResourceKey<World>, World>

    /**
     * The default world for this server.
     *
     * The implementation will define what the default world is.
     */
    val default: World

    /**
     * Gets the loaded world with the given resource [key], or null if there is
     * no world loaded with the given resource [key].
     *
     * @param key The resource key.
     *
     * @return The loaded world with the key, or null if not present.
     */
    fun getWorld(key: Key): World?

    /**
     * Checks if there is currently a world loaded with the given [key].
     *
     * @param key The resource key for the world.
     * @return `true` if there is a world loaded with the given key, `false`
     * otherwise.
     */
    fun isLoaded(key: Key): Boolean

    /**
     * Loads a world by its resource key, if there is a world that can be
     * loaded from the given key.
     *
     * If no world can be loaded from the given key, this returns a null
     * result.
     *
     * @param key The resource key for the world.
     * @return The loaded world, or null if no world could be loaded from
     * the key.
     */
    fun loadWorld(key: Key): CompletableFuture<out World?>

    /**
     * Saves the given [world] to disk.
     *
     * @param world The world to save.
     */
    fun saveWorld(world: World): CompletableFuture<Void>
}

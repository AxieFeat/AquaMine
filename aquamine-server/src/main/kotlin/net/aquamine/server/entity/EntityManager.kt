package net.aquamine.server.entity

import org.apache.logging.log4j.LogManager
import net.aquamine.server.entity.player.KryptonPlayer
import net.aquamine.server.event.entity.KryptonRemoveEntityEvent
import net.aquamine.server.event.entity.KryptonSpawnEntityEvent
import net.aquamine.server.packet.out.play.PacketOutUpdateTime
import net.aquamine.server.world.KryptonWorld
import space.vectrix.flare.fastutil.Int2ObjectSyncMap
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

/**
 * Manages all entities that exist in a world.
 *
 * This provides fast lookups for entities by both internal ID and unique ID (UUID), and also
 * supports loading and saving entities to and from chunks.
 */
class EntityManager(private val world: KryptonWorld) {

    private val entityTracker = world.entityTracker
    private val byId = Int2ObjectSyncMap.hashmap<KryptonEntity>()
    private val byUUID = ConcurrentHashMap<UUID, KryptonEntity>()

    fun entities(): MutableCollection<KryptonEntity> = byId.values

    fun getById(id: Int): KryptonEntity? = byId.get(id)

    fun getByUUID(uuid: UUID): KryptonEntity? = byUUID.get(uuid)

    /**
     * Spawns the entity in to the world, starting its tracking and ticking, and making it
     * viewable by players.
     *
     * This will not spawn the entity under the following conditions:
     * * The world the entity is in is not the world this manager is managing
     * * The entity's UUID is already in use by another entity
     * * The entity's spawn event is denied
     * * The chunk the entity is in is not loaded (throws an error)
     */
    fun spawnEntity(entity: KryptonEntity) {
        if (entity.world != world) return
        if (byUUID.containsKey(entity.uuid)) {
            LOGGER.error("UUID collision! UUID for entity ${entity.id} was the same as that of entity ${byUUID.get(entity.uuid)?.id}!")
            LOGGER.warn("Refusing to spawn entity with ID ${entity.id}.")
            return
        }

        val event = world.server.eventNode.fire(KryptonSpawnEntityEvent(entity, world))
        if (!event.isAllowed()) return

        val chunk = checkNotNull(world.getChunk(entity.position.chunkX(), entity.position.chunkZ())) {
            "The chunk that entity ${entity.id} is in is not loaded!"
        }

        entityTracker.add(entity, entity.position, entity.trackingTarget, entity.trackingViewCallback)
        byId.put(entity.id, entity)
        byUUID.put(entity.uuid, entity)
        world.server.tickDispatcher().queueElementUpdate(entity, chunk)
    }

    /**
     * Spawns the player in to the world, starting its tracking and ticking, and making it
     * viewable by other players.
     *
     * This will not spawn the player under the following conditions:
     * * The world the player is in is not the world this manager is managing
     * * The chunk the player is in is not loaded (throws an error)
     */
    fun spawnPlayer(player: KryptonPlayer) {
        if (player.world != world) return

        // TODO: World border
        player.connection.send(PacketOutUpdateTime.create(world.data))

        val chunk = world.loadChunk(player.position.chunkX(), player.position.chunkZ())
        if (chunk == null) {
            LOGGER.error("The chunk that player ${player.name} is loading in to is not loaded! Refusing to spawn ${player.name}!")
            return
        }

        entityTracker.add(player, player.position, player.trackingTarget, player.trackingViewCallback)
        byId.put(player.id, player)
        byUUID.put(player.uuid, player)
        world.server.tickDispatcher().queueElementUpdate(player, chunk)
    }

    /**
     * Removes the entity from the world, stopping its tracking and ticking, and making it
     * no longer viewable by players.
     *
     * This will not remove the entity under the following conditions:
     * * The world the entity is in is not the world this manager is managing
     * * The entity's removal event is denied
     */
    fun removeEntity(entity: KryptonEntity) {
        if (entity.world != world) return

        val event = world.server.eventNode.fire(KryptonRemoveEntityEvent(entity, world))
        if (!event.isAllowed()) return

        entityTracker.remove(entity, entity.trackingTarget, entity.trackingViewCallback)
        byId.remove(entity.id)
        byUUID.remove(entity.uuid)
        world.scoreboard.onEntityRemoved(entity)
        world.server.tickDispatcher().queueElementRemove(entity)
    }

    companion object {

        private val LOGGER = LogManager.getLogger()
    }
}

package net.aquamine.server.entity

import org.apache.logging.log4j.LogManager
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.event.entity.AquaRemoveEntityEvent
import net.aquamine.server.event.entity.AquaSpawnEntityEvent
import net.aquamine.server.packet.out.play.PacketOutUpdateTime
import net.aquamine.server.world.AquaWorld
import space.vectrix.flare.fastutil.Int2ObjectSyncMap
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

/**
 * Manages all entities that exist in a world.
 *
 * This provides fast lookups for entities by both internal ID and unique ID (UUID), and also
 * supports loading and saving entities to and from chunks.
 */
class EntityManager(private val world: AquaWorld) {

    private val entityTracker = world.entityTracker
    private val byId = Int2ObjectSyncMap.hashmap<AquaEntity>()
    private val byUUID = ConcurrentHashMap<UUID, AquaEntity>()

    fun entities(): MutableCollection<AquaEntity> = byId.values

    fun getById(id: Int): AquaEntity? = byId.get(id)

    fun getByUUID(uuid: UUID): AquaEntity? = byUUID.get(uuid)

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
    fun spawnEntity(entity: AquaEntity) {
        if (entity.world != world) return
        if (byUUID.containsKey(entity.uuid)) {
            LOGGER.error("UUID collision! UUID for entity ${entity.id} was the same as that of entity ${byUUID.get(entity.uuid)?.id}!")
            LOGGER.warn("Refusing to spawn entity with ID ${entity.id}.")
            return
        }

        val event = world.server.eventNode.fire(AquaSpawnEntityEvent(entity, world))
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
    fun spawnPlayer(player: AquaPlayer) {
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
    fun removeEntity(entity: AquaEntity) {
        if (entity.world != world) return

        val event = world.server.eventNode.fire(AquaRemoveEntityEvent(entity, world))
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

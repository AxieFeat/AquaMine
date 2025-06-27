package net.aquamine.server.entity.tracking

import it.unimi.dsi.fastutil.ints.IntOpenHashSet
import it.unimi.dsi.fastutil.ints.IntSet
import net.aquamine.api.util.Position
import net.aquamine.server.entity.AquaEntity
import net.aquamine.server.entity.player.AquaPlayer
import net.aquamine.server.world.AquaWorld
import java.util.Collections
import java.util.function.Consumer

/**
 * This engine keeps track of what players are viewing an entity, and what entities an entity can see.
 *
 * This is primarily based off of Minestom's `ViewEngine`, with the manual viewing completely stripped
 * out, as it is not something we want to support in the API, and there is no way we would want it in
 * the backend.
 */
class EntityViewingEngine(private val entity: AquaEntity) {

    // The lock to use when synchronizing operations on the engine.
    // This is a separate field to make it easier for us to change the lock if we want to, and
    // to make it clear we are synchronizing on a lock.
    private val mutex: Any = this
    // A view of all viewers of the entity.
    private val viewers = SetImpl()
    // The currently known location of the entity.
    @Volatile
    private var trackedLocation: TrackedLocation? = null

    // A tracker for all players that are viewing this entity.
    private val viewable: Tracker<AquaPlayer> = Tracker({ player ->
        val lock1 = if (player.id < entity.id) player else entity
        val lock2 = if (lock1 === entity) player else entity
        synchronized(lock1.viewingEngine.mutex) {
            synchronized(lock2.viewingEngine.mutex) {
                entity.viewingEngine.viewable.register(player)
                player.viewingEngine.viewing.register(entity)
            }
        }
        entity.showToViewer(player)
    }, { player ->
        val lock1 = if (player.id < entity.id) player else entity
        val lock2 = if (lock1 === entity) player else entity
        synchronized(lock1.viewingEngine.mutex) {
            synchronized(lock2.viewingEngine.mutex) {
                entity.viewingEngine.viewable.unregister(player)
                player.viewingEngine.viewing.unregister(entity)
            }
        }
        entity.hideFromViewer(player)
    })
    // A tracker for all entities that this entity is viewing (primarily for players).
    private val viewing: Tracker<AquaEntity> = Tracker(
        if (entity is AquaPlayer) Consumer { it.viewingEngine.viewable.addition!!.accept(entity) } else null,
        if (entity is AquaPlayer) Consumer { it.viewingEngine.viewable.removal!!.accept(entity) } else null
    )

    fun viewers(): Set<AquaPlayer> = viewers

    /**
     * Call to indicate that the entity has moved between worlds or to a different position.
     */
    fun updateTracker(world: AquaWorld, position: Position) {
        trackedLocation = TrackedLocation(world, position)
    }

    /**
     * Should be called when the entity enters the view of this entity.
     *
     * As all viewing is automatic, this should not need to be called outside of the
     * tracking view callback within the entity.
     */
    fun handleEnterView(entity: AquaEntity) {
        handleViewUpdate(entity, viewing.addition, viewable.addition)
    }

    /**
     * Should be called when the entity exits the view of this entity.
     *
     * As all viewing is automatic, this should not need to be called outside of the
     * tracking view callback within the entity.
     */
    fun handleExitView(entity: AquaEntity) {
        handleViewUpdate(entity, viewing.removal, viewable.removal)
    }

    private fun handleViewUpdate(entity: AquaEntity, viewer: Consumer<AquaEntity>?, viewable: Consumer<AquaPlayer>?) {
        if (this.entity is AquaPlayer) viewer?.accept(entity)
        if (entity is AquaPlayer) viewable?.accept(entity)
    }

    @JvmRecord
    private data class TrackedLocation(val world: AquaWorld, val position: Position)

    private class Tracker<E : AquaEntity>(val addition: Consumer<E>?, val removal: Consumer<E>?) {

        // Contains all the entity IDs that are viewable by this tracker
        val ids: IntSet = IntOpenHashSet()

        fun isRegistered(entity: E): Boolean = ids.contains(entity.id)

        fun register(entity: E) {
            ids.add(entity.id)
        }

        fun unregister(entity: E) {
            ids.remove(entity.id)
        }
    }

    private inner class SetImpl : AbstractSet<AquaPlayer>() {

        override val size: Int
            get() = synchronized(mutex) { viewable.ids.size }

        override fun isEmpty(): Boolean = synchronized(mutex) { viewable.ids.isEmpty() }

        override fun contains(element: AquaPlayer): Boolean = synchronized(mutex) { viewable.isRegistered(element) }

        override fun iterator(): Iterator<AquaPlayer> {
            val players: MutableList<AquaPlayer>
            synchronized(mutex) {
                val bitSet = viewable.ids
                if (bitSet.isEmpty()) return Collections.emptyIterator()

                players = ArrayList(bitSet.size)
                val iterator = bitSet.iterator()
                while (iterator.hasNext()) {
                    val id = iterator.nextInt()
                    val player = entity.world.entityManager.getById(id) as? AquaPlayer
                    if (player != null) players.add(player)
                }
            }
            return players.iterator()
        }
    }
}

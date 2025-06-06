package net.aquamine.api.event

import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import net.aquamine.api.AquaMine
import java.util.function.BiPredicate
import java.util.function.Consumer
import java.util.function.Predicate

/**
 * A single node in an event tree.
 *
 * A node may contain any number of children and/or listeners. When an event
 * is called on a node, the node will first check if it has any listeners for
 * the event, calling those first, then the event will propagate down the tree
 * to all children of the node, in order of their priority.
 *
 * If an event in the tree is canceled, it will not propagate to any other
 * nodes, and its dispatching will be considered complete. No further event
 * listeners will receive the event.
 *
 * Every event node has a name, which should be uniquely identifiable and
 * will be useful for debugging, as well as identifying the node in the tree.
 */
interface EventNode<T : Event> {

    /**
     * The type of events this node can have listeners for.
     */
    val eventType: Class<T>

    /**
     * The name of this node.
     *
     * This is used to identify the node in the tree, and so should ideally be
     * unique, and not conflict with other nodes.
     */
    val name: String

    /**
     * The parent of this node.
     *
     * If this node is at the root of the tree, this will be null.
     */
    val parent: EventNode<in T>?

    /**
     * The children of this node.
     *
     * If this node is a leaf on the tree, this will be empty.
     */
    val children: Set<EventNode<T>>

    /**
     * The priority of this node.
     *
     * Due to the design of the event system, this should rarely need to be
     * used. It is useful when you wish to override the default execution
     * order of event nodes, which is based on the order in which they are
     * registered.
     */
    var priority: Int

    /**
     * Checks if this event node has a listener for the given event [type].
     *
     * Due to the nature of event nodes, this method will check both this node
     * and all of its children for listeners.
     *
     * @param type The event type.
     *
     * @return `true` if this node has a listener for the given event type.
     */
    fun hasListener(type: Class<out T>): Boolean

    /**
     * Fires the given [event] on this event node.
     *
     * Due to the nature of event nodes, this method will propagate the event
     * to any nodes that are registered children of this event node.
     *
     * @param E The event type.
     * @param event The event to fire.
     */
    fun <E : T> fire(event: E): E

    /**
     * Adds the given [node] as a child of this event node.
     *
     * @param node The node to add.
     *
     * @return This event node.
     */
    fun addChild(node: EventNode<out T>)

    /**
     * Removes the given [node] as a child of this event node.
     *
     * @param node The node to remove.
     *
     * @return This event node.
     */
    fun removeChild(node: EventNode<out T>)

    /**
     * Registers the given event [listener] to this event node.
     *
     * @param listener The listener to add.
     */
    fun registerListener(listener: EventListener<out T>)

    /**
     * Registers an event listener for the given [eventType] to this event
     * node, calling the given [handler] when the event is fired.
     *
     * @param E The type of event to listen for.
     * @param eventType The type of event to listen for.
     * @param handler The handler for the event.
     *
     * @return This event node.
     */
    fun <E : T> registerListener(eventType: Class<E>, handler: Consumer<E>) {
        registerListener(EventListener.of(eventType, handler))
    }

    /**
     * Registers all event listeners in the given [listenerClass] to this
     * event node.
     *
     * This is used to register event listeners that are defined in a class
     * using the [Listener] annotation.
     *
     * @param listenerClass The listener class.
     *
     * @return This event node.
     */
    fun registerListeners(listenerClass: Any)

    /**
     * Unregisters the given event [listener] from this event node, meaning it
     * will not be called when an event that it listens for is fired.
     *
     * @param listener The listener to unregister.
     *
     * @return This event node.
     */
    fun unregisterListener(listener: EventListener<out T>)

    /**
     * Unregisters all listeners in the given [listenerClass] from this event
     * node.
     *
     * This is used to unregister event listeners that are defined in a class
     * using the [Listener] annotation.
     *
     * @param listenerClass The listener class.
     *
     * @return This event node.
     */
    fun unregisterListeners(listenerClass: Any)

    @TypeFactory
    @ApiStatus.Internal
    interface Factory {

        fun <T : Event, V> create(name: String, filter: EventFilter<T, V>, predicate: BiPredicate<T, V?>?): EventNode<T>
    }

    companion object {

        /**
         * Creates a new event node that accepts any event.
         *
         * @param name The name of the node.
         *
         * @return A new event node.
         */
        @JvmStatic
        fun all(name: String): EventNode<Event> = forType(name, EventFilter.ALL)

        /**
         * Creates a new event node that accepts any event of the given event
         * type [T] that passes the given [filter].
         *
         * For example, you could create an event node that only accepts
         * entity events:
         *
         * Java:
         * ```java
         * final EventNode<PlayerEvent> playerNode = EventNode.forType("abc", EventFilter.PLAYER);
         * ```
         * Kotlin:
         * ```kotlin
         * val playerNode = EventNode.forType("abc", EventFilter.PLAYER)
         * ```
         *
         * @param T The event type.
         * @param V The value type.
         * @param name The name of the node.
         * @param filter The event filter.
         *
         * @return A new event node.
         */
        @JvmStatic
        fun <T : Event, V> forType(name: String, filter: EventFilter<T, V>): EventNode<T> {
            return AquaMine.factory<Factory>().create(name, filter, null)
        }

        /**
         * Creates a new event node that accepts any event of the given event
         * type [T] that passes the given [filter] and the given [predicate].
         *
         * The filter defines the required event type for the node, as well as
         * how to retrieve the handle object to filter on from the event.
         * The predicate is used to check if the event is valid for the node.
         *
         * For example, you could create an event node that only accepts
         * player events where the player is in creative mode:
         *
         * Java:
         * ```java
         * final EventNode<PlayerEvent> playerInCreative = EventNode.filteredForType("abc", EventFilter.PLAYER,
         *         (event, player) -> player.getGameMode() == GameMode.CREATIVE);
         * ```
         * Kotlin:
         * ```kotlin
         * val playerInCreative = EventNode.filteredForType("abc", EventFilter.PLAYER) { event, player -> player.gameMode == GameMode.CREATIVE }
         * ```
         * @param T The event type.
         * @param V The value type.
         * @param name The name of the node.
         * @param filter The event filter.
         * @param predicate The predicate to test.
         *
         * @return A new event node.
         */
        @JvmStatic
        fun <T : Event, V> filteredForType(name: String, filter: EventFilter<T, V>, predicate: BiPredicate<T, V?>): EventNode<T> {
            return AquaMine.factory<Factory>().create(name, filter, predicate)
        }

        /**
         * Creates a new event node that accepts any event of the given event
         * type [T] that passes the given [filter] and the given [predicate].
         *
         * This functions identically to [filteredForType], except that the
         * predicate does not provide the handle object.
         *
         * @param T The event type.
         * @param V The value type.
         * @param name The name of the node.
         * @param filter The event filter.
         * @param predicate The predicate to test.
         *
         * @return A new event node.
         */
        @JvmStatic
        fun <T : Event, V> filteredForEvent(name: String, filter: EventFilter<T, V>, predicate: Predicate<T>): EventNode<T> {
            return filteredForType(name, filter) { event, _ -> predicate.test(event) }
        }

        /**
         * Creates a new event node that accepts any event of the given event
         * type [T] that passes the given [filter] and the given [predicate].
         *
         * This functions identically to [filteredForType], except that the
         * predicate only provides the handle object.
         *
         * @param T The event type.
         * @param V The value type.
         * @param name The name of the node.
         * @param filter The event filter.
         * @param predicate The predicate to test.
         *
         * @return A new event node.
         */
        @JvmStatic
        fun <T : Event, V> filteredForValue(name: String, filter: EventFilter<T, V>, predicate: Predicate<V?>): EventNode<T> {
            return filteredForType(name, filter) { _, handle -> predicate.test(handle) }
        }
    }
}

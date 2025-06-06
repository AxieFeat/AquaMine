package net.aquamine.api.world.chunk

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import org.jetbrains.annotations.ApiStatus
import net.aquamine.api.AquaMine

/**
 * A set of flags used to determine what happens when a block is changed.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
interface BlockChangeFlags {

    /**
     * The raw value of this set of flags.
     */
    val raw: Int

    /**
     * If neighbors should be updated when the block is updated.
     */
    @get:JvmName("updateNeighbours")
    val updateNeighbours: Boolean

    /**
     * If clients with the block in render distance should be notified of its
     * update.
     */
    @get:JvmName("notifyClients")
    val notifyClients: Boolean

    /**
     * If observer blocks should be updated.
     */
    @get:JvmName("updateNeighbourShapes")
    val updateNeighbourShapes: Boolean

    /**
     * If blocks can be destroyed as a result of updating neighbor shapes.
     */
    @get:JvmName("neighbourDrops")
    val neighbourDrops: Boolean

    /**
     * If the block change considers that blocks can be moved in the world.
     */
    @get:JvmName("blockMoving")
    val blockMoving: Boolean

    /**
     * If lighting updates should be performed.
     */
    @get:JvmName("lighting")
    val lighting: Boolean

    /**
     * Creates a new set of flags with the given [updateNeighbours] setting.
     *
     * @param updateNeighbours The new setting.
     *
     * @return The resulting flags.
     */
    fun withUpdateNeighbours(updateNeighbours: Boolean): BlockChangeFlags

    /**
     * Creates a new set of flags with the given [notifyClients] setting.
     *
     * @param notifyClients The new setting.
     *
     * @return The resulting flags.
     */
    fun withNotifyClients(notifyClients: Boolean): BlockChangeFlags

    /**
     * Creates a new set of flags with the given [updateNeighbourShapes]
     * setting.
     *
     * @param updateNeighbourShapes The new setting.
     *
     * @return The resulting flags.
     */
    fun withUpdateNeighbourShapes(updateNeighbourShapes: Boolean): BlockChangeFlags

    /**
     * Creates a new set of flags with the given [neighbourDrops] setting.
     *
     * @param neighbourDrops The new setting.
     *
     * @return The resulting flags.
     */
    fun withNeighbourDrops(neighbourDrops: Boolean): BlockChangeFlags

    /**
     * Creates a new set of flags with the given [blockMoving] setting.
     *
     * @param blockMoving The new setting.
     *
     * @return The resulting flags.
     */
    fun withBlockMoving(blockMoving: Boolean): BlockChangeFlags

    /**
     * Creates a new set of flags with the given [lighting] setting.
     *
     * @param lighting The new setting.
     *
     * @return The resulting flags.
     */
    fun withLighting(lighting: Boolean): BlockChangeFlags

    /**
     * Performs a bitwise NOT operation on this set of flags.
     *
     * @return The resulting flags.
     */
    fun not(): BlockChangeFlags

    /**
     * Performs a bitwise AND operation between this set of flags and the
     * given [other] set of flags.
     *
     * @param other The other flags.
     *
     * @return The resulting flags.
     */
    fun and(other: BlockChangeFlags): BlockChangeFlags

    /**
     * Performs a bitwise OR operation between this set of flags and the
     * given [other] set of flags.
     *
     * @param other The other flags.
     *
     * @return The resulting flags.
     */
    fun or(other: BlockChangeFlags): BlockChangeFlags

    @TypeFactory
    @ApiStatus.Internal
    interface Factory {

        fun none(): BlockChangeFlags

        fun all(): BlockChangeFlags
    }

    companion object {

        /**
         * A set of flags with no flags set.
         *
         * @return A flag set with no flags set.
         */
        @JvmStatic
        fun none(): BlockChangeFlags = AquaMine.factory<Factory>().none()

        /**
         * A set of flags with all the flags set.
         *
         * @return A flag set with all flags set.
         */
        @JvmStatic
        fun all(): BlockChangeFlags = AquaMine.factory<Factory>().all()
    }
}

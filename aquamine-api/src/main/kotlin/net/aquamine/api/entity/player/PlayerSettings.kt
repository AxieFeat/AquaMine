package net.aquamine.api.entity.player

import net.aquamine.annotations.ImmutableType
import net.aquamine.api.entity.MainHand
import java.util.Locale

/**
 * The settings for a player indicated by the client.
 */
@Suppress("INAPPLICABLE_JVM_NAME")
@ImmutableType
interface PlayerSettings {

    /**
     * The locale of the player.
     *
     * If the client has not specified this, this will be null.
     */
    val locale: Locale?

    /**
     * The number of chunks that the player will see in front of them,
     * excluding the chunk the player is in.
     */
    val viewDistance: Int

    /**
     * The chat visibility of the player.
     */
    val chatVisibility: ChatVisibility

    /**
     * If the player accepts colors in chat messages.
     */
    @get:JvmName("hasChatColors")
    val hasChatColors: Boolean

    /**
     * The skin parts that the player has shown.
     */
    val skinParts: SkinParts

    /**
     * The primary hand of the player.
     */
    val mainHand: MainHand

    /**
     * If the player allows appearing in the player sample on the server
     * status.
     */
    @get:JvmName("allowsServerListing")
    val allowsServerListing: Boolean
}

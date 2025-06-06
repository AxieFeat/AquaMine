package net.aquamine.api.resource

import net.aquamine.annotations.ImmutableType
import net.aquamine.annotations.TypeFactory
import net.kyori.adventure.text.Component
import org.jetbrains.annotations.ApiStatus
import org.jetbrains.annotations.Contract
import net.aquamine.api.AquaMine
import java.net.URI

/**
 * A resource pack that may be sent to clients.
 */
@ImmutableType
interface ResourcePack {

    /**
     * The URI pointing to the location where this resource pack is from.
     */
    val uri: URI

    /**
     * The hash of the resource pack. This should be generated from hashing the
     * resource that the [uri] points to.
     */
    val hash: String

    /**
     * If clients must always display this resource pack.
     */
    val isForced: Boolean

    /**
     * The message that will be shown to the client within the prompt used to
     * confirm the resource pack.
     */
    val promptMessage: Component?

    /**
     * The status of a resource pack.
     */
    enum class Status {

        /**
         * The client has successfully downloaded and applied the resource
         * pack.
         */
        SUCCESSFULLY_LOADED,

        /**
         * The client refused to accept the resource pack.
         */
        DECLINED,

        /**
         * The client accepted the resource pack, but it failed to download it.
         */
        FAILED_DOWNLOAD,

        /**
         * The client accepted the resource pack and is attempting to download
         * it.
         */
        ACCEPTED
    }

    @ApiStatus.Internal
    @TypeFactory
    interface Factory {

        fun of(uri: URI, hash: String, isForced: Boolean, promptMessage: Component?): ResourcePack
    }

    companion object {

        /**
         * Creates a new resource pack with the given values.
         *
         * @param uri The URI.
         * @param hash The hash of the resource at the URI.
         * @param isForced If all clients must use the resource pack.
         * @param promptMessage The message sent with the prompt to accept the
         * pack.
         */
        @JvmStatic
        @Contract("_, _, _, _ -> new", pure = true)
        fun of(uri: URI, hash: String, isForced: Boolean, promptMessage: Component?): ResourcePack =
            AquaMine.factory<Factory>().of(uri, hash, isForced, promptMessage)
    }
}

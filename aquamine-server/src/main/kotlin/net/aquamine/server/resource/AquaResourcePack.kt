package net.aquamine.server.resource

import net.kyori.adventure.text.Component
import net.aquamine.api.resource.ResourcePack
import java.net.URI

@JvmRecord
data class AquaResourcePack(
    override val uri: URI,
    override val hash: String,
    override val isForced: Boolean,
    override val promptMessage: Component?
) : ResourcePack {

    object Factory : ResourcePack.Factory {

        override fun of(uri: URI, hash: String, isForced: Boolean, promptMessage: Component?): ResourcePack =
            AquaResourcePack(uri, hash, isForced, promptMessage)
    }
}

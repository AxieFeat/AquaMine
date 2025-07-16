package net.aquamine.server.util.provider

import net.aquamine.serialization.Codec

fun interface IntProviderType<P : IntProvider> {

    fun codec(): Codec<P>
}

package net.aquamine.server.util.provider

import org.kryptonmc.serialization.Codec

fun interface IntProviderType<P : IntProvider> {

    fun codec(): Codec<P>
}

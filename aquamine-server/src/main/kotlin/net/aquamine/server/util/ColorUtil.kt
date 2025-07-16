package net.aquamine.server.util

import net.aquamine.api.util.Color
import net.aquamine.serialization.Codec

object ColorUtil {

    @JvmField
    val CODEC: Codec<Color> = Codec.INT.xmap({ Color(it) }, { it.encode() })
}

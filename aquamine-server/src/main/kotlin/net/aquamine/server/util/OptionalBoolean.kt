package net.aquamine.server.util

import java.util.Optional

object OptionalBoolean {

    @JvmField
    val TRUE: Optional<Boolean> = Optional.of(true)
    @JvmField
    val FALSE: Optional<Boolean> = Optional.of(false)

    @JvmStatic
    fun of(value: Boolean): Optional<Boolean> = if (value) TRUE else FALSE
}

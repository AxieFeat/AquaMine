package net.aquamine.server.util

import net.aquamine.serialization.DataResult
import java.util.Optional

inline fun <R> Optional<R>.successOrError(crossinline message: () -> String): DataResult<R> =
    map { DataResult.success(it!!) }.orElseGet { DataResult.error(message()) }

inline fun <R> R?.resultOrError(error: () -> String): DataResult<R> = if (this == null) DataResult.error(error()) else DataResult.success(this)

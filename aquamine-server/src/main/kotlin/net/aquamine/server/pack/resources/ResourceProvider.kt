package net.aquamine.server.pack.resources

import net.kyori.adventure.key.Key
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStream

fun interface ResourceProvider {

    fun getResource(location: Key): Resource?

    fun getResourceOrThrow(location: Key): Resource = getResource(location) ?: throw FileNotFoundException(location.toString())

    fun open(location: Key): InputStream = getResourceOrThrow(location).open()

    fun openAsReader(location: Key): BufferedReader = getResourceOrThrow(location).openAsReader()
}

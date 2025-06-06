package net.aquamine.api

import net.aquamine.annotations.ImmutableType

/**
 * Provides information about the current platform this is running on, such as
 * the name and version, if it is considered stable, and the target Minecraft
 * version.
 */
@ImmutableType
interface Platform {

    /**
     * The name of the platform.
     */
    val name: String

    /**
     * The version of the platform.
     */
    val version: String

    /**
     * The version of vanilla Minecraft that this platform targets.
     */
    val minecraftVersion: String

    /**
     * The version of the world format used by this platform.
     */
    val worldVersion: Int

    /**
     * The version of the protocol used by this platform.
     */
    val protocolVersion: Int

    /**
     * The version of the data pack format used by this platform.
     */
    val dataPackVersion: Int
}

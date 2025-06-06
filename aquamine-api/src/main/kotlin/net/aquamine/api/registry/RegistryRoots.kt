package net.aquamine.api.registry

import net.kyori.adventure.key.Key

/**
 * All the built-in registry roots.
 */
object RegistryRoots {

    /**
     * The built-in Minecraft root, used for all the vanilla registries.
     *
     * The key of this root is "minecraft:root".
     */
    @JvmField
    val MINECRAFT: Key = Key.key("minecraft", "root")

    /**
     * The AquaMine root, designed to be used for custom registries.
     *
     * The key of this root is "aquamine:root".
     */
    @JvmField
    val AQUAMINE: Key = Key.key("aquamine", "root")
}

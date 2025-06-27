package net.aquamine.server.registry

import net.aquamine.api.registry.DefaultedRegistry

/**
 * This type exists to unify the APIs of AquaRegistry and DefaultedRegistry, so we can use internal methods on defaulted registries
 * without having to use the implementation class, which is writable.
 */
interface AquaDefaultedRegistry<T> : AquaRegistry<T>, DefaultedRegistry<T>

package net.aquamine.server.registry

import net.aquamine.api.registry.DefaultedRegistry

/**
 * This type exists to unify the APIs of KryptonRegistry and DefaultedRegistry, so we can use internal methods on defaulted registries
 * without having to use the implementation class, which is writable.
 */
interface KryptonDefaultedRegistry<T> : KryptonRegistry<T>, DefaultedRegistry<T>

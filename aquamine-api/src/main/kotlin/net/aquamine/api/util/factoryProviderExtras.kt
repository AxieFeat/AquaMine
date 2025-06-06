@file:JvmSynthetic
package net.aquamine.api.util

/**
 * Provides the factory with the given type [T], or throws a
 * [TypeNotFoundException] if there is no factory registered for the given
 * type.
 *
 * @param T The factory type.
 */
@JvmSynthetic
inline fun <reified T> FactoryProvider.provide(): T = provide(T::class.java)

/**
 * Registers the given [factory] of the given type [T] to this factory
 * provider.
 *
 * @param T The factory type.
 * @param factory The factory to register.
 *
 * @throws IllegalStateException If the factory is already registered.
 */
@JvmSynthetic
inline fun <reified T> FactoryProvider.register(factory: T) {
    register(T::class.java, factory)
}

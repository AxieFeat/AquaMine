package net.aquamine.server.util

/**
 * Downcasts the given API type [A] to its implementation type [I].
 *
 * This is a common implementation shared between specific downcast functions
 * that are used to downcast a specific API type to its implementation
 * equivalent when implementation-specific information is required.
 */
inline fun <A, reified I : A> A.downcastApiType(name: String): I {
    check(this is I) { "Custom implementations of $name are not supported!" }
    return this
}

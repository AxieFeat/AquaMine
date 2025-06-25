package net.aquamine.server.util.crypto

/**
 * A general exception signalling an error with one of the cryptography functions. This always wraps other exceptions.
 */
class CryptException(cause: Throwable?) : Exception(cause)

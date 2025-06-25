package net.aquamine.server.util.crypto

sealed class InsecurePublicKeyException(message: String) : RuntimeException(message) {

    class Missing : InsecurePublicKeyException("Missing public key!")

    class Invalid(message: String) : InsecurePublicKeyException(message)
}

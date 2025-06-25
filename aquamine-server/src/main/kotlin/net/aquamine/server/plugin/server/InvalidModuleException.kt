package net.aquamine.server.plugin.server

class InvalidModuleException : Exception {

    constructor(message: String, cause: Throwable?) : super(message, cause)

    constructor(message: String) : super(message)
}

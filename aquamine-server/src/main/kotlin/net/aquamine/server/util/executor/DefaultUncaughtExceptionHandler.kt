package net.aquamine.server.util.executor

import org.apache.logging.log4j.Logger

class DefaultUncaughtExceptionHandler(private val logger: Logger) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread?, exception: Throwable) {
        logger.error("Caught unhandled exception!", exception)
    }
}

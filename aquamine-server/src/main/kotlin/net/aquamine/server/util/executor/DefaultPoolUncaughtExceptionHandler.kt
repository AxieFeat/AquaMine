package net.aquamine.server.util.executor

import org.apache.logging.log4j.Logger

class DefaultPoolUncaughtExceptionHandler(private val logger: Logger) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        logger.error("Caught unhandled exception in thread ${thread.name}!", exception)
    }
}

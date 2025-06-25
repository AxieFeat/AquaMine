package net.aquamine.server.util.executor

import com.google.common.util.concurrent.ThreadFactoryBuilder
import java.util.concurrent.ThreadFactory

inline fun daemonThreadFactory(nameFormat: String, builder: ThreadFactoryBuilder.() -> Unit = {}): ThreadFactory =
    ThreadFactoryBuilder().setNameFormat(nameFormat).setDaemon(true).apply(builder).build()

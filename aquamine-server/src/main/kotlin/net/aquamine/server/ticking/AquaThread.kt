package net.aquamine.server.ticking

open class AquaThread(
    group: ThreadGroup?,
    task: Runnable?,
    name: String,
    stackSize: Long,
    priority: Int
) : Thread(group, task, "AquaMine $name", stackSize) {

    init {
        this.priority = priority
    }

    constructor(name: String, priority: Int = NORM_PRIORITY) : this(null, null, name, 0, priority)

    constructor(task: Runnable, name: String, priority: Int = NORM_PRIORITY) : this(null, task, name, 0, priority)
}

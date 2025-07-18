package net.aquamine.server.plugin.server

class ServerModules(val all: Set<String>, val enabled: Set<String>) {

    fun isEnabled(moduleName: String): Boolean = enabled.contains(moduleName)

    companion object {

        @JvmField
        val ALL_MODULES: Set<String> = setOf()

        @JvmStatic
        fun createDefault(enabled: Set<String>): ServerModules = ServerModules(ALL_MODULES, enabled)
    }
}

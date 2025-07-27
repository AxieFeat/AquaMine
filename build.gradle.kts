plugins {
    id("aquamine.common")
}

allprojects {
    apply(plugin = "aquamine.common")

    group = rootProject.findProperty("group") ?: throw IllegalStateException("Project group not specified")
    version = rootProject.findProperty("version") ?: throw IllegalStateException("Project version not specified")
    description = rootProject.findProperty("description") as? String ?: run {
        logger.warn("Project description not found, fallback to empty string.")
        return@run ""
    }
}
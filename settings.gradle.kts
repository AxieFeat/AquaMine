enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "AquaMine"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

project("api")
project("server")
project("protocol")
project("annotations")
project("annotation-processor")
project("generator")

fun project(projectName: String) {
    include("${rootProject.name.lowercase()}-$projectName")
}